package pleasefivebank.UserPage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.Transaction;
import pleasefivebank.Objects.User;

import java.io.IOException;

import static pleasefivebank.EntryPage.EntryPageController.user;
import static pleasefivebank.Main.mainWindow;

public class TransactionsController {
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
    private TextField showBalance;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableView<Transaction> tableView;
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

    @FXML
    void showTransactions(ActionEvent event) {
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
    public void setName(String name){
        NameLabel.setText(name);
    }

    //juan and carlotta
    public void setupTable(){
        dateColumn = new TableColumn<Transaction, String>();
        dateColumn.setText("Date");

        receiverColumn = new TableColumn<Transaction, String>();
        receiverColumn.setText("Receiver");

        IBANColumn = new TableColumn<Transaction, String>();
        IBANColumn.setText("Receiver IBAN");

        amountColumn = new TableColumn<Transaction, String>();
        amountColumn.setText("quantity");

        conceptColumn = new TableColumn<Transaction, String>();
        conceptColumn.setText("Concept");

        statusColumn = new TableColumn<Transaction, String>();
        statusColumn.setText("Status");

        ObservableList<Transaction> transactions = Mongo.getAllTransactions(user.getAccountIBAN(),
                "actualTransactions");
        tableView.getItems().clear();
        tableView.setItems(transactions);
        tableView.refresh();
    }
}

