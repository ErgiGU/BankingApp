package pleasefivebank.EntryPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.MailBot;
import pleasefivebank.Objects.PasswordGen;

import javax.mail.MessagingException;
import java.io.IOException;

public class ForgotPasswordController {

    private static String tempEmail = "";
    private static String tempUsername = "";

    @FXML
    private TextField Email;

    @FXML
    private TextField Username;

    @FXML
    private Label confirmationLabel;

    @FXML
    //andreea and ossian
    protected void NewPasswordPressed(ActionEvent event){
        //get user input
        String email = Email.getText();
        String userName = Username.getText();
        if(!Mongo.isAssociatedEmail(email) || !Mongo.isUser(userName) ||
                (email.isEmpty()) || (userName.isEmpty())){
            confirmationLabel.setText("this account doesn't exist in our database");
        } else {
            tempEmail = email;
            tempUsername = userName;
            ForgotPassword forgotPassword = new ForgotPassword();//might need to be moved to the first line
            forgotPassword.setEmail(tempEmail);//set email and user as attributes
            forgotPassword.setUsername(tempUsername);
            forgotPassword.reset();
            //resetting password
            PasswordGen generator = new PasswordGen();
            String newpass = generator.GeneratePassword();
            forgotPassword.updatePassword(newpass,email);
            //preparing to send email
            MailBot mail = new MailBot();
            mail.setupServerProperties();
            try {
                mail.draftEmail(email,"New Password","Hi "+userName+" here is your new password: "+newpass );
                mail.sendEmail();
            } catch (MessagingException | IOException e) {
                e.printStackTrace();
            }
            try {
                Main.showPage("Entry-Page.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    protected void BackToEntryPage() {
        try {
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

