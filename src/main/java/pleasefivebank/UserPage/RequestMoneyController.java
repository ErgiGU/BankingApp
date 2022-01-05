package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.Transaction;
import pleasefivebank.Objects.User;

import java.io.IOException;

import static pleasefivebank.EntryPage.EntryPageController.user;
import static pleasefivebank.Main.mainWindow;

public class RequestMoneyController {
    //this controller corresponds to the page where the user can request money
    //there are methods to connect pages and to save and store the transaction requests

    @FXML
    private TextField Amount;

    @FXML
    private TextField IBAN;

    @FXML
    private TextArea Message;

    @FXML
    private TextField Name;

    @FXML
    private Button NameLabel;

    @FXML
    void LogOut(ActionEvent event) {
        try {
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    void Request(ActionEvent event) {
        String receiver = Name.getText();
        String receiverIban = IBAN.getText();
        String quantity = Amount.getText();
        String concept = Message.getText();
        if((Mongo.isValidIBAN(receiverIban)) && (Integer.parseInt(quantity)>0) && (!concept.isEmpty())){
            float balance = Float.parseFloat(user.getBalance());
            float intAmount = Float.parseFloat(quantity);
            if (balance >= intAmount){
                Transaction purchase = new Transaction(receiver, receiverIban, quantity, concept, "requested");
            }

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
    //juan
    public void setName(String name){
        NameLabel.setText(name);
    }

}


