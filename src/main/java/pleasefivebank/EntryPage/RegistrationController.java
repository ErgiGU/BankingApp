package pleasefivebank.EntryPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.User;

import java.io.IOException;
import java.util.Random;

public class RegistrationController{

    //the whole 4 pages from the registration controller are administered here
    //there are methods to go from page to page, to go back pages and to set up
    //data

    private final Registration registration = new Registration();
    private String selection = "no university";


    @FXML
    protected TextField FirstName;

    @FXML
    protected TextField LastName;

    @FXML
    protected TextField MiddleName;

    @FXML
    protected TextField PersonalID;

    //juan
    @FXML
    void BackToEntryPage() {
        try {
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    protected Label firstNameLabel;

    @FXML
    protected Label addressLabel;

    @FXML
    protected Label cityLabel;

    @FXML
    protected Label postalLabel;

    @FXML
    protected Label emailLabel;

    @FXML
    protected Label phoneLabel;

    @FXML
    protected Label lastNameLabel;

    @FXML
    protected Label idLabel;

    @FXML
    protected Label middleNameLabel;

    //Ergi
    @FXML
    void Page1to2() {
        registration.Page1to2Logic(FirstName,LastName,MiddleName,PersonalID,
                firstNameLabel,lastNameLabel,middleNameLabel,idLabel);
    }

    @FXML
    protected TextField City;

    @FXML
    protected TextField Email;

    @FXML
    protected TextField PhoneNumber;

    @FXML
    protected TextField PostalCode;

    @FXML
    protected TextField Address;

    //juan && Ergi
    @FXML
    void Page2to1() {
        try {
            Registration.backToPage("RegistrationPage1.fxml",1);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Ergi
    @FXML
    void Page2to3() {
        registration.page2to3Logic(Address,City,PostalCode,Email,PhoneNumber,
                addressLabel,cityLabel,postalLabel,emailLabel,phoneLabel);
    }

    @FXML
    protected PasswordField ConfirmPassword;

    @FXML
    protected Label confirmLabel;

    @FXML
    protected Label passwordLabel;

    @FXML
    protected Label usernameLabel;

    @FXML
    protected PasswordField Password;

    @FXML
    protected TextField UserName;

    //juan && Ergi
    @FXML
    void Page3to2(ActionEvent event) {
        try {
            Registration.backToPage("RegistrationPage2.fxml",2);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Ergi
    @FXML
    void Page3to4(ActionEvent event) {
        registration.page3to4Logic(UserName,Password,ConfirmPassword,usernameLabel,passwordLabel,confirmLabel);
    }

    //juan
    @FXML
    void checkBoxPressed() {
        registration.changeCheckBox();
    }

    @FXML
    protected Button FinishRegister;

    @FXML
    protected ComboBox<String> UniversityOption;

    //Ergi && Juan
    @FXML
    void Select(ActionEvent event) throws IOException {
        selection = UniversityOption.getSelectionModel().getSelectedItem();
        Registration.tempUni = selection;

    }

    @FXML
    private Tooltip passwordPrompt;

    //Ergi
    @FXML
    void showTip(MouseEvent event) {
        passwordPrompt.setText("Password must be at least 8 characters and contain at least " +
                "one(uppercase,digit,special character)");
    }


    //Ergi
    @FXML
    void FinishRegister(ActionEvent event) {
        registration.FinishRegisterLogic();
    }



    //juan && Ergi
    @FXML
    void Page4to3(ActionEvent event) {
        try {
            Registration.backToPage("RegistrationPage3.fxml",3);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //juan
    public void setData1(){
        FirstName.setText(Registration.tempFirstName);
        LastName.setText(Registration.tempLastName);
        MiddleName.setText(Registration.tempMiddleName);
        PersonalID.setText(Registration.tempID);
    }
    //Ergi && Juan
    public void setData2(){
        Address.setText(Registration.tempAddress);
        City.setText(Registration.tempCity);
        PostalCode.setText(Registration.tempPostal);
        Email.setText(Registration.tempEmail);
        PhoneNumber.setText(Registration.tempPhone);
    }
    //Ergi && Juan
    public void setData3(){
        UserName.setText(Registration.tempUsername);
        Password.setText(Registration.tempPassword);
        ConfirmPassword.setText(Registration.tempConfirmPassword);

    }
}




