package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pleasefivebank.Main;

import java.io.IOException;

public class TransactionsController {

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
    void ToRequestMoney(ActionEvent event) {


    }

    @FXML
    void ToTransferMoney(ActionEvent event) {

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

}

