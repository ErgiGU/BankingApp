package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pleasefivebank.Main;

import java.io.IOException;

import static pleasefivebank.EntryPage.EntryPageController.user;

public class EditDetailsController {

    @FXML
    private Text IBANlabel;

    @FXML
    private Text accountFrozenLabel;

    @FXML
    private Text accountnumberLabel;

    @FXML
    private TextField cityTextfield;

    @FXML
    private TextField postalcodeTextfield;

    @FXML
    private TextField streetTextfield;

    @FXML
    private TextField emailTextfield;

    @FXML
    private Label firstnameLabel;

    @FXML
    private Label lastnameLabel;

    @FXML
    private Text personalidLabel;

    @FXML
    private TextField phonenumberTextfield;

    @FXML
    private TextField universityTextfield;

    @FXML
    private TextField usernameTextfield;

    @FXML
    void SaveChanges(ActionEvent event) {

    }

    @FXML
    void toHomePage(ActionEvent event) {
        try {
            Main.showLoginPage(user.getFirstName()+ " " + user.getLastName());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void setData(String fullName){
        firstnameLabel.setText(user.getFirstName());
        lastnameLabel.setText(user.getLastName());
        IBANlabel.setText(user.getAccountIBAN());
        accountFrozenLabel.setText(user.getFrozen());
        accountnumberLabel.setText(user.getAccountNr());
        cityTextfield.setText(user.getCity());
        postalcodeTextfield.setText(user.getPostalCode());
        streetTextfield.setText(user.getAddress());
        emailTextfield.setText(user.getEmail());
        personalidLabel.setText(user.getPersonnummer());
        phonenumberTextfield.setText(user.getPhoneNumber());
        universityTextfield.setText(user.getUniversity());
        usernameTextfield.setText(user.getUsername1());
    }

}

