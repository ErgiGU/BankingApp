package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pleasefivebank.Main;

import java.io.IOException;

public class HomePageController {

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

}

