import cli.Console;
import domainpackage.Automat;
import eventsimpl.automatevent.Mode;
import eventsystem.automatsystem.AutomatEventHandler;
import eventsystem.clisystem.CliEventHandler;
import listeners.automat.*;
import listeners.cli.DisplayAllergenResponseListener;
import listeners.cli.DisplayCakeResponseListener;
import listeners.cli.HerstellerMapResponseListener;
import observers.AutomatAllergenObserver;
import observers.AutomatCapacityObserver;

public class AlternativesCli {
    private static int capacity = 5;
    public static void main(String[] args) {

        if (args.length > 0) {
            try {
                capacity = Integer.parseInt(args[0]);
                if (capacity <= 0) {
                    System.out.println("Kapazität muss eine positive Zahl sein. Standardwert 5 wird verwendet.");
                    capacity = 5;
                }
            } catch (NumberFormatException e) {
                System.out.println("Ungültiges Argument für Kapazität. Standardwert 5 wird verwendet.");
            }
        }

        Automat automat = new Automat(capacity);

        AutomatCapacityObserver capacityObserver = new AutomatCapacityObserver(automat);
        AutomatAllergenObserver changesObserver = new AutomatAllergenObserver(automat);

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
//        cli.setHandler(Mode.DELETE_HERSTELLER, deleteHerstellerHandler);
        cli.setHandler(Mode.UPDATE_INSPECTDATE, inspectDateHandler);
//        cli.setHandler(Mode.DISPLAY_ALLERGEN, displayAllergenHandler);
        cli.setHandler(Mode.PERSIST_SAVE, persistenceSaveHandler);
        cli.setHandler(Mode.PERSIST_LOAD, persistenceLoadHandler);

        cli.execute();


    }

}
