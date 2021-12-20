package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.Transaction;

import java.io.IOException;

public class RequestMoneyController {

    private static String tempName;
    private static long tempQuantity;
    private static String tempConcept;

    @FXML
    private TextField FullName;

    @FXML
    private TextField Quantity;

    @FXML
    private TextField Concept;

    @FXML
    private Label confirmLabel;

    //andreea
    @FXML
    public void ToRequestMoney(ActionEvent actionEvent) {
        String name = FullName.getText();//must modify to check for the whole name
        long quantity = Long.parseLong(Quantity.getText());
        String concept = Concept.getText();
        if((Mongo.isUser(name)) && (quantity>0) && (!concept.isEmpty())){
            tempName = name;
            tempQuantity = quantity;
            tempConcept = concept;
            Transaction purchase = new Transaction(tempName, "", tempQuantity, tempConcept);
            String date = Mongo.formatTime();// we register the time
            purchase.setDate(date);
            purchase.setStatus("pending");
            Mongo.coll3.insertOne(purchase.save());//will modify to add it to collection 2 instead
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
