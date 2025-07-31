import cli.Console;
import domainpackage.Automat;
import eventsimpl.automatevent.Mode;
import eventsystem.automatsystem.AutomatEventHandler;
import eventsystem.clisystem.CliEventHandler;
import gui.viewmodel.ViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import listeners.automat.*;
import listeners.cli.DisplayAllergenResponseListener;
import listeners.cli.DisplayCakeResponseListener;
import listeners.cli.HerstellerMapResponseListener;
import observers.AutomatCapacityObserver;
import observers.AutomatAllergenObserver;

public class CliundGui extends Application {
    private Stage primaryStage;
    private static Automat automat;
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
        automat = new Automat(capacity);

        AutomatCapacityObserver capacityObserver = new AutomatCapacityObserver(automat);
        AutomatAllergenObserver changesObserver = new AutomatAllergenObserver(automat);

        new Thread(() -> {
            Console cli = new Console();

            AutomatEventHandler insertCakeHandler = new AutomatEventHandler();
            insertCakeHandler.add(new InsertCakeListener(automat));

            AutomatEventHandler insertHerstellerHandler = new AutomatEventHandler();
            insertHerstellerHandler.add(new InsertHerstellerListener(automat));

            CliEventHandler cliHerstellerHandler = new CliEventHandler();
            AutomatEventHandler getHerstellerHandler = new AutomatEventHandler();
            getHerstellerHandler.add(new GetHerstellerMapListener(automat, cliHerstellerHandler));
            cliHerstellerHandler.add(new HerstellerMapResponseListener());

            CliEventHandler cliDisplayCakeHandler = new CliEventHandler();
            AutomatEventHandler displayCakeHandler = new AutomatEventHandler();
            displayCakeHandler.add(new DisplayCakeListener(automat, cliDisplayCakeHandler));
            cliDisplayCakeHandler.add(new DisplayCakeResponseListener());

            CliEventHandler cliDisplayAllergenHandler = new CliEventHandler();
            AutomatEventHandler displayAllergenHandler = new AutomatEventHandler();
            displayAllergenHandler.add(new DisplayAllergenListener(automat, cliDisplayAllergenHandler));
            cliDisplayAllergenHandler.add(new DisplayAllergenResponseListener());

            AutomatEventHandler deleteCakeHandler = new AutomatEventHandler();
            deleteCakeHandler.add(new DeleteCakeListener(automat));

            AutomatEventHandler deleteHerstellerHandler = new AutomatEventHandler();
            deleteHerstellerHandler.add(new DeleteHerstellerListener(automat));

            AutomatEventHandler inspectDateHandler = new AutomatEventHandler();
            inspectDateHandler.add(new InspectCakeListener(automat));

            AutomatEventHandler persistenceSaveHandler = new AutomatEventHandler();
            persistenceSaveHandler.add(new PersistenceSaveListener(automat));

            AutomatEventHandler persistenceLoadHandler = new AutomatEventHandler();
            persistenceLoadHandler.add(new PersistenceLoadListener(automat));


            cli.setHandler(Mode.INSERT_CAKE, insertCakeHandler);
            cli.setHandler(Mode.INSERT_HERSTELLER, insertHerstellerHandler);
            cli.setHandler(Mode.DISPLAY_HERSTELLER, getHerstellerHandler);
            cli.setHandler(Mode.DISPLAY_CAKE, displayCakeHandler);
            cli.setHandler(Mode.DELETE_CAKE, deleteCakeHandler);
            cli.setHandler(Mode.DELETE_HERSTELLER, deleteHerstellerHandler);
            cli.setHandler(Mode.UPDATE_INSPECTDATE, inspectDateHandler);
            cli.setHandler(Mode.DISPLAY_ALLERGEN, displayAllergenHandler);
            cli.setHandler(Mode.PERSIST_SAVE, persistenceSaveHandler);
            cli.setHandler(Mode.PERSIST_LOAD, persistenceLoadHandler);

            cli.execute();
        }).start();

        Application.launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gui/view/view.fxml"));

        Parent root = loader.load();

        ViewModel controller = loader.getController();

        controller.setAutomat(automat);


        Scene scene = new Scene(root);

        this.primaryStage = stage;
        this.primaryStage.setTitle("CakeApp");

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

