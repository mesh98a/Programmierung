# Beleg SS 25 PZR1 (93)
Checkboxen befüllen und _kursiv_ gesetzten Text durch entsprechende Angaben ersetzten.
Bei keiner Angabe wird nur Entwurf, Testqualität, Testabdeckung GL, Fehlerfreiheit und Basisfunktionalität bewertet.
Die Zahl in der Klammer sind die jeweiligen Punkte für die Bewertung.
Die empfohlenen Realisierungen zum Bestehen der Prüfung sind **fett** gesetzt.
Ergänzende Anmerkungen bitte immer _kursiv_ setzen. Andere Änderungen sind nicht zulässig.

## Vorrausetzungen für das Bestehen
- [x] Quellen angegeben
- [x] keine vorgetäuschte Funktionalität (inkl. leere Tests)
- [x] zip Archiv mit dem Projekt im root
- [x] IntelliJ-Projekt (kein Gradle, Maven o.ä.)
- [x] keine weiteren Bibliotheken außer JUnit5, Mockito und JavaFX
- [x] keine Umlaute, Sonderzeichen, etc. in Datei- und Pfadnamen
- [x] Trennung zwischen Test- und Produktiv-Code
- [x] kompilierbar
- [x] geforderte main-Methoden nur im default package des module belegProg3, nicht in den sub modules
  - [x] CLI
  - [x] alternatives CLI
  - [x] je eine für jede Simulation
  - [x] GUI
  - [x] Server
  - [x] CLI und GUI parallel (zusätzliche Anforderungen)

## Entwurf (10)
- [x] **Benennung** (2)
- [x] **Zuständigkeit** (2)
- [x] **Paketierung** (2)
- [x] **Schichtenaufteilung (via modules)** (2)
- [x] **nur absolut notwendige down casts** (1)
- [ ] keine Duplikate (außer in den Tests und setups) (1)

## Tests (28)
- [ ] **Testqualität** (7)
- [ ] **Testabdeckung GL inkl. Abhängigkeiten (100% additiv)** (7) _98_
- [ ] Testabdeckung Rest (jeweils 100% additiv) (6)
  - [x] Einfügen von Hersteller*innen über das CLI _Console InsertCommand InsertHerstellerEvent InsertHerstellerListener_
  - [ ] Anzeigen von Hersteller*innen über das CLI _getestete Klassen angeben_
  - [x] ein Beobachter bzw. dessen alternative Implementierung _AutomatCapacityObserver.java_ 
  - [ ] deterministische Funktionalität der Simulationen _getestete Klassen angeben_
  - [x] Speichern via JOS oder JBP _AutomatSerializer für speichern JOS_ 
  - [x] Laden via JOS oder JBP _AutomatSerializer für laden JOS und Automat für die copyFrom Methode_
- [x] **mindestens 5 Unittests, die Mockito verwenden** (4)
- [x] mindestens 4 Spy- / Verhaltens-Tests (3)
- [x] **keine unbeabsichtigt fehlschlagenden Test** (1)

## Fehlerfreiheit (10)
- [x] **Kapselung** (5)
- [x] **keine Ablauffehler** (5)

## Basisfunktionalität (12)
- [x] **CRUD** (2)
- [x] **CLI** (2)
  * Syntax gemäß Anforderungen
- [x] **Simulation** (2)
  * ohne race conditions
- [x] **GUI** (2)
- [x] **I/O** (2)
  * in CLI oder GUI integriert
- [x] **Net** (2)

## Funktionalität (23)
- [x] vollständige GL (2)
- [x] threadsichere GL (1)
- [x] vollständiges CLI (1)
- [x] alternatives CLI (1)
  * _angeben welche Funktionalität im alternativen CLI deaktiviert_
- [x] ausdifferenziertes event-System mit mindestens 3 events (2)
- [x] observer (2)
- [x] bzgl. den Anforderungen angemessene Typen der collections (2)
- [x] Simulation 2 (1)
- [ ] Simulation 3 (1)
- [x] skalierbare GUI (1)
- [x] vollständige GUI (1)
- [x] FXML verwendet (1)
- [x] Änderung des Fachnummer mittels drag&drop (1)
- [ ] Einfügen von Kuchen via GUI erfolgt nebenläufig (1)
- [x] sowohl JBP als auch JOS (2)
- [ ] sowohl TCP als auch UDP (1)
- [x] Server unterstützt konkurierende Clients für TCP oder UDP (2)

## zusätzliche Anforderungen (10)
- [x] parallele Ausführung CLI und GUI (2)
- [x] Änderungen in der GUI direkt sichtbar (4)
- [x] CLI und GUI unabhängig (4)

