package pleasefivebank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import pleasefivebank.Menus.EntryPage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Entry-Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 765, 588);
        stage.setTitle("P5B");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            new Mongo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        EntryPage entrypage = new EntryPage();
        EntryPage.createUser();
        launch();
    }
}