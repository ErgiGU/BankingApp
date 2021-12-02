
package pleasefivebank.EntryPage;
import pleasefivebank.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import java.io.IOException;

public class EntryPageController{

    @FXML
    protected void PressedLoginButton() {
        try {
            Main.showPage("ForgotPassword.fxml");
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

    @FXML
    protected void ChangeToRegisterPage (){
        try {
            Main.showPage("RegistrationPage1.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    protected void ForgotPasswordPressed(){
        try {
            Main.showPage("ForgotPassword.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}




