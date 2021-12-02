package pleasefivebank.EntryPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pleasefivebank.Main;

import java.io.IOException;

public class ForgotPasswordController {

    @FXML
    private TextField Email;

    @FXML
    private TextField Username;

    @FXML
    protected void NewPasswordPressed(ActionEvent event){
        String email = Email.getText();
        String userName = Username.getText();


    }
    @FXML
    protected void BackToEntryPage() {
        try {
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

