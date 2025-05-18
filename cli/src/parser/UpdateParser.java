package parser;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UpdateParser {
    public final int fachnummer;
    public final Date inspectdate;

    public UpdateParser(String input) {
        String[] parts = input.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(parts[1], formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        this.fachnummer = Integer.parseInt(parts[0]);
        this.inspectdate = date;
    }

}
