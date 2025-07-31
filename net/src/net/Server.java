package net;

import domainpackage.Automat;
import eventsystem.automatsystem.AutomatEvent;
import eventsystem.automatsystem.AutomatEventHandler;
import eventsimpl.automatevent.Mode;
import eventsystem.clisystem.CliEvent;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.EnumMap;
import java.util.Map;

public class Server implements Runnable {
    private final Automat automat;
    private final Socket socket;
    private final ObjectInputStream upStream;
    private final ObjectOutputStream downStream;
    private final Map<Mode, AutomatEventHandler> handlers = new EnumMap<>(Mode.class);
//    private final AutomatEventHandler handler;

    public Server(Socket socket, Automat automat) throws IOException {
        this.automat = automat;
        this.socket = socket;
        this.downStream = new ObjectOutputStream(socket.getOutputStream());
        this.upStream = new ObjectInputStream(socket.getInputStream());
    }

    public void setHandler(Mode mode, AutomatEventHandler handler) {
        handlers.put(mode, handler);
    }

    @Override
    public void run() {
        try {
            while (true) {
                receive();
            }
        } catch (SocketException | EOFException e) {
            System.out.println("client @" + this.socket.getInetAddress() + ":" + this.socket.getPort() + " disconnect");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void send(CliEvent event){
        try {
            if (socket.isConnected()) {
                String stringEvent = event.getClass().getSimpleName();
                System.out.println("Server sendet Antwort: " + stringEvent);
                this.downStream.writeUTF(stringEvent);
                this.downStream.writeObject(event);
                this.downStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(Object message) {
        try {
            downStream.writeObject(message);
            downStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receive() throws IOException, ClassNotFoundException {
        String type = this.upStream.readUTF();
        System.out.println("Typ empfangen: " + type);
        AutomatEvent event = (AutomatEvent) this.upStream.readObject();
        Mode mode = Mode.fromEventClassName(type);

        if (mode != null) {
            AutomatEventHandler handler = handlers.get(mode);
            if (handler != null) {
                handler.handle(event);
            } else {
                System.out.println("Kein Handler für Mode: " + mode);
            }
        } else {
            System.out.println("Unbekannter Event-Typ: " + event.getClass().getSimpleName());
        }

    }
}

