
package pleasefivebank.EntryPage;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import pleasefivebank.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import pleasefivebank.Objects.User;


import java.io.IOException;

import static pleasefivebank.Menus.UsefulFunctions.popup;

public class EntryPageController{

    private static String tempUserName = "";
    private static String tempPassword = "";
    private static int i = 0;
    public static User user;
    @FXML
    private StackPane rootPane;

    //Ergi && Andreea
    //this method is made up of three parts
    //the first one gathers user information
    //the second one validates the data and
    //and logs the user in if the data is valid
    //the third part activates the labels if user input is wrong
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
        boolean k = Mongo.isValidLogin(username, encryptedPassword);
        boolean validUsername = Mongo.usernameExists(username);
        if(validUsername) {
            EntryPage login = new EntryPage(username);
            User user1 = login.getLogin();
            //checks if the password is right
            if (k && i < 3 && user1.getFrozen().equals("false")) {
                user = user1;
                tempUserName = username;
                tempPassword = encryptedPassword;
                login.setUsername(tempUserName);
                login.setPassword(tempPassword);
                i = 0;
                try {
                    Mongo.mongoTransactions();
                    main.showLoginPage(user.getFirstName() + " " + user.getLastName());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (!k) {
                i++;
                if (i == 1) {
                    confirmLabel.setText("Wrong password, 3 attempt(s) left");
                } else if (i == 2) {
                    confirmLabel.setText("Wrong password, 2 attempt(s) left");
                } else if (i == 3) {
                    confirmLabel.setText("Wrong password, 1 attempt(s) left");
                } else if (i == 4) {
                    //freezes account and displays a popup
                    user1.freezeAccount();
                    confirmLabel.setText(null);
                    popup("Your account is frozen, please contact us.",borderpane,rootPane);

                }else{
                    popup("Your account is frozen, please contact us.",borderpane,rootPane);
                }
            }else if(user1.getFrozen().equals("false")){
                popup("Your account is frozen, please contact us.",borderpane,rootPane);
            }
            }else {
            confirmLabel.setText("Invalid username");
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

    //elisa
    @FXML
    public void ToEntryMenu(ActionEvent event) {
        try {
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Ergi
    //this method allows for pop up screens when the user
    //fails three times with the password by freezing the account

    public static void popup(String message, BorderPane pane, StackPane stackPane){
        BoxBlur blur = new BoxBlur(3, 3, 3);
        JFXDialogLayout layout = new JFXDialogLayout();
        JFXButton button = new JFXButton("OK");
        button.getStyleClass().add(".dialog-button");
        JFXDialog dialog = new JFXDialog(stackPane, layout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mousevent) -> {
            dialog.close();
        });
        layout.setHeading(new Label("Your account is frozen, please contact us"));
        layout.setActions(button);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
            pane.setEffect(null);
        });
        pane.setEffect(blur);
    }
}




