package pleasefivebank.UserPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import pleasefivebank.Main;
import pleasefivebank.Objects.User;

import java.io.IOException;

public class AccountDetailsController {

    @FXML
    private Text IBAN;

    @FXML
    private Text accountFrozen;

    @FXML
    private Text accountNumber;

    @FXML
    private Text address;

    @FXML
    private Text email;

    @FXML
    private Text firstName;

    @FXML
    private Text lastName;

    @FXML
    private Text personalID;

    @FXML
    private Text phoneNumber;

    @FXML
    private Text university;

    @FXML
    private Text username;

    @FXML
    void EditDetails(ActionEvent event) {

    }

    @FXML
    void ToUserHomePage(ActionEvent event) {
        try {
            Main.showPage("UserHomePage.fxml");
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

    public void setInformation(User user){
        //username.setText
        IBAN.setText(user.getAccountIBAN());

        accountFrozen.setText(user.getFrozen());

        accountNumber.setText(user.getAccountNr());

        address.setText(user.getAddress());

        email.setText(user.getEmail());

        firstName.setText(user.getFirstName());

        lastName.setText(user.getLastName());

        personalID.setText(user.getPersonnummer());

        phoneNumber.setText(user.getPhoneNumber());

        university.setText(user.getUniversity());

        username.setText(user.getUsername());
    }
}
