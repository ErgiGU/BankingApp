package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.Transaction;

import java.io.IOException;

public class PayMoneyController {

    private static String tempReceiver;
    private static String tempReceiverIBAN;
    private static long tempQuantity;
    private static String tempConcept;

    @FXML
    private TextField Receiver;

    @FXML
    private TextField ReceiverIban;

    @FXML
    private TextField Quantity;

    @FXML
    private TextField Concept;

    @FXML
    private Label confirmLabel;

    //andreea
    @FXML
    public void ToTransferMoney(ActionEvent actionEvent) {
        String receiver = Receiver.getText();
        String receiverIban = ReceiverIban.getText();
        long quantity = Long.parseLong(Quantity.getText());
        String concept = Concept.getText();
        if((Mongo.isAccount(receiver, receiverIban)) && (quantity>0) && (!concept.isEmpty())){
            tempReceiver = receiver;
            tempReceiverIBAN = receiverIban;
            tempQuantity = quantity;
            tempConcept = concept;
            Transaction purchase = new Transaction(tempReceiver, receiverIban, tempQuantity, tempConcept);
            String date = Mongo.formatTime();// we register the time
            purchase.setDate(date);
            //validate balance
            //update balance
            purchase.setStatus("approved");
            Mongo.coll3.insertOne(purchase.save());
        }
        else{confirmLabel.setText("this user does not have an account yet");
        }
    }

    @FXML
    void ToCards(ActionEvent event) {
        try {
            Main.showPage("CardsPage.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void ToHome(ActionEvent event) {
        try {
            Main.showPage("UserHomePage.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void ToLoans(ActionEvent event) {
        try {
            Main.showPage("StudentLoans.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void ToDetails(ActionEvent event) {
        try {
            Main.showPage("AccountDetails.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

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
}
