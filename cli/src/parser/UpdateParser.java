package parser;

public class UpdateParser {
    private int fachnummer;

    public boolean parse(String input) {
        try {
            this.fachnummer = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Keine Zahl");
            return false;
        }
    }
    public int getFachnummer() {
        return fachnummer;
    }

}
