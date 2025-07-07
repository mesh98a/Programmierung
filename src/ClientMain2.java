import cli.Console;
import domainpackage.Automat;

import java.io.IOException;
import java.net.Socket;
import java.rmi.ConnectException;

public class ClientMain2 {
    public static void main(String[] args) {


//        try {
//            Automat automat = new Automat(3);
//            Console cli = new Console();
//            Socket socket = new Socket("localhost", 9000);
//
//            AutomatCapacityObserver capacityObserver = new AutomatCapacityObserver(automat);
//            AutomatChangesObserver changesObserver = new AutomatChangesObserver(automat);
//            ObjectOutputStream upstream = new ObjectOutputStream(socket.getOutputStream());
//
//            AutomatEventHandler insertCakeHandler = new AutomatEventHandler();
//            insertCakeHandler.add(new ClientListener((upstream)));
//
//            AutomatEventHandler insertHerstellerHandler = new AutomatEventHandler();
//            insertHerstellerHandler.add(new ClientListener(upstream));
//
//            cli.setHandler(Mode.INSERT_CAKE, insertCakeHandler);
//            cli.setHandler(Mode.INSERT_HERSTELLER, insertHerstellerHandler);
//
//            cli.execute();
//
//        } catch (ConnectException e) {
//            System.out.println("Connection refused");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
