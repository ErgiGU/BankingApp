package pleasefivebank.UserPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pleasefivebank.Main;
import pleasefivebank.Objects.Loan;

import java.io.IOException;

public class LoansController {

    Loan loan = new Loan();

    @FXML
    public TextField rentTotal;

    @FXML
    public TextField totalCost;

    @FXML
    public TextField totalAmount;

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
    void Accept(ActionEvent event) { loan.changeCheckBox();}

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

