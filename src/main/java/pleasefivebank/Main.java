package pleasefivebank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    private static Stage mainWindow = null;

    @Override
    public void start(Stage stage) throws IOException {
        mainWindow = stage;
        stage.setTitle("P5B");
        showPage("Entry-Page.fxml");
        stage.show();
    }


    public static void main(String[] args) {
        try {
            new Mongo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        launch(args);
    }

    public static void showPage(String fxmlFile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load());
        mainWindow.setScene(scene);
    }
    
}