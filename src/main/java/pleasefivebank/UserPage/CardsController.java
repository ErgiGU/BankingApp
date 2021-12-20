package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pleasefivebank.Main;

import java.io.IOException;

public class CardsController {

    @FXML
    void ToDetails(ActionEvent event) {
        try {
            Main.showPage("AccountDetails.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void ToHome(ActionEvent event) {
        try {
            Main.showPage("UserHomePage.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void ToLoans(ActionEvent event) {
        try {
            Main.showPage("StudentLoans.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void ToTransactions(ActionEvent event) {
        try {
            Main.showPage("Transactions.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
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




