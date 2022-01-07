package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import pleasefivebank.Main;

import java.io.IOException;

import static pleasefivebank.EntryPage.EntryPageController.user;

public class ContactController {

    @FXML
    private Text HelloMessage;


    @FXML
    private Button NameDisplay;


    @FXML
    void Logout(ActionEvent event) {
        try {
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void ToCards(ActionEvent event) {
        try {
            Main.showCardsPage();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void ToDetails(ActionEvent event) {
        try {
            Main.showDetails(user);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void ToLoans(ActionEvent event) {
        try {
            Main.showLoansPage(user.getFirstName()+ " " + user.getLastName());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void ToTransactions(ActionEvent event) {
        try {
            Main.showTransactionsPage(user.getFirstName()+ " " + user.getLastName());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void setName() {
        NameDisplay.setText(user.getFirstName() + " " + user.getLastName());
    }

    //Ergi
    @FXML
    void ToHome(ActionEvent event) {
        try {
            Main.showLoginPage(user.getFirstName()+ " " + user.getLastName());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

