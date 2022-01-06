package pleasefivebank.UserPage;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.Loan;
import pleasefivebank.Objects.Transaction;

import java.io.IOException;

import static pleasefivebank.EntryPage.EntryPageController.user;

public class NotificationsController {

    //This controller corresponds to the page where we showcase transaction requests received by the user and the
    //status of loans

    @FXML
    private TableColumn<?, ?> IBANColumn;

    @FXML
    private Button NameDisplay;

    @FXML
    private TableColumn<?, ?> amountColumn;

    @FXML
    private TableColumn<?, ?> amountLeft;

    @FXML
    private TableColumn<?, ?> amountLoans;

    @FXML
    private TableColumn<?, ?> conceptColumn;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> totalAmount;

    @FXML
    private TableColumn<?, ?> loanPeriod;

    @FXML
    private TableView<Loan> loansTable;

    @FXML
    private TableColumn<?, ?> receiverColumn;

    @FXML
    private TableView<Transaction> requestedTransactionsTable;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private TableColumn<?, ?> statusLoans11;

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
            Main.showTransactionsPage(user.getFirstName() + " ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //method to setup the table showcasing transaction requests
    //juan and carlotta
    public void setupRequestsTable(){
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

        requestedTransactionsTable.getItems().clear();
        ObservableList<Transaction> transactions = Mongo.getAllPendingTransactions(user.getAccountIBAN());
        requestedTransactionsTable.getItems().clear();
        requestedTransactionsTable.setItems(transactions);
        requestedTransactionsTable.refresh();
    }
    //juan
    //method to setup the table where the user can see the loan statuses
    public void seTupLoansTable(){

        amountLoans = new TableColumn<Loan, String>();
        amountLoans.setText("Amount per month");

        totalAmount = new TableColumn<Loan, String>();
        totalAmount.setText("Total Amount");

        amountLeft = new TableColumn<Loan, String>();
        amountLeft.setText("Amount Left");

        loanPeriod = new TableColumn<Loan, String>();
        loanPeriod.setText("Loan period(years)");

        statusLoans11 = new TableColumn<Loan, String>();
        statusLoans11.setText("Status");

        loansTable.getItems().clear();
        ObservableList<Loan> loans = Mongo.getAllLoans(user.getAccountIBAN());
        loansTable.getItems().clear();
        loansTable.setItems(loans);
        loansTable.refresh();
    }
    //this method sets up all of the data up before the user can see the page
    public void setUpData(){
        try {
            Mongo.mongoLoans();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setupRequestsTable();
        seTupLoansTable();
        NameDisplay.setText(user.getFirstName()+ " " + user.getLastName());
    }

}

