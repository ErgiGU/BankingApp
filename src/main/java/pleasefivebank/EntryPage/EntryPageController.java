
package pleasefivebank.EntryPage;
import javafx.scene.control.Label;
import pleasefivebank.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import java.io.IOException;
import java.util.HashMap;

public class EntryPageController{



    private static String tempUserName = "";
    private static String tempPassword = "";

    //juan && andreea
    @FXML
    protected void PressedLoginButton(ActionEvent event) {
        String username = "";
        String password = "";
        String encryptedPassword = "";
        int i = 0;
        do {//get user input
            username = LoginUsername.getText();
            password = LoginPassword.getText();
            //make sure input it is not empty
            if ((username.isEmpty()) || (password.isEmpty())) {
                confirmLabel.setText("try again...");
                i++;
                continue;
            }
            //encrypt the password to search for it in the database
            encryptedPassword = Mongo.encrypt(password);
            i++;
        } while (!Mongo.isValidLogin(username, encryptedPassword) && (i<3));//try at most 3 times
        if(i==3){ confirmLabel.setText("press forgot password");
        } else {
            EntryPage login = new EntryPage();
            tempUserName = username;
            tempPassword = password;
            login.setUsername(tempUserName);//set the user and password as attributes
            login.setPassword(tempPassword);
            try {
                Main.showPage("UserHomePage.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private PasswordField LoginPassword;

    @FXML
    private TextField LoginUsername;

    @FXML
    private Label confirmLabel;

    //juan
    @FXML
    protected void ChangeToRegisterPage (){
        try {
            Main.showPage("RegistrationPage1.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //juan
    @FXML
    protected void ForgotPasswordPressed(){
        try {
            Main.showPage("ForgotPassword.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //andreea
    @FXML
    protected void ContactUsPressed(){
        try {
            Main.showPage("ContactUs.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //andreea
    @FXML
    protected void AboutUsPressed(){
        try {
            Main.showPage("aboutUsPage.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}




