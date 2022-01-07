package pleasefivebank.UserPage;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.Transaction;
import pleasefivebank.Objects.User;

import java.io.IOException;

import static pleasefivebank.EntryPage.EntryPageController.user;
import static pleasefivebank.Main.mainWindow;

public class TransactionsController {

    //this controller corresponds to the page where the user can view transactions
    //there are methods to connect pages and to save and store the transaction requests

    @FXML
    private TableColumn<?, ?> IBANColumn;

    @FXML
    private Button NameLabel;

    @FXML
    private TableColumn<?, ?> amountColumn;

    @FXML
    private TableColumn<?, ?> conceptColumn;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> receiverColumn;

    @FXML
    private Text showBalance;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableView<Transaction> tableView;
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

    //Elisa
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
    void ToHome(ActionEvent event) {
        try {
            Main.showLoginPage(user.getFirstName()+ " " + user.getLastName());
        } catch (IOException ex) {
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
    //juan
    @FXML
    void ToRequestMoney(ActionEvent event) {
        try {
            Main.showRequestTransactionsPage(user.getFirstName()+ " " + user.getLastName());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //juan
    @FXML
    void ToTransferMoney(ActionEvent event) {
        try {
            Main.showTransferMoneyPage(user.getFirstName()+ " " + user.getLastName());
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
    @FXML
    void ToNotifications(ActionEvent event) {
        try {
            Main.showNotificationsPage();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    public void setName(String name){
        NameLabel.setText(name);
    }

    //juan and carlotta
    //method to set up the table
    public void setupTable(){
        dateColumn = new TableColumn<Transaction, String>();
        dateColumn.setText("Date");

        receiverColumn = new TableColumn<Transaction, String>();
        receiverColumn.setText("Receiver");

        IBANColumn = new TableColumn<Transaction, String>();
        IBANColumn.setText("Receiver IBAN");

        amountColumn = new TableColumn<Transaction, String>();
        amountColumn.setText("Amount");

        conceptColumn = new TableColumn<Transaction, String>();
        conceptColumn.setText("Message");

        statusColumn = new TableColumn<Transaction, String>();
        statusColumn.setText("Status");

        tableView.getItems().clear();
        ObservableList<Transaction> transactions = Mongo.getAllTransactions(user.getAccountIBAN());
        tableView.getItems().clear();
        tableView.setItems(transactions);
        tableView.refresh();
        NameLabel.setText(user.getFirstName()+ " " + user.getLastName());
        showBalance.setText("+" +user.getBalance()+ " SEK");
    }
}

