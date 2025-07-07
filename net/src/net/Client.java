package net;

import eventsimpl.clievent.CliHandlerController;
import eventsimpl.clievent.HerstellerMapResponseEvent;
import eventsystem.automatsystem.AutomatEvent;
import eventsystem.clisystem.CliEvent;
import eventsystem.clisystem.CliEventHandler;

import java.io.*;
import java.net.Socket;
import java.util.EventObject;

public class Client {
    private final Socket socket;
    private final ObjectOutputStream upStream;
    private final ObjectInputStream downStream;
    private final CliHandlerController controller;

    public Client(Socket socket, CliHandlerController controller) throws IOException {
        this.controller = controller;
        this.socket = socket;
        this.upStream = new ObjectOutputStream(socket.getOutputStream());
        this.downStream = new ObjectInputStream(socket.getInputStream());
    }

    public ObjectOutputStream getUpStream() {
        return upStream;
    }

    public ObjectInputStream getDownStream() {
        return downStream;
    }

    public void send(AutomatEvent event) {

        try {
            if (socket.isConnected()) {
                String stringEvent = event.getClass().getSimpleName();
                System.out.println("Client sendet Event: " + stringEvent);
                this.upStream.writeUTF(stringEvent);
                this.upStream.writeObject(event);
                this.upStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receive() {
        try {
            String type = this.downStream.readUTF();
            CliEvent event = (CliEvent) this.downStream.readObject();
            switch (type) {
                case "HerstellerMapResponseEvent":
                    controller.displayHerstellerMapHandler.handle(event);
                    break;
                case "DisplayCakeResponseEvent":
                    controller.displayCakeHandler.handle(event);
                    break;
                default:
                    System.out.println("Unknown event: " + type);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    public void startReceiving() {
//        new Thread(() -> {
//            try {
//                while (true) {
//                    String type = downStream.readUTF();
//                    CliEvent event = (CliEvent) this.downStream.readObject();
//                    switch (type) {
//                        case "HerstellerMapResponseEvent":
//                            this.cliHandler.handle(event);
//                            break;
//                        default:
//                            System.out.println("Unknown event: " + type);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }

}
