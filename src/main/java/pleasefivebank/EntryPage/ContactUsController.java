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
    private TextArea MessageField;

    @FXML
    private TextField NameTextField;

    @FXML
    private Label messageLabel;

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
    //after that it sends an email to the user thanking them for the review
    @FXML
    void SendPressed(ActionEvent event) {
        try {
            String email = EmailTextField.getText();
            String name = NameTextField.getText();
            MailBot mail = new MailBot();
            mail.setupServerProperties();
            String reply = "Automated reply";
            String body = "Hello " + name +"\n\nThank you so much for reaching out to us! We have received your email and" +
                    " we will get back to you in 1-3 business days.\n\n\nThis is an automated reply, do not respond.";
            mail.draftEmail(email,reply,body);
            mail.sendEmail();
            Main.showPage("Entry-Page.fxml");
        } catch (IOException | MessagingException ex) {
            ex.printStackTrace();
        }
    }
    }

