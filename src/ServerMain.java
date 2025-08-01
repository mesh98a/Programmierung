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
    private static int capacity = 5;

    public static void main(String[] args) {

        if (args.length > 0) {
            try {
                capacity = Integer.parseInt(args[0]);
                System.out.println("Kapazität gesetzt auf: " + capacity);
            } catch (NumberFormatException e) {
                System.out.println("Ungültiges Argument: '" + args[0] + "'. Verwende Standard-Kapazität " + capacity);
            }
        }

        Automat automat = new Automat(capacity);
        if (args[1].equals("TCP")) {
            try (ServerSocket serverSocket = new ServerSocket(9000)) {
                System.out.println("Server started...");
                while (true) {
                    Socket socket = serverSocket.accept();
                    Server s = new Server(socket, automat);

                    AutomatCapacityObserver capacityObserver = new AutomatCapacityObserver(automat);
                    AutomatAllergenObserver changesObserver = new AutomatAllergenObserver(automat);

                    CliHandlerController controller = new CliHandlerController();
                    controller.displayHerstellerMapHandler = new CliEventHandler<>();
                    controller.displayCakeHandler = new CliEventHandler<>();
                    controller.displayAllergenHandler = new CliEventHandler<>();
                    controller.displayHerstellerMapHandler.add(new ServerListener(s));
                    controller.displayCakeHandler.add(new ServerListener(s));
                    controller.displayAllergenHandler.add(new ServerListener(s));


                    AutomatEventHandler insertCakeHandler = new AutomatEventHandler();
                    insertCakeHandler.add(new InsertCakeListener(automat));

                    AutomatEventHandler insertHerstellerHandler = new AutomatEventHandler();
                    insertHerstellerHandler.add(new InsertHerstellerListener(automat));

                    AutomatEventHandler getHerstellerHandler = new AutomatEventHandler();
                    getHerstellerHandler.add(new GetHerstellerMapListener(automat, controller.displayHerstellerMapHandler));

                    AutomatEventHandler displayCakeHandler = new AutomatEventHandler();
                    displayCakeHandler.add(new DisplayCakeListener(automat, controller.displayCakeHandler));

                    AutomatEventHandler displayAllergenHandler = new AutomatEventHandler();
                    displayAllergenHandler.add(new DisplayAllergenListener(automat, controller.displayAllergenHandler));

                    AutomatEventHandler deleteCakeHandler = new AutomatEventHandler();
                    deleteCakeHandler.add(new DeleteCakeListener(automat));

                    AutomatEventHandler deleteHerstellerHandler = new AutomatEventHandler();
                    deleteHerstellerHandler.add(new DeleteHerstellerListener(automat));

                    AutomatEventHandler inspectCakeHandler = new AutomatEventHandler();
                    inspectCakeHandler.add(new InspectCakeListener(automat));

                    AutomatEventHandler persistenceSaveHandler = new AutomatEventHandler();
                    persistenceSaveHandler.add(new PersistenceSaveListener(automat));

                    AutomatEventHandler persistenceLoadHandler = new AutomatEventHandler();
                    persistenceLoadHandler.add(new PersistenceLoadListener(automat));


                    s.setHandler(Mode.INSERT_CAKE, insertCakeHandler);
                    s.setHandler(Mode.INSERT_HERSTELLER, insertHerstellerHandler);
                    s.setHandler(Mode.DISPLAY_HERSTELLER, getHerstellerHandler);
                    s.setHandler(Mode.DISPLAY_ALLERGEN, displayAllergenHandler);
                    s.setHandler(Mode.DISPLAY_CAKE, displayCakeHandler);
                    s.setHandler(Mode.DELETE_CAKE, deleteCakeHandler);
                    s.setHandler(Mode.DELETE_HERSTELLER, deleteHerstellerHandler);
                    s.setHandler(Mode.UPDATE_INSPECTDATE, inspectCakeHandler);
                    s.setHandler(Mode.PERSIST_SAVE, persistenceSaveHandler);
                    s.setHandler(Mode.PERSIST_LOAD, persistenceLoadHandler);

                    System.out.println("new client@" + socket.getInetAddress() + ":" + socket.getPort());
                    new Thread(s).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
