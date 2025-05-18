# Übung 2
Erstellen Sie ein prototypisches Command Line Interface (CLI) für den Beleg mit 4 Befehlen (und ggf. ohne Modi): ein Befehl zum Einfügen eines (vordefinierten) Kuchens, ein Befehl zum Anzeigen der Kuchen, ein Befehl zum Ändern des Inspektionsdatums eines Kuchens und ein Befehl zum Entfernen eines Kuchens.

Weitere Informationen stehen im Anforderungsdokument unter der Überschrift CLI. Der Persistenzmodus sowie die Ausführung als Client bzw. Server sind nicht Teil dieser Übung.

## Abgabeanforderungen
Die Abgabe hat als zip-Datei zu erfolgen, die ein lauffähiges IntelliJ-IDEA-Projekt enthält. Dafür kann der Projektordner direkt in das zip eingepackt werden. Sie sollte die befüllte Checkliste im root des Projektes (neben der iml-Datei) enthalten in der der erreichte Stand bezüglich des Bewertungsschemas vermerkt ist.

Änderungen an der Checkliste sind grundsätzlich nicht zulässig. Davon ausgenommen ist das Befüllen der Checkboxen und ergänzende Anmerkungen die _kursiv gesetzt_ sind.

## Quellen
Zulässige Quellen sind suchmaschinen-indizierte Internetseiten. Werden mehr als drei zusammenhängende Anweisungen übernommen ist die Quelle in den Kommentaren anzugeben. Ausgeschlossen sind Quellen, die auch als Beleg oder Übungsaufgabe abgegeben werden oder wurden. Zulässig sind außerdem die über moodle bereitgestellten Materialien, diese können für die Übungsaufgaben und den Beleg ohne Quellenangabe verwendet werden.
Flüchtige Quellen, wie Sprachmodelle, sind per screen shot zu dokumentieren.

## Bewertung
1 Punkt für die Erfüllung des Pflichtteils

### Pflichtteil
- [x] Quellen angegeben
- [x] zip Archiv
- [x] IntelliJ-Projekt (kein Gradle, Maven o.ä.)
- [x] JUnit5 und Mockito als Testframeworks (soweit verwendet)
- [x] keine weiteren Bibliotheken außer JavaFX
- [x] keine Umlaute, Sonderzeichen, etc. in Datei- und Pfadnamen
- [x] kompilierbar
- [x] Trennung zwischen TestEvents- und Produktiv-Code
- [x] main-Methoden nur im default package des module belegProg3
- [x] ausführbar
- [x] Benutzeroberfläche und Geschäftslogik korrekt aufgeteilt (mindestens 2-Schichten-Architektur)
- [x] prototypisches CLI für CRUD _MainOhneModi_

### empfohlene Realisierungen als Vorbereitung auf den Beleg
werden überprüft (aber nicht bewertet), wenn hier in der vorgegebenen Reihenfolge als bearbeitet angegeben  
- [x] event-System für die Kommunikation vom CLI zur GL realisiert
- [x] ein Beobachter realisiert
- [x] event-System für die Kommunikation von der GL zum CLI realisiert 
- [ ] angemessene Aufzählungstypen verwendet
- [ ] zwei Tests für Beobachter realisiert
- [ ] zwei Tests für listener realisiert
- [ ] nach MVC strukturiert
- [ ] vollständiger Befehlssatz des CLIs

