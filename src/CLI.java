import cli.Console;
import eventsimpl.clievent.CliHandlerController;
import eventsimpl.automatevent.Mode;
import eventsystem.automatsystem.AutomatEventHandler;
import eventsystem.clisystem.CliEventHandler;
import listeners.cli.DisplayAllergenResponseListener;
import listeners.cli.DisplayCakeResponseListener;
import listeners.cli.HerstellerMapResponseListener;
import listeners.net.ClientListener;
import listeners.net.ClientRequestListener;
import net.Client;

import java.io.IOException;
import java.net.Socket;
import java.rmi.ConnectException;

public class CLI {

    public static void main(String[] args) {
        if (args[0].equals("TCP")) {
            try {

                Console cli = new Console();
                Socket socket = new Socket("localhost", 9000);

                CliHandlerController controller = new CliHandlerController();
                controller.displayHerstellerMapHandler = new CliEventHandler<>();
                controller.displayCakeHandler = new CliEventHandler<>();
                controller.displayAllergenHandler = new CliEventHandler<>();
                controller.displayHerstellerMapHandler.add(new HerstellerMapResponseListener());
                controller.displayCakeHandler.add(new DisplayCakeResponseListener());
                controller.displayAllergenHandler.add(new DisplayAllergenResponseListener());

                Client client = new Client(socket, controller);

                AutomatEventHandler insertCakeHandler = new AutomatEventHandler();
                insertCakeHandler.add(new ClientListener(client));

                AutomatEventHandler insertHerstellerHandler = new AutomatEventHandler();
                insertHerstellerHandler.add(new ClientListener(client));

                AutomatEventHandler getHerstellerHandler = new AutomatEventHandler();
                getHerstellerHandler.add(new ClientRequestListener(client));

                AutomatEventHandler displayCakeHandler = new AutomatEventHandler();
                displayCakeHandler.add(new ClientRequestListener(client));

                AutomatEventHandler displayAllergenHandler = new AutomatEventHandler();
                displayAllergenHandler.add(new ClientRequestListener(client));

                AutomatEventHandler deleteCakeHandler = new AutomatEventHandler();
                deleteCakeHandler.add(new ClientListener(client));

                AutomatEventHandler deleteHerstellerHandler = new AutomatEventHandler();
                deleteHerstellerHandler.add(new ClientListener(client));

                AutomatEventHandler inspectCakeHandler = new AutomatEventHandler();
                inspectCakeHandler.add(new ClientListener(client));

                AutomatEventHandler persistenceSaveHandler = new AutomatEventHandler();
                persistenceSaveHandler.add(new ClientListener(client));

                AutomatEventHandler persistenceLoadHandler = new AutomatEventHandler();
                persistenceLoadHandler.add(new ClientListener(client));

                cli.setHandler(Mode.DISPLAY_CAKE, displayCakeHandler);
                cli.setHandler(Mode.DISPLAY_HERSTELLER, getHerstellerHandler);
                cli.setHandler(Mode.DISPLAY_ALLERGEN, displayAllergenHandler);
                cli.setHandler(Mode.INSERT_CAKE, insertCakeHandler);
                cli.setHandler(Mode.INSERT_HERSTELLER, insertHerstellerHandler);
                cli.setHandler(Mode.DELETE_CAKE, deleteCakeHandler);
                cli.setHandler(Mode.DELETE_HERSTELLER, deleteHerstellerHandler);
                cli.setHandler(Mode.UPDATE_INSPECTDATE, inspectCakeHandler);
                cli.setHandler(Mode.PERSIST_SAVE, persistenceSaveHandler);
                cli.setHandler(Mode.PERSIST_LOAD, persistenceLoadHandler);

                cli.execute();

            } catch (ConnectException e) {
                System.out.println("Connection refused");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
