import cli.Console;
import cli.Mode;
import domainpackage.Automat;
import events.eventsystem.automatsystem.AutomatEventHandler;
import events.eventsystem.clisystem.CliEventHandler;
import events.listeners.automat.*;
import events.listeners.cli.DisplayCakeResponseListener;
import events.listeners.cli.HerstellerMapResponseListener;

public class Main {
    /*Enter command (:c = insert, :r = read, :d = delete, :u = update, :x = exit):
:c
Was möchtest du einfügen? (:h hersteller / :k kuchen)
:h
Herstellername:
Sonne
Hersteller eingefügt
Enter command (:c = insert, :r = read, :d = delete, :u = update, :x = exit):
:c
Was möchtest du einfügen? (:h hersteller / :k kuchen)
:k
Kuchen:
Obstkuchen Sonne 10.0 400 4 Gluten Sahne
Kuchen eingefügt
Enter command (:c = insert, :r = read, :d = delete, :u = update, :x = exit):
:r
Was möchtest du ansehen? (:h hersteller / :k kuchen)
:k
Filtertyp:
Obst
Fachnummer: 0 Die verbleibende Haltbarkeit : 14382
Enter command (:c = insert, :r = read, :d = delete, :u = update, :x = exit):
:u
Fachnummer des Kuchens und das Datum im yyyy-mm-dd Format:
0 2025-05-05
Inspektionsdatum eingesetzt
Enter command (:c = insert, :r = read, :d = delete, :u = update, :x = exit):
:d
Fachnummer des Kuchens :
0
Kuchen gelöscht
Enter command (:c = insert, :r = read, :d = delete, :u = update, :x = exit):
:r
Was möchtest du ansehen? (:h hersteller / :k kuchen)
:k
Filtertyp:
obst
Enter command (:c = insert, :r = read, :d = delete, :u = update, :x = exit):
:x
Programm beendet */

    public static void main(String[] args) {
        Automat automat = new Automat(2);
        Console cli = new Console();

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
