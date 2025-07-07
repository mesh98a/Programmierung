package parser;

public class PersistenceParser{
    private String action;
    private String mode;

    public boolean parse(String input) {
        String[] parts = input.trim().split(" ");
        try {
            this.action = parts[0].toLowerCase();
            this.mode = parts[1].toUpperCase();
            return true;
        } catch (Exception e) {
            System.out.println("Fehler: Ungültige Anzahl an Tokens (" + parts.length + ")");
            return false;
        }
    }
    public String getAction() {
        return action;
    }
    public String getMode() {
        return mode;
    }
}
