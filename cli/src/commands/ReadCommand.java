package commands;

import eventsimpl.automatevent.Mode;
import eventsimpl.automatevent.DisplayAllergenEvent;
import eventsimpl.automatevent.DisplayCakeEvent;
import eventsimpl.automatevent.GetHerstellerMapEvent;
import eventsystem.automatsystem.AutomatEvent;
import eventsystem.automatsystem.AutomatEventHandler;
import parser.ReadCakeParser;

import java.util.Map;
import java.util.Scanner;

public class ReadCommand implements Command {
    @Override
    public String execute(Scanner scanner, Map<Mode, AutomatEventHandler> handlers, Map<String, Command> commands) {
        while (true) {
            System.out.println("Was möchtest du ansehen? hersteller / kuchen / allergene /");
            String command = scanner.nextLine().trim();
            if (command.startsWith(":") && commands.containsKey(command)) {
                System.out.println("Moduswechsel auf " + command);
                return command;
            }
            ReadCakeParser parser = new ReadCakeParser();
            if (parser.parse(command)) {

                switch (parser.getMode()) {
                    case "hersteller":
                        readHersteller(handlers);
                        break;

                    case "kuchen":
                        AutomatEvent kuchenEvent = new DisplayCakeEvent(this, parser.getFilterTyp());
                        AutomatEventHandler khandler = handlers.get(Mode.DISPLAY_CAKE);
                        if (khandler != null) khandler.handle(kuchenEvent);
                        break;

                    case "allergene":
                        String vorhanden = parser.getFilterTyp();
                        boolean enthalten;

                        if ("i".equalsIgnoreCase(vorhanden)) {
                            enthalten = true;
                        } else if ("e".equalsIgnoreCase(vorhanden)) {
                            enthalten = false;
                        } else {
                            System.out.println("Ungültige Eingabe. Bitte 'i' oder 'e' eingeben.");
                            break;
                        }
                        AutomatEvent allergenEvent = new DisplayAllergenEvent(this, enthalten);
                        AutomatEventHandler displayAllergenHandler = handlers.get(Mode.DISPLAY_ALLERGEN);
                        if (displayAllergenHandler != null) {
                            displayAllergenHandler.handle(allergenEvent);
                        }
                        break;
                    default:
                        System.out.println("Unbekannter Lesebefehl.");
                }
            }
        }
    }

    public void readHersteller(Map<Mode, AutomatEventHandler> handlers) {
        AutomatEvent herstellerEvent = new GetHerstellerMapEvent(this);
        AutomatEventHandler hhandler = handlers.get(Mode.DISPLAY_HERSTELLER);
        if (hhandler != null) hhandler.handle(herstellerEvent);
    }
}
