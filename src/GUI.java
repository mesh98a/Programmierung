import domainpackage.Automat;
import gui.viewmodel.ViewModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// --module-path /Users/arturmeshalkin/Downloads/javafx-sdk-21.0.7/lib --add-modules javafx.controls,javafx.fxml
public class GUI extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("gui/view/view.fxml"));

        Parent root = loader.load();

        ViewModel controller = loader.getController();

        Automat automat = new Automat(10);
        controller.setAutomat(automat);

        Scene scene = new Scene(root);

        this.primaryStage = stage;
        this.primaryStage.setTitle("CakeApp");

        primaryStage.setScene(scene);
        primaryStage.show();

    }

}



