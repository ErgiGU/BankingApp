
package pleasefivebank.EntryPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class EntryPageController{
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private PasswordField LoginPassword;

    @FXML
    private TextField LoginUsername;

    @FXML
    private Hyperlink SignUp;

    @FXML
    private Button BackToEntryPageButton;

    @FXML
    private TextField FirstName;

    @FXML
    private TextField LastName;

    @FXML
    private TextField MiddleName;

    @FXML
    private TextField PersonalID;

    @FXML
    private Button ToPage2;

    @FXML
    void BackToEntryPagePressed(ActionEvent event) throws IOException{
        URL url = new File("src/main/resources/pleasefivebank/Entry-Page.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ToPage2Pressed(ActionEvent event) {

    }

    @FXML
    void PressedLoginButton(ActionEvent event) throws IOException {
        //get input
        String userName = LoginUsername.getText();
        String password = LoginPassword.getText();
        //validate it
        System.out.println("username: "+userName+" password: "+password);
        //if valid go to user Menu

        //if not valid stay in page but show error msg
        URL url = new File("src/main/resources/pleasefivebank/RegistrationPage1.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void ChangeToRegisterPage (ActionEvent event) throws IOException {

    }
}




