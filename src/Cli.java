import cli.Console;
import cli.Mode;
import domainpackage.Automat;
import eventsystem.automatsystem.AutomatEventHandler;
import eventsystem.clisystem.CliEventHandler;
import listeners.automat.*;
import listeners.cli.DisplayAllergenResponseListener;
import listeners.cli.DisplayKeineAllergenResponseListener;
import listeners.cli.HerstellerMapResponseListener;
import listeners.cli.DisplayCakeResponseListener;
import observers.AutomatCapacityObserver;
import observers.AutomatChangesObserver;

public class Cli {

    public static void main(String[] args) {
        Automat automat = new Automat(3);
        Console cli = new Console();

        AutomatCapacityObserver capacityObserver = new AutomatCapacityObserver(automat);
        AutomatChangesObserver changesObserver = new AutomatChangesObserver(automat);


        AutomatEventHandler insertCakeHandler = new AutomatEventHandler();
        insertCakeHandler.add(new InsertCakeListener(automat));

        AutomatEventHandler insertHerstellerHandler = new AutomatEventHandler();
        insertHerstellerHandler.add(new InsertHerstellerListener(automat));

        CliEventHandler cliHerstellerHandler = new CliEventHandler();
        AutomatEventHandler getHerstellerHandler = new AutomatEventHandler();
        getHerstellerHandler.add(new GetHerstellerMapListener(automat,cliHerstellerHandler));
        cliHerstellerHandler.add(new HerstellerMapResponseListener());

        CliEventHandler cliDisplayCakeHandler = new CliEventHandler();
        AutomatEventHandler displayCakeHandler = new AutomatEventHandler();
        displayCakeHandler.add(new DisplayCakeListener(automat,cliDisplayCakeHandler));
        cliDisplayCakeHandler.add(new DisplayCakeResponseListener());

        CliEventHandler cliDisplayAllergenHandler = new CliEventHandler();
        AutomatEventHandler displayAllergenHandler = new AutomatEventHandler();
        displayAllergenHandler.add(new DisplayAllergenListener(automat,cliDisplayAllergenHandler));
        cliDisplayAllergenHandler.add(new DisplayAllergenResponseListener());


        CliEventHandler cliDisplayKeineAllergenHandler = new CliEventHandler();
        AutomatEventHandler displayKeineAllergenHandler = new AutomatEventHandler();
        displayKeineAllergenHandler.add(new DisplayKeineAllergenListener(automat,cliDisplayKeineAllergenHandler));
        cliDisplayKeineAllergenHandler.add(new DisplayKeineAllergenResponseListener());

        AutomatEventHandler deleteCakeHandler = new AutomatEventHandler();
        deleteCakeHandler.add(new DeleteCakeListener(automat));

        AutomatEventHandler deleteHerstellerHandler = new AutomatEventHandler();
        deleteHerstellerHandler.add(new DeleteHerstellerListener(automat));

        AutomatEventHandler inspectDateHandler = new AutomatEventHandler();
        inspectDateHandler.add(new InspectCakeListener(automat));




        cli.setHandler(Mode.INSERT_CAKE, insertCakeHandler);
        cli.setHandler(Mode.INSERT_HERSTELLER, insertHerstellerHandler);
        cli.setHandler(Mode.DISPLAY_HERSTELLER, getHerstellerHandler);
        cli.setHandler(Mode.DISPLAY_CAKE, displayCakeHandler);
        cli.setHandler(Mode.DELETE_CAKE, deleteCakeHandler);
        cli.setHandler(Mode.DELETE_HERSTELLER,deleteHerstellerHandler);
        cli.setHandler(Mode.UPDATE_INSPECTDATE, inspectDateHandler);
        cli.setHandler(Mode.DISPLAY_ALLERGEN,displayAllergenHandler);
        cli.setHandler(Mode.DISPLAY_KEINE_ALLERGEN,displayKeineAllergenHandler);

        cli.execute();


    }
}
