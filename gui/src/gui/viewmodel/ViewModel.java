package gui.viewmodel;

import domainpackage.*;
import domainpackage.dto.AutomatDTO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import kuchen.Allergen;
import kuchen.KuchenTyp;
import kuchen.Kuchenprodukt;
import io.AutomatSerializer;
import io.AutomatXMLSerializer;
import verwaltung.Hersteller;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;

public class ViewModel {

    private Automat automat;

    @FXML
    private TableView<AllergenStatistik> table_allergen;

    @FXML
    private TableColumn<?, ?> col_allergen;

    @FXML
    private TableView<CakeStatistik> table_cake;

    @FXML
    private TableColumn<CakeStatistik, Integer> col_cake_fachnummer;

    @FXML
    private TableColumn<CakeStatistik, Duration> col_cake_haltbarkeit;

    @FXML
    private TableColumn<CakeStatistik, String> col_cake_hersteller;

    @FXML
    private TableColumn<CakeStatistik, Date> col_cake_inspect;

    @FXML
    private TableView<HerstellerStatistik> table_hersteller;

    @FXML
    private TableColumn<HerstellerStatistik, String> col_hersteller;

    @FXML
    private TableColumn<HerstellerStatistik, Integer> col_number_of_cakes;

    @FXML
    private TextField input_hersteller;

    @FXML
    private ComboBox<KuchenTyp> input_kuchentyp;

    @FXML
    private ComboBox<String> input_typ;

    @FXML
    private TextField input_preis;

    @FXML
    private TextField input_naehrwert;

    @FXML
    private TextField input_haltbarkeit;

    @FXML
    private ListView<Allergen> input_allergene;

    @FXML
    private TextField input_extras;

    @FXML
    private TextField input_fachnummer;

    @FXML
    public Button insert_button;

    @FXML
    public Button read_button;

    @FXML
    public Button update_button;

    @FXML
    public Button delete_button;

    @FXML
    public Button clear_button;

    @FXML
    public Button save_button;

    @FXML
    public Button load_button;

    private ObservableList<HerstellerStatistik> herstellerListe = FXCollections.observableArrayList();
    private ObservableList<CakeStatistik> cakeListe = FXCollections.observableArrayList();
    private ObservableList<AllergenStatistik> allergenListe = FXCollections.observableArrayList();

    @FXML
    private void insert() {
        String name = input_hersteller.getText().trim();
        boolean herstellerAusgefuellt = !name.isEmpty();
        boolean kuchenAusgefuellt =
                input_kuchentyp.getValue() != null ||
                        !input_preis.getText().isEmpty() ||
                        !input_naehrwert.getText().isEmpty() ||
                        !input_haltbarkeit.getText().isEmpty() ||
                        !input_extras.getText().isEmpty() ||
                        (input_allergene.getSelectionModel().getSelectedItems() != null &&
                                !input_allergene.getSelectionModel().getSelectedItems().isEmpty());

        if (herstellerAusgefuellt && !kuchenAusgefuellt) {
            insertHersteller();
        } else if (herstellerAusgefuellt && kuchenAusgefuellt) {
            Platform.runLater(() -> {
                insertKuchen();
            });
        } else {
            showAlert(Alert.AlertType.ERROR, "Eingabefehler", "Bitte mindestens den Herstellernamen eingeben.");
        }
    }

    private void insertHersteller() {
        String name = input_hersteller.getText().trim();

        boolean success = automat.insertHersteller(name);
        if (success) {
            input_hersteller.clear();
        }
    }

    private void insertKuchen() {
        try {
            String herstellerName = input_hersteller.getText();
            KuchenTyp kuchentyp = input_kuchentyp.getValue();
            BigDecimal preis = new BigDecimal(input_preis.getText());
            int naehrwert = Integer.parseInt(input_naehrwert.getText());
            int haltbarkeitStunden = Integer.parseInt(input_haltbarkeit.getText());
            Duration haltbarkeit = Duration.ofHours(haltbarkeitStunden);
            Collection<Allergen> allergen = new HashSet<>(input_allergene.getSelectionModel().getSelectedItems());
            String extras = input_extras.getText();
            List<String> extrasList = Arrays.asList(extras.split(" "));

            Hersteller hersteller = new HerstellerImpl(herstellerName);

            AbstractCake cake = CakeFactory.createCake(kuchentyp, hersteller, preis, naehrwert, haltbarkeit, allergen, extrasList);

            boolean result = automat.insertCake(cake);
            if (result) {
                clearFields();
            }

        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Eingabefehler", "Bitte gültige Zahlen für Haltbarkeit, Nährwert und Preis eingeben.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Fehler beim Einfügen des Kuchens: " + e.getMessage());

        }
    }

    @FXML
    private void update() {
        boolean flag = !input_fachnummer.getText().isEmpty();
        if (flag) {
            try {
                int fachnummer = Integer.parseInt(input_fachnummer.getText());

                automat.inspectCake(fachnummer);
                input_fachnummer.clear();
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Eingabefehler", "Bitte eine Zahl eingeben");
            }
        }
    }

    @FXML
    private void read() {
        KuchenTyp kuchenTyp = input_kuchentyp.getValue();
        showKuchen(kuchenTyp);
        input_kuchentyp.getSelectionModel().clearSelection();

    }

    @FXML
    private void delete() {
        boolean herstellerAusgefuellt = !input_hersteller.getText().isEmpty();
        boolean fachnummerAusgefuellt = !input_fachnummer.getText().isEmpty();
        if (herstellerAusgefuellt && !fachnummerAusgefuellt) {
            deleteHersteller();
        } else if (!herstellerAusgefuellt && fachnummerAusgefuellt) {
            deleteCake();
        } else {
            showAlert(Alert.AlertType.ERROR, "Eingabefehler", "Hersteller oder Fachnummer eingeben.");
        }
    }

    private void deleteCake() {
        boolean flag = !input_fachnummer.getText().isEmpty();
        if (flag) {
            try {
                int fachnummer = Integer.parseInt(input_fachnummer.getText());

                automat.deleteCake(fachnummer);
                input_fachnummer.clear();
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Eingabefehler", "Bitte eine Zahl eingeben");
            }
        }
    }

    private void deleteHersteller() {
        boolean flag = !input_hersteller.getText().isEmpty();
        if (flag) {
            try {
                String herstellerName = input_hersteller.getText();

                automat.deleteHersteller(herstellerName);
                input_hersteller.clear();
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Eingabefehler", "Kein Hersteller gefunden");
            }
        }
    }

    @FXML
    private void save() {
        String value = input_typ.getValue();

        if (value.equalsIgnoreCase("JOS")) {
            AutomatSerializer.serialize("automat.ser", automat);
        } else if (value.equalsIgnoreCase("JBP")) {
            AutomatDTO dto = automat.createDTO();
            AutomatXMLSerializer.save("automat.xml", dto);
        }
    }

    @FXML
    private void load() {
        String value = input_typ.getValue();

        if (value.equalsIgnoreCase("JOS")) {
            Automat loadedAutomat = AutomatSerializer.deserialize("automat.ser");
            automat.copyFrom(loadedAutomat);
        } else if (value.equalsIgnoreCase("JBP")) {
            AutomatDTO loadedAutomat = AutomatXMLSerializer.load("automat.xml");
            this.automat.restoreFromDTO(loadedAutomat);
        }

    }

    public void showHersteller() {
        herstellerListe.clear();
        Map<Hersteller, Integer> herstellerMap = automat.getHerstellerMap();
        for (Map.Entry<Hersteller, Integer> entry : herstellerMap.entrySet()) {
            herstellerListe.add(new HerstellerStatistik(entry.getKey().getName(), entry.getValue()));
        }
    }

    public void showKuchen(KuchenTyp kuchenTyp) {
        List<Kuchenprodukt> cakeList;
        if (kuchenTyp == null) {
            cakeList = automat.getListCake();
        } else {
            cakeList = automat.getListCake(kuchenTyp);
        }
        cakeListe.clear();
        for (Kuchenprodukt cake : cakeList) {
            Integer fachnummer = cake.getFachnummer();
            String herstellerName = cake.getHersteller().getName();
            Date inspektionsdatum = cake.getInspektionsdatum();
            long haltbarkeit = cake.getHaltbarkeit().getSeconds();
            cakeListe.add(new CakeStatistik(fachnummer, herstellerName, inspektionsdatum, haltbarkeit));
        }
    }

    public void showAllergen() {
        Set<Allergen> allergen = automat.getAllergen(true);
        allergenListe.clear();
        for (Allergen allergene : allergen) {
            allergenListe.add(new AllergenStatistik(allergene));
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void clearFields() {
        input_hersteller.clear();
        input_extras.clear();
        input_fachnummer.clear();
        input_haltbarkeit.clear();
        input_naehrwert.clear();
        input_preis.clear();
        input_kuchentyp.getSelectionModel().clearSelection();
        input_allergene.getSelectionModel().clearSelection();
        input_typ.getSelectionModel().clearSelection();
    }

    public void comboAllergen() {
        Set<Allergen> allAllergene = EnumSet.allOf(Allergen.class);
        ObservableList<Allergen> items = FXCollections.observableArrayList(allAllergene);
        input_allergene.setItems(items);
        input_allergene.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void comboPers() {
        List<String> liste = new ArrayList<>();
        liste.add("JOS");
        liste.add("JBP");
        ObservableList items = FXCollections.observableList(liste);
        input_typ.setItems(items);
    }


    public void comboKuchentyp() {
        Set<KuchenTyp> kuchenTyps = EnumSet.allOf(KuchenTyp.class);
        List<KuchenTyp> kuchenList = new ArrayList<>(kuchenTyps);
        ObservableList items = FXCollections.observableList(kuchenList);
        input_kuchentyp.setItems(items);
    }

    public void setAutomat(Automat automat) {
        this.automat = automat;
        automat.registerObserver(() -> {
            // Quelle von ChatGPT gui.png
            Platform.runLater(() -> {
                showKuchen(null);
                showAllergen();
                showHersteller();
            });
        });
    }

    private void setupDragAndDrop() {
        table_cake.setRowFactory(tv -> {
            TableRow<CakeStatistik> row = new TableRow<>();

            row.setOnDragDetected(event -> {
                if (!row.isEmpty()) {
                    Integer draggedFachnummer = row.getItem().getFachnummer();
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(draggedFachnummer.toString());
                    db.setContent(content);
                    event.consume();
                }
            });

            row.setOnDragOver(event -> {
                if (event.getGestureSource() != row && event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            });

            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                boolean success = false;

                if (db.hasString()) {
                    try {
                        int draggedFachnummer = Integer.parseInt(db.getString());
                        CakeStatistik dropTarget = row.getItem();

                        if (dropTarget != null) {
                            int targetFachnummer = dropTarget.getFachnummer();

                            boolean swapSuccessful = automat.swapFachnummern(draggedFachnummer, targetFachnummer);
                            if (swapSuccessful) {
                                success = true;
                            }
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                event.setDropCompleted(success);
                event.consume();
            });

            return row;
        });

    }


    @FXML
    public void initialize() {
        comboPers();
        comboKuchentyp();
        comboAllergen();
        setupDragAndDrop();

        col_hersteller.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        col_number_of_cakes.setCellValueFactory(cellData -> cellData.getValue().number_of_cakesProperty().asObject());
        table_hersteller.setItems(herstellerListe);

        col_cake_fachnummer.setCellValueFactory(new PropertyValueFactory<>("fachnummer"));
        col_cake_hersteller.setCellValueFactory(new PropertyValueFactory<>("hersteller"));
        col_cake_inspect.setCellValueFactory(new PropertyValueFactory<>("inspektionsdatum"));
        col_cake_haltbarkeit.setCellValueFactory(new PropertyValueFactory<>("haltbarkeit"));
        table_cake.setItems(cakeListe);

        col_allergen.setCellValueFactory(new PropertyValueFactory<>("allergen"));
        table_allergen.setItems(allergenListe);

    }
}
