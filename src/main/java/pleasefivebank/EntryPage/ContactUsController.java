package pleasefivebank.EntryPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.bson.Document;
import pleasefivebank.Main;
import pleasefivebank.Mongo;
import pleasefivebank.Objects.MailBot;
import pleasefivebank.Objects.PasswordGen;

import javax.mail.MessagingException;
import java.io.IOException;

public class ContactUsController {

    @FXML
    private TextField EmailTextField;

    @FXML
    private Label nameLabel;

    @FXML
    private TextArea MessageField;

    @FXML
    private TextField NameTextField;

    @FXML
    private Label emailLabel;

    //andreea
    //this method is self-explanatory, it goes back to the entry page
    @FXML
    void BackToEntryPage() {
        try {
            Main.showPage("Entry-Page.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //andreea,ossian and Ergi
    //this method saves the review to the 4th collection of the database
    //after that it sends an automated email
    @FXML
    void SendPressed(ActionEvent event) throws IOException, MessagingException {
        String email = EmailTextField.getText();
        String name = NameTextField.getText();
        if (!DataValidation.validateField(email, emailLabel, "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\\\"(?:[" +
                "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)" +
                "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[" +
                "\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", "Invalid email format")
                && DataValidation.textFieldIsEmpty("NameTextField", nameLabel, "Please enter your name")) {
            MailBot mail = new MailBot();
            mail.setupServerProperties();
            String reply = "Automated reply";
            String body = "Hello " + name + "\n\nThank you so much for reaching out to us! We have received your email and" +
                    " we will get back to you in 1-3 business days.\n\n\nThis is an automated reply, do not respond.";
            mail.draftEmail(email, reply, body);
            mail.sendEmail();
            Main.showPage("Entry-Page.fxml");


        }
    }
}

