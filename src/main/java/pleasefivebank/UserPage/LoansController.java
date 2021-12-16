package pleasefivebank.UserPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pleasefivebank.Main;

import java.io.IOException;

public class LoansController {

    @FXML
    private TextField AmountPerMonth;

    @FXML
    private TextField LoanPeriod;

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

