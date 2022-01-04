package pleasefivebank.UserPage;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.Loan;
import pleasefivebank.Objects.Transaction;
import pleasefivebank.Objects.User;

import java.io.IOException;

import static pleasefivebank.EntryPage.EntryPageController.user;
import static pleasefivebank.Main.mainWindow;

public class NotificationsController {

    @FXML
    private TableColumn<?, ?> IBANColumn;

    @FXML
    private Button NameLabel;

    @FXML
    private TableColumn<?, ?> amountColumn;

    @FXML
    private TableColumn<?, ?> amountLoans;

    @FXML
    private TableColumn<?, ?> conceptColumn;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> dateLoans;

    @FXML
    private TableColumn<?, ?> interestLoans;

    @FXML
    private TableView<Loan> loansTable;

    @FXML
    private TableColumn<?, ?> receiverColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> statusLoans;
    @FXML
    private TableView<Transaction> requestedTransactionsTable;

    //juan
    @FXML
    void Logout(ActionEvent event) {
        try {
            Main.showPage("Entry-Page.fxml");
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
    public void setupRequestsTable(){
        dateColumn = new TableColumn<Transaction, String>();
        dateColumn.setText("Date");

        receiverColumn = new TableColumn<Transaction, String>();
        receiverColumn.setText("Receiver");

        IBANColumn = new TableColumn<Transaction, String>();
        IBANColumn.setText("Receiver IBAN");

        amountColumn = new TableColumn<Transaction, String>();
        amountColumn.setText("Quantity");

        conceptColumn = new TableColumn<Transaction, String>();
        conceptColumn.setText("Concept");

        statusColumn = new TableColumn<Transaction, String>();
        statusColumn.setText("Status");

        requestedTransactionsTable.getItems().clear();
        ObservableList<Transaction> transactions = Mongo.getAllPendingTransactions(user.getAccountIBAN());
        requestedTransactionsTable.getItems().clear();
        requestedTransactionsTable.setItems(transactions);
        requestedTransactionsTable.refresh();
    }
    public void seTupLoansTable(){
        dateLoans = new TableColumn<Loan, String>();
        dateLoans.setText("Date");

        amountLoans = new TableColumn<Loan, String>();
        amountLoans.setText("Loan Amount");

        interestLoans = new TableColumn<Loan, String>();
        interestLoans.setText("Loan Interest");

        statusLoans = new TableColumn<Loan, String>();
        statusLoans.setText("Loan Status");

        loansTable.getItems().clear();
        ObservableList<Loan> loans = Mongo.getAllLoans(user.getAccountIBAN());
        loansTable.getItems().clear();
        loansTable.setItems(loans);
        loansTable.refresh();
    }
    public void setUpData(){
        try {
            Mongo.mongoLoans();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setupRequestsTable();
        seTupLoansTable();
        NameLabel.setText(user.getFirstName()+ " " + user.getLastName());
    }

}

