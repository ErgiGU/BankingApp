package pleasefivebank.EntryPage;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import pleasefivebank.Main;

import java.io.IOException;


public class SuccessfulRegistrationController {

    @FXML
    void ToEntryPage(MouseEvent event) {
        try {
            Main.showPage("Entry-page.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
