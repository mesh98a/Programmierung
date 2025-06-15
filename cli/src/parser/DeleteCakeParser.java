package parser;

public class DeleteCakeParser {
    private int fachnummer;

    public boolean parse(String input) {
        try {
            this.fachnummer = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input is not an integer");
            return false;
        }
    }
    public int getFachnummer() {
        return fachnummer;
    }


}
