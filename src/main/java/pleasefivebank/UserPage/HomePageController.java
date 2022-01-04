package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.Transaction;
import pleasefivebank.Objects.User;

import java.io.IOException;
import java.util.ArrayList;

import static pleasefivebank.EntryPage.EntryPageController.user;
import static pleasefivebank.Main.mainWindow;

public class HomePageController { //need the attributes from EntryPage controller

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
            Main.showCardsPage(user.getFirstName()+ " " + user.getLastName());
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
    //juan and Carlotta
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
    //elisa
    @FXML
    void ToContactUs(ActionEvent event) {
        try {
            Main.showPage("ContactPage.fxml");;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    //juan
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
    //juan
    public void setName(String name){
        NameDisplay.setText(name);
        HelloMessage.setText("Hello, "+ name+"!");
        float balance = Float.parseFloat(user.getBalance());
        balance -= balance % 0.001;
        BalanceLabel.setText("+" + balance +" SEK");
        AccountNumberLabel.setText(user.getAccountNr());
        ArrayList<Transaction> transactions = Mongo.getFourTransactions(user.getAccountIBAN());
        if (transactions.size() == 4){
            Transaction transaction1 = transactions.get(0);
            Name1.setText(transaction1.getOtherPersonsName());
        }

    }
}

