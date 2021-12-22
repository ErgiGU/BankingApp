
package pleasefivebank.EntryPage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.bson.Document;
import pleasefivebank.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Hyperlink;
import pleasefivebank.Objects.User;
import pleasefivebank.UserPage.HomePageController;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jfoenix.converters.DialogTransitionConverter;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.CachedTransition;

import java.io.IOException;
import java.util.HashMap;

import static com.mongodb.client.model.Filters.eq;

public class EntryPageController{

    private static String tempUserName = "";
    private static String tempPassword = "";
    private static int i = 0;
    @FXML
    private StackPane rootPane;

    //juan && andreea
    @FXML
    protected void PressedLoginButton(ActionEvent event) {
        String username = "";
        String password = "";
        String encryptedPassword = "";
        Main main = new Main();
        //get user input
        username = LoginUsername.getText();
        password = LoginPassword.getText();
        //encrypt the password to search for it in the database
        encryptedPassword = Mongo.encrypt(password);
        //try at most 3 times
        Boolean k = Mongo.isValidLogin(username, encryptedPassword);
        if (k && i < 3) {
            EntryPage login = new EntryPage(encryptedPassword,username);
            User user = login.getLogin();
            tempUserName = username;
            tempPassword = encryptedPassword;
            login.setUsername(tempUserName);
            login.setPassword(tempPassword);
            i = 0;
            try {
                main.showLoginPage(user.getFirstName()+" " + user.getLastName());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (!k) {
            i++;
            confirmLabel.setText("Invalid username or password");
            if (i >= 3) {
                BoxBlur blur = new BoxBlur(3, 3, 3);
                JFXDialogLayout layout = new JFXDialogLayout();
                JFXButton button = new JFXButton("OK");
                button.getStyleClass().add("dialog-button");
                JFXDialog dialog = new JFXDialog(rootPane, layout, JFXDialog.DialogTransition.TOP);
                button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mousevent) -> {
                    dialog.close();
                });
                layout.setHeading(new Label("Your account is now frozen"));
                layout.setActions(button);
                dialog.show();
                dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
                    borderpane.setEffect(null);
                });
                borderpane.setEffect(blur);

            }

        }
    }
    @FXML
    private BorderPane borderpane;

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




