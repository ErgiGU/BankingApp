
package pleasefivebank.EntryPage;
import pleasefivebank.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import java.io.IOException;
import java.util.HashMap;

public class EntryPageController{
    //juan
    @FXML
    protected void PressedLoginButton() {
        try {
            Main.showPage("UserHomePage.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private PasswordField LoginPassword;

    @FXML
    private TextField LoginUsername;

    @FXML
    private Hyperlink SignUp;

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




