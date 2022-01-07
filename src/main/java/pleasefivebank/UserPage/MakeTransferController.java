package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import org.bson.Document;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.Transaction;
import pleasefivebank.Objects.User;
import pleasefivebank.Utilities.Utilities;

import java.io.IOException;

import static pleasefivebank.EntryPage.EntryPageController.user;
import static pleasefivebank.Main.mainWindow;

public class MakeTransferController {
    private String tempReceiver;
    private String tempReceiverIBAN;
    private String tempAmount;
    private String tempMessage;
    //this controller only includes a method to set up the user data,
    //methods to go from page to page
    //and logic to make loans in the sendMoney method in line 135

    @FXML
    private TextField IBAN;

    @FXML
    private TextField Name;

    @FXML
    private Button NameDisplay;

    @FXML
    private TextField amount;

    @FXML
    private TextArea message;
    //juan
    @FXML
    void LogOut(ActionEvent event) {
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
            Main.showTransactionsPage(user.getFirstName() + " " +user.getLastName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private BorderPane borderPane;

    @FXML
    private StackPane rootPane;

    //juan and carlotta
    @FXML
    public void SendMoney(ActionEvent event) {
        String receiver = Name.getText();
        String receiverIban = IBAN.getText();
        String quantity = amount.getText();
        String concept = message.getText();

        if((Mongo.isValidIBAN(receiverIban)) && (Integer.parseInt(quantity)>0) && (!concept.isEmpty())){
            tempReceiver = receiver;
            tempReceiverIBAN = receiverIban;
            tempAmount = quantity;
            tempMessage = concept;

            float balance = Float.parseFloat(user.getBalance());
            float intAmount = Float.parseFloat(tempAmount);
            if (balance >= intAmount){
                Transaction purchase = new Transaction(tempReceiver, receiverIban, tempAmount, tempMessage, "Sent");
                //purchase.toDatabase();
                String newBalance = Float.toString(balance - intAmount);
                user.setBalance(newBalance);
                //update the current users balance in the db
                Mongo.updateInformation("balance",newBalance,user.getPersonnummer());
                //update the receivers balance in the database
                Document doc = Mongo.getDocumentWithIBAN(receiverIban);
                String receiverBalance = doc.get("balance").toString();
                String personnummerReceiver = doc.get("personnummer").toString();
                float newReceiverBalance = Float.parseFloat(receiverBalance)+intAmount;
                receiverBalance = Float.toString(newReceiverBalance);
                Mongo.updateInformation("balance",receiverBalance,personnummerReceiver);

                Mongo.updateJson();

            }

        }else{
            Utilities.popup("The transaction was unsuccessful, please enter the correct user information and try again.",borderPane,rootPane);
        }
    }

    //Juan
    public void setName(String name){
        NameDisplay.setText(name);
    }

    //elisa
    @FXML
    void ToTransactionsPage(ActionEvent event) {
        try {
            Main.showTransactionsPage(user.getFirstName()+ " " + user.getLastName());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //elisa
    @FXML
    void ToContactPage(ActionEvent event) {
        try {
            Main.showPage("ContactPage.fxml");;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

