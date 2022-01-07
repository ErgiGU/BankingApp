package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.Transaction;
import pleasefivebank.Objects.User;

import java.io.IOException;
import java.util.ArrayList;

import static pleasefivebank.EntryPage.EntryPageController.user;
import static pleasefivebank.Main.mainWindow;

public class HomePageController {
    //this controller only includes a method to set up the user data
    //and methods to go from page to page


    @FXML
    private Text Amount1;

    @FXML
    private Text Amount2;

    @FXML
    private Text Amount3;

    @FXML
    private Text Amount4;

    @FXML
    private Text Date1;

    @FXML
    private Text Date2;

    @FXML
    private Text Date3;

    @FXML
    private Text Date4;

    @FXML
    private Text Name1;

    @FXML
    private Text Name2;

    @FXML
    private Text Name3;

    @FXML
    private Text Name4;

    @FXML
    private Button NameDisplay;


    @FXML
    private Text HelloMessage;


    @FXML
    private Text AccountNumberLabel;

    @FXML
    private Text BalanceLabel;
    //juan
    @FXML
    void ToNotifications(ActionEvent event) {
        try {
            Main.showNotificationsPage();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    //juan
    @FXML
    void ToCards(ActionEvent event) {
        try {
            Main.showCardsPage();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //juan
    @FXML
    void ToLoans(ActionEvent event) {
        try {
            Main.showLoansPage(user.getFirstName()+ " " + user.getLastName());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //Ergi
    @FXML
    void ToTransactions(ActionEvent event) {
        try {
            Main.showTransactionsPage(user.getFirstName()+ " " + user.getLastName());
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


    //juan
    @FXML
    public void Logout(ActionEvent event) {
        try {
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //juan
    public void setName(String name){
        NameDisplay.setText(name);
        HelloMessage.setText("Hello, "+ name+"!");
        float balance = Float.parseFloat(user.getBalance());
        balance -= balance % 0.001;
        BalanceLabel.setText("+" + balance +" SEK");
        AccountNumberLabel.setText(user.getAccountNr());
        ArrayList<Transaction> transactions = Mongo.getFourTransactions(user.getAccountIBAN());
        if (transactions.size() >=1){
            Transaction transaction = transactions.get(0);
            Name1.setText(transaction.getOtherPersonsName());
            Date1.setText(transaction.getDate());
            if (transaction.getReceiverIBAN().equals(user.getAccountIBAN())){
                Amount1.setText("+"+transaction.getQuantity()+" SEK");
            }
            else{
                Amount1.setText("-"+transaction.getQuantity()+" SEK");
            }
        }else{
            Name1.setText("No Transactions Made Yet");
            Date1.setText("");
            Amount1.setText("");
        }
        if (transactions.size() >=2){
            Transaction transaction = transactions.get(1);
            Name2.setText(transaction.getOtherPersonsName());
            Date2.setText(transaction.getDate());
            if (transaction.getReceiverIBAN().equals(user.getAccountIBAN())){
                Amount2.setText("+"+transaction.getQuantity()+" SEK");
            }
            else{
                Amount2.setText("-"+transaction.getQuantity()+" SEK");
            }
        }else{
            Name2.setText("");
            Date2.setText("");
            Amount2.setText("");
        }
        if (transactions.size() >=3){
            Transaction transaction = transactions.get(2);
            Name3.setText(transaction.getOtherPersonsName());
            Date3.setText(transaction.getDate());
            if (transaction.getReceiverIBAN().equals(user.getAccountIBAN())){
                Amount3.setText("+"+transaction.getQuantity()+" SEK");
            }
            else{
                Amount3.setText("-"+transaction.getQuantity()+" SEK");
            }
        }else{
            Name3.setText("");
            Date3.setText("");
            Amount3.setText("");
        }
        if (transactions.size() == 4){
            Transaction transaction = transactions.get(3);
            Name4.setText(transaction.getOtherPersonsName());
            Date4.setText(transaction.getDate());
            if (transaction.getReceiverIBAN().equals(user.getAccountIBAN())){
                Amount4.setText("+"+transaction.getQuantity()+" SEK");
            }
            else{
                Amount4.setText("-"+transaction.getQuantity()+" SEK");
            }
        }else{
            Name4.setText("");
            Date4.setText("");
            Amount4.setText("");
        }
    }
}

