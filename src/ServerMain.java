import domainpackage.Automat;
import eventsimpl.clievent.CliHandlerController;
import eventsystem.automatsystem.AutomatEventHandler;
import eventsimpl.automatevent.Mode;
import eventsystem.clisystem.CliEventHandler;
import listeners.automat.*;
import listeners.net.ServerListener;
import net.Server;
import observers.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void main(String[] args) {

        Automat automat = new Automat(3);

        try (ServerSocket serverSocket = new ServerSocket(9000)) {
            System.out.println("Server started...");
            while(true){
                Socket socket = serverSocket.accept();
                Server s = new Server(socket, automat);

                AutomatCapacityObserver capacityObserver = new AutomatCapacityObserver(automat);
                AutomatChangesObserver changesObserver = new AutomatChangesObserver(automat);

                CliHandlerController controller = new CliHandlerController();
                controller.displayHerstellerMapHandler = new CliEventHandler<>();
                controller.displayCakeHandler = new CliEventHandler<>();
                controller.displayHerstellerMapHandler.add(new ServerListener(s));
                controller.displayCakeHandler.add(new ServerListener(s));

                AutomatEventHandler insertCakeHandler = new AutomatEventHandler();
                insertCakeHandler.add(new InsertCakeListener(automat));

                AutomatEventHandler insertHerstellerHandler = new AutomatEventHandler();
                insertHerstellerHandler.add(new InsertHerstellerListener(automat));

//                CliEventHandler cliHerstellerHandler = new CliEventHandler();
                AutomatEventHandler getHerstellerHandler = new AutomatEventHandler();
                getHerstellerHandler.add(new GetHerstellerMapListener(automat,controller.displayHerstellerMapHandler));
//                cliHerstellerHandler.add(new ServerListener(s));
                AutomatEventHandler displayCakeHandler = new AutomatEventHandler();
                displayCakeHandler.add(new DisplayCakeListener(automat,controller.displayCakeHandler));

                AutomatEventHandler deleteCakeHandler = new AutomatEventHandler();
                deleteCakeHandler.add(new DeleteCakeListener(automat));

                AutomatEventHandler deleteHerstellerHandler = new AutomatEventHandler();
                deleteHerstellerHandler.add(new DeleteHerstellerListener(automat));

                AutomatEventHandler inspectDateHandler = new AutomatEventHandler();
                inspectDateHandler.add(new InspectCakeListener(automat));

                s.setHandler(Mode.INSERT_CAKE, insertCakeHandler);
                s.setHandler(Mode.INSERT_HERSTELLER, insertHerstellerHandler);
                s.setHandler(Mode.DISPLAY_HERSTELLER,getHerstellerHandler);
                s.setHandler(Mode.DISPLAY_CAKE,displayCakeHandler);
                s.setHandler(Mode.DELETE_CAKE, deleteCakeHandler);
                s.setHandler(Mode.DELETE_HERSTELLER,deleteHerstellerHandler);
                s.setHandler(Mode.UPDATE_INSPECTDATE, inspectDateHandler);

                System.out.println("new client@" + socket.getInetAddress() + ":" + socket.getPort());
                new Thread(s).start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
