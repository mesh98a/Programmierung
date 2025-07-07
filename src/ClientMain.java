import cli.Console;
import eventsimpl.clievent.CliHandlerController;
import eventsimpl.automatevent.Mode;
import domainpackage.Automat;
import eventsystem.automatsystem.AutomatEventHandler;
import eventsystem.clisystem.CliEventHandler;
import listeners.cli.DisplayCakeResponseListener;
import listeners.cli.HerstellerMapResponseListener;
import listeners.net.ClientListener;
import listeners.net.ClientRequestListener;
import net.Client;
import observers.AutomatCapacityObserver;
import observers.AutomatChangesObserver;

import java.io.IOException;
import java.net.Socket;
import java.rmi.ConnectException;

public class ClientMain {

    public static void main(String[] args) {
        try {
//            Automat automat = new Automat(3);
            Console cli = new Console();
            Socket socket = new Socket("localhost", 9000);

            CliHandlerController controller = new CliHandlerController();
            controller.displayHerstellerMapHandler = new CliEventHandler<>();
            controller.displayCakeHandler = new CliEventHandler<>();
            controller.displayHerstellerMapHandler.add(new HerstellerMapResponseListener());
            controller.displayCakeHandler.add(new DisplayCakeResponseListener());

            Client client = new Client(socket, controller);

//           client.startReceiving();


//            AutomatCapacityObserver capacityObserver = new AutomatCapacityObserver(automat);
//            AutomatChangesObserver changesObserver = new AutomatChangesObserver(automat);

            AutomatEventHandler insertCakeHandler = new AutomatEventHandler();
            insertCakeHandler.add(new ClientListener(client));

            AutomatEventHandler insertHerstellerHandler = new AutomatEventHandler();
            insertHerstellerHandler.add(new ClientListener(client));

            AutomatEventHandler getHerstellerHandler = new AutomatEventHandler();
            getHerstellerHandler.add(new ClientRequestListener(client));

            AutomatEventHandler displayCakeHandler = new AutomatEventHandler();
            displayCakeHandler.add(new ClientRequestListener(client));

            AutomatEventHandler deleteCakeHandler = new AutomatEventHandler();
            deleteCakeHandler.add(new ClientListener(client));

            AutomatEventHandler deleteHerstellerHandler = new AutomatEventHandler();
            deleteHerstellerHandler.add(new ClientListener(client));

            AutomatEventHandler inspectDateHandler = new AutomatEventHandler();
            inspectDateHandler.add(new ClientListener(client));

            cli.setHandler(Mode.DISPLAY_CAKE,displayCakeHandler);
            cli.setHandler(Mode.DISPLAY_HERSTELLER, getHerstellerHandler);
            cli.setHandler(Mode.INSERT_CAKE, insertCakeHandler);
            cli.setHandler(Mode.INSERT_HERSTELLER, insertHerstellerHandler);
            cli.setHandler(Mode.DELETE_CAKE, deleteCakeHandler);
            cli.setHandler(Mode.DELETE_HERSTELLER,deleteHerstellerHandler);
            cli.setHandler(Mode.UPDATE_INSPECTDATE, inspectDateHandler);

            cli.execute();

        } catch (ConnectException e) {
            System.out.println("Connection refused");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
