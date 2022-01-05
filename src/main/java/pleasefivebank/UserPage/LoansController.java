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
import static pleasefivebank.Utillities.Utillities.trunc;

//Linus and Andreea
public class LoansController {


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
    private Button calculateButton;

    @FXML
    private Button BackButtonAfterLoan;

    @FXML
    private Button applyButton;
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

    @FXML
    public void calculate(ActionEvent event) throws Exception {
        errorHandling();
        Interest interest = loan.totalCosts(getAmountPerMonthPrompt(), getEstPayBackTimePrompt(), getLoanPeriodPrompt());
        double totalWRent = interest.getTotal();
        double rentOnly = interest.getRentOnly();
        totalCost.setText(String.valueOf(trunc(totalWRent)));
        rentTotal.setText(String.valueOf(trunc(rentOnly)));
        totalAmount.setText(String.valueOf(trunc(totalWRent)));
    }


    @FXML
    void ToCards(ActionEvent event) {
        try {
            Main.showPage("CardsPage.fxml");
        } catch (IOException ex) {
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
            Main.showPage("UserHomePage.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void Accept(ActionEvent event) {

        loan.changeCheckBox();
        if (checkBoxBoo == true){ checkBoxBoo = false;
        }else {
            checkBoxBoo = true;
        }
    }


    @FXML
    void ToTransactions(ActionEvent event) {
        try {
            Main.showPage("Transactions.fxml");
        } catch (IOException ex) {
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

    @FXML
    void ToLoans(ActionEvent event) {
        try {
            Main.showPage("StudentLoans.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void ToConfirmation(ActionEvent event) throws Exception {
      errorHandling();

        if (checkBoxBoo == true ) {
            try {
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
        if (amountPerMonth.getText() == "") {
            notBlank1.setText("Cannot be blank");
        } else {
            notBlank1.setText("");
            flag = true;
        }
        if (LoanPeriod.getText() == "") {
            notBlank2.setText("Cannot be blank");
        } else {
            notBlank2.setText("");
            flag2 = true;
        }
        if (EstPayBackTime.getText() == "") {
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
        if(flag == false || flag2 == false || flag3 == false || flag4 == false){
            throw new Exception("All fields not entered");
        }
    }
    //juan and lotti
    public void setName(String name){
        NameLabel.setText(user.getFirstName()+ " " + user.getLastName());
    }
}

