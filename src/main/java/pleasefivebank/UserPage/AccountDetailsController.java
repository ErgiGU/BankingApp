package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pleasefivebank.Main;

import java.io.IOException;

public class AccountDetailsController {

    @FXML
    void EditDetails(ActionEvent event) {

    }

    @FXML
    void ToUserHomePage(ActionEvent event) {

    }

    @FXML
    public void Logout(ActionEvent event) {
        //save the activity
        try {
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
