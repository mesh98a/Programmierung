
import cli.ConsoleOhneModi;
import cli.Mode;
import domainpackage.Automat;
import eventsystem.automatsystem.AutomatEventHandler;
import eventsystem.clisystem.CliEventHandler;
import cli.AutomatObserver;
import listeners.automat.*;
import listeners.cli.*;

public class MainOhneModi {
    /* Enter command (:c = insert, :r = read, :d = delete, :u = update)
:c Obstkuchen Sonne 10.0 400 4 Gluten Apfel
    Enter command (:c = insert, :r = read, :d = delete, :u = update)
:c Obstkuchen Sonne 10.0 400 4 Gluten Zitrone
    Enter command (:c = insert, :r = read, :d = delete, :u = update)
:c Kremkuchen Sonne 10.0 400 4 Gluten Sahne
Kapazität über 90%
    Enter command (:c = insert, :r = read, :d = delete, :u = update)
:u 0 2025-05-05
    Enter command (:c = insert, :r = read, :d = delete, :u = update)
:r obst
Fachnummer: 0 Die verbleibende Haltbarkeit : 14345 Inspektionsdatum: Mon May 05 00:00:00 CEST 2025
Fachnummer: 1 Die verbleibende Haltbarkeit : 14352
    Enter command (:c = insert, :r = read, :d = delete, :u = update)
:d 0
    Enter command (:c = insert, :r = read, :d = delete, :u = update)
:r obst
Fachnummer: 1 Die verbleibende Haltbarkeit : 14330*/

    public static void main(String[] args) {
        Automat automat = new Automat(3);
        ConsoleOhneModi cli = new ConsoleOhneModi();

        AutomatObserver observer = new AutomatObserver(automat);

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

        AutomatEventHandler deleteCakeHandler = new AutomatEventHandler();
        deleteCakeHandler.add(new DeleteCakeListener(automat));

        AutomatEventHandler inspectDateHandler = new AutomatEventHandler();
        inspectDateHandler.add(new InspectCakeListener(automat));


        cli.setHandler(Mode.INSERT_CAKE, insertCakeHandler);
        cli.setHandler(Mode.INSERT_HERSTELLER, insertHerstellerHandler);
        cli.setHandler(Mode.DISPLAY_HERSTELLER, getHerstellerHandler);
        cli.setHandler(Mode.DISPLAY_CAKE, displayCakeHandler);
        cli.setHandler(Mode.DELETE_CAKE, deleteCakeHandler);
        cli.setHandler(Mode.UPDATE_INSPECTDATE, inspectDateHandler);

        cli.execute();
    }
}
