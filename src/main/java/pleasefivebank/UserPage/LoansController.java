package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pleasefivebank.Main;
import pleasefivebank.Objects.Interest;
import pleasefivebank.Objects.Loan;
import pleasefivebank.Objects.User;

import java.io.IOException;

import static pleasefivebank.EntryPage.EntryPageController.user;
import static pleasefivebank.Main.mainWindow;
import static pleasefivebank.Main.showTransactionsPage;
import static pleasefivebank.Utilities.Utilities.trunc;

//Linus and Juan
public class LoansController {
    //this controller only includes a method to set up the user data
    //and methods to go from page to page
    //it also has a method to showcase the loans calculated information
    //and some error handling to avoid blank fields

    private boolean checkBoxBoo;


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
    Interest interest;

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
    public void calculate(ActionEvent event) throws Exception {
        errorHandling();
        interest = Loan.totalCosts(getAmountPerMonthPrompt(), getEstPayBackTimePrompt(), getLoanPeriodPrompt());
        double totalRent = interest.getTotal();
        double interestOnly = interest.getRentOnly();
        totalCost.setText(String.valueOf(trunc(totalRent)));
        rentTotal.setText(String.valueOf(trunc(interestOnly)));
    }

    //elisa
    @FXML
    void ContactUsButton(MouseEvent event) {
        try {
            Main.showContactUs(user);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
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
            Main.showDetails(user);
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
    void ToTransactions(ActionEvent event) {
        try {
            showTransactionsPage(user.getFirstName() + " "+ user.getLastName());
        } catch (IOException e) {
            e.printStackTrace();
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

    //juan and Linus(slight modifications by Ergi)
    @FXML
    void ToConfirmation(ActionEvent event) throws Exception {
        //errorHandling();

        if (checkBox.isSelected()) {
            try {
                errorHandling();
                double amountMonth = Double.parseDouble(amountPerMonth.getText());
                int payback = Integer.parseInt(EstPayBackTime.getText());
                int loanPeriod = Integer.parseInt(LoanPeriod.getText());
                double finalAmount = trunc(Loan.totalCosts(amountMonth,payback,loanPeriod).getTotal());
                //finalAmount is here 2 times because the amount left(when the constructor is created) is the same as the totalAmount
                Loan loanToSave = new Loan(amountMonth, user.getAccountIBAN(),loanPeriod,finalAmount,finalAmount,"Pending" );
                loanToSave.toDatabase();
                Main.showPage("LoanRequestSent.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            pleaseAccept.setText("Please accept the terms and conditions");
        }
    }
    private void errorHandling() throws  Exception{
        boolean flag = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        if (amountPerMonth.getText().isBlank()) {
            notBlank1.setText("Cannot be blank");
        } else {
            notBlank1.setText("");
            flag = true;
        }
        if (LoanPeriod.getText().isBlank()) {
            notBlank2.setText("Cannot be blank");
        } else {
            notBlank2.setText("");
            flag2 = true;
        }
        if (EstPayBackTime.getText().isBlank()) {
            notBlank3.setText("Cannot be blank");
        } else {
            notBlank3.setText("");
            flag3 = true;
        }
        double quantity = Double.parseDouble(amountPerMonth.getText());
        if (quantity > 13000 || quantity < 3000) {
            notBlank1.setText("Amount must be between 3000 and 13000 SEK");
        } else {
            notBlank1.setText("");
            flag4 = true;
        }
        if(!flag && !flag2 && !flag3 && !flag4){
            throw new Exception("All fields not entered");
        }
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

