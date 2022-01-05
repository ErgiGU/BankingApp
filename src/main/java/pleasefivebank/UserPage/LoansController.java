package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pleasefivebank.Main;
import pleasefivebank.Objects.Interest;
import pleasefivebank.Objects.Loan;
import pleasefivebank.Objects.User;

import java.io.IOException;

import static pleasefivebank.EntryPage.EntryPageController.user;
import static pleasefivebank.Main.mainWindow;
import static pleasefivebank.Utilities.Utilities.trunc;

//Linus and Juan
public class LoansController {
    //this controller only includes a method to set up the user data
    //and methods to go from page to page
    //it also has a method to showcase the loans calculated information
    //and some error handling to avoid blank fields


    private boolean checkBoxBoo;
    Loan loan = new Loan();

    @FXML
    private Button NameLabel;

    @FXML
    public Label rentTotal;

    @FXML
    public Label totalCost;

    @FXML
    public TextField amountPerMonth;


    @FXML
    private TextField LoanPeriod;

    @FXML
    private Label totalAmount;


    @FXML
    private TextField EstPayBackTime;

    @FXML
    private CheckBox checkBox;

    @FXML
    private Label pleaseAccept;

    @FXML
    private Label notBlank1;

    @FXML
    private Label notBlank2;

    @FXML
    private Label notBlank3;


    double amountPerMonthPrompt = 0;
    int estPayBackTimePrompt = 0;
    int loanPeriodPrompt = 0;

    @FXML
    public double getAmountPerMonthPrompt() {
        amountPerMonthPrompt = Double.parseDouble(amountPerMonth.getText());
        return amountPerMonthPrompt;
    }

    @FXML
    public int getEstPayBackTimePrompt() {
        estPayBackTimePrompt = Integer.parseInt(EstPayBackTime.getText());
        return estPayBackTimePrompt;
    }

    @FXML
    public int getLoanPeriodPrompt() {
        loanPeriodPrompt = Integer.parseInt(LoanPeriod.getText());
        return loanPeriodPrompt;
    }

    //Linus
    @FXML
    public void calculate(ActionEvent event) {
        errorHandling();
        Interest interest = loan.totalCosts(getAmountPerMonthPrompt(), getEstPayBackTimePrompt(), getLoanPeriodPrompt());
        double totalWRent = interest.getTotal();
        double rentOnly = interest.getRentOnly();
        totalCost.setText(String.valueOf(trunc(totalWRent)));
        rentTotal.setText(String.valueOf(trunc(rentOnly)));
    }


    @FXML
    void ToCards(ActionEvent event) {
        try {
            Main.showCardsPage(user.getFirstName()+ " " + user.getLastName());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //Juan and Ergi
    @FXML
    void ToDetails(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AccountDetails.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            AccountDetailsController accountDetailsController = fxmlLoader.getController();
            User currentUser = user;
            if(!currentUser.equals(null)) {
                accountDetailsController.setInformation(user);
                mainWindow.setScene(scene);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void ToHome(ActionEvent event) {
        try {
            Main.showLoginPage(user.getFirstName()+ " " + user.getLastName());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void Accept(ActionEvent event) {
        loan.changeCheckBox();
        checkBoxBoo = true;
    }

    @FXML
    void ToTransactions(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Transactions.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            TransactionsController transactionsController = fxmlLoader.getController();
            transactionsController.setupTable();
            mainWindow.setScene(scene);
            //Main.showTransactionsPage(user.getFirstName()+ " " + user.getLastName());
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //juan and Linus
    @FXML
    void ToConfirmation(ActionEvent event) {
        if (errorHandling() && checkBoxBoo){
            try {
                Loan loan = new Loan(amountPerMonth.getText(), user.getAccountIBAN(), EstPayBackTime.getText(), LoanPeriod.getText());
                Main.showLoanConfirmation();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else{
        pleaseAccept.setText("Please accept the terms and conditions");
        }
    }
    private boolean errorHandling(){
        if (amountPerMonth.getText() == "") {
            notBlank1.setText("Cannot be blank");
            return false;
        }else{notBlank1.setText("");}
        if (LoanPeriod.getText() == "") {
            notBlank2.setText("Cannot be blank");
            return false;
        }else{notBlank2.setText("");}
        if (EstPayBackTime.getText() == "") {
            notBlank3.setText("Cannot be blank");
            return false;
        }else{notBlank3.setText("");}
        return true;
    }
    //juan and lotti
    public void setName(){
        NameLabel.setText(user.getFirstName()+ " " + user.getLastName());
    }


    @FXML
    void ToNotifications(ActionEvent event) {
        try {
            Main.showNotificationsPage();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

