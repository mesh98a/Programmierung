package commands;

import cli.Console;
import eventsimpl.automatevent.Mode;
import eventsimpl.automatevent.DisplayAllergenEvent;
import eventsimpl.automatevent.DisplayCakeEvent;
import eventsimpl.automatevent.DisplayKeineAllergenEvent;
import eventsimpl.automatevent.GetHerstellerMapEvent;
import eventsystem.automatsystem.AutomatEvent;
import eventsystem.automatsystem.AutomatEventHandler;
import parser.ReadCakeParser;

import java.util.Map;
import java.util.Scanner;

public class ReadCommand implements Command {
    @Override
    public void execute(Scanner scanner, Map<Mode, AutomatEventHandler> handlers) {
        while (true) {
            System.out.println("Was möchtest du ansehen? (:h hersteller / :k kuchen / :a  allergen /:x Read Modus verlassen)");
            String command = scanner.nextLine().trim();
            if (command.equalsIgnoreCase(":x")) {
                System.out.println("Read Modus verlassen.");
                break;
            }

            switch (command) {
                case ":h":
                    AutomatEvent hevent = new GetHerstellerMapEvent(new Console());
                    AutomatEventHandler hhandler = handlers.get(Mode.DISPLAY_HERSTELLER);
                    if (hhandler != null) hhandler.handle(hevent);
                    break;

                case ":k":
                    System.out.println("Filtertyp:");
                    ReadCakeParser parser = new ReadCakeParser();
                    parser.parse(scanner.nextLine());
                    AutomatEvent kevent = new DisplayCakeEvent(new Console(),parser.getFilterTyp());
                    AutomatEventHandler khandler = handlers.get(Mode.DISPLAY_CAKE);
                    if (khandler != null) khandler.handle(kevent);
                    break;

                case ":a":
                    System.out.println("enthalten(i)/nicht enthalten(e): ");
                    String vorhanden = scanner.nextLine();

                    AutomatEvent raevent;
                    if ("i".equalsIgnoreCase(vorhanden)) {
                        raevent = new DisplayAllergenEvent(new Console());
                        AutomatEventHandler displayAllergenHandler = handlers.get(Mode.DISPLAY_ALLERGEN);
                        if (displayAllergenHandler != null) {
                            displayAllergenHandler.handle(raevent);
                        }
                    } else if ("e".equalsIgnoreCase(vorhanden)) {
                        raevent = new DisplayKeineAllergenEvent(new Console());
                        AutomatEventHandler displayAllergenHandler = handlers.get(Mode.DISPLAY_KEINE_ALLERGEN);
                        if (displayAllergenHandler != null) {
                            displayAllergenHandler.handle(raevent);
                        }
                    } else {
                        System.out.println("Ungültige Eingabe. Bitte 'i' oder 'e' eingeben.");
                        break;
                    }
                    break;
                default:
                    System.out.println("Unbekannter Lesebefehl.");
            }
        }
    }
}
