package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import pleasefivebank.Main;
import pleasefivebank.Objects.User;

import java.io.IOException;

import static pleasefivebank.EntryPage.EntryPageController.user;
import static pleasefivebank.Main.mainWindow;

public class CardsController {

    @FXML
    private Text AccountNumber;

    @FXML
    private Text BIC;

    @FXML
    private Text Balance;

    @FXML
    private Text CardName;

    @FXML
    private Text CardNumber;

    @FXML
    private Text ExpirationDate;

    @FXML
    private Text IBAN;

    @FXML
    private Button NameLabel;

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
            Main.showTransactionsPage(user.getFirstName()+ " " + user.getLastName());
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
        NameLabel.setText(user.getFirstName()+ " " + user.getLastName());
        CardName.setText(user.getFirstName()+ " " + user.getLastName());
        CardNumber.setText(user.getCardNumber());
        ExpirationDate.setText(user.getExpirationDate());
        float balance = Float.parseFloat(user.getBalance());
        balance -= balance % 0.001;
        Balance.setText("+" + balance +" SEK");
        AccountNumber.setText(user.getAccountNr());
        IBAN.setText(user.getAccountIBAN());
        BIC.setText("PFBSEGBGXXX");
    }

}




