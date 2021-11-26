package pleasefivebank;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;
import pleasefivebank.Menus.EntryPage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("Entry-Page.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("P5B");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
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