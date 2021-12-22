package pleasefivebank.UserPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pleasefivebank.Main;
import pleasefivebank.Objects.Interest;
import pleasefivebank.Objects.Loan;

import java.io.IOException;
//Linus and Andreea
public class LoansController {

    Loan loan = new Loan();


    @FXML
    public TextField rentTotal;

    @FXML
    public TextField totalCost;

    @FXML
    public TextField amountPerMonth;


    @FXML
    private TextField LoanPeriod;

    @FXML
    private TextField totalAmount;


    @FXML
    private TextField EstPayBackTime;

    @FXML
    private Button calculateButton;

    double amountPerMonthPrompt = 0;
    int estPayBackTimePrompt = 0;
    Interest totalWRent = null;
    int loanPeriodPrompt = 0;

    @FXML
    public double getAmountPerMonthPrompt(){
        amountPerMonthPrompt = Double.parseDouble(amountPerMonth.getText());
        return amountPerMonthPrompt;
    }
    @FXML
    public int getEstPayBackTimePrompt(){
        estPayBackTimePrompt = Integer.parseInt(EstPayBackTime.getText());
        return estPayBackTimePrompt;
    }
    @FXML
    public int getLoanPeriodPrompt(){
        loanPeriodPrompt = Integer.parseInt(LoanPeriod.getText());
        return loanPeriodPrompt;
    }

    @FXML
    public void calculate(ActionEvent event){

        Interest interest = loan.totalCosts(getAmountPerMonthPrompt(), getEstPayBackTimePrompt(), getLoanPeriodPrompt());
        double totalWRent = interest.getTotal();
        double rentOnly = interest.getRentOnly();
        totalCost.setText(String.valueOf(totalWRent));
        rentTotal.setText(String.valueOf(rentOnly));
        totalAmount.setText(String.valueOf(totalWRent));
    }















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

