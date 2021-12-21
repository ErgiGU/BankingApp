package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import pleasefivebank.Main;
import pleasefivebank.EntryPage.EntryPageController;

import java.io.IOException;

public class HomePageController { //need the attributes from EntryPage controller


    @FXML
    void ToCards(ActionEvent event) {
        try {
            Main.showPage("CardsPage.fxml");
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
    void ToDetails(ActionEvent event) {
        try {
            Main.showPage("AccountDetails.fxml");
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

