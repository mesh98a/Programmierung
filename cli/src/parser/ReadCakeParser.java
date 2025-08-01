package parser;

public class ReadCakeParser {
    private String mode;
    private String filterTyp;

    public boolean parse(String input) {
        String[] parts = input.trim().split(" ");
        try {
            this.mode = parts[0];
            if (parts.length > 1) {
                this.filterTyp = parts[1];
            }
            return true;
        } catch (Exception e) {
            System.out.println("Fehler beim Parsen: " + e);
            return false;
        }
    }

    public String getFilterTyp() {
        return filterTyp;
    }

    public String getMode() {
        return mode;
    }
}
