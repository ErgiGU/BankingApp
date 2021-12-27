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
    @FXML
    void BackToEntryPage() {
        try {
            Main.showPage("Entry-Page.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //andreea and ossian
    @FXML
    void SendPressed(ActionEvent event){
        //get user input
        String email = EmailTextField.getText();
        String username = NameTextField.getText();
        String message = MessageField.getText();
        //validate data
        if((!Mongo.isUser(username)) || (!Mongo.isAssociatedEmail(email))
                || (email.isEmpty()) || (username.isEmpty()) || (message.isEmpty())){
            messageLabel.setText("please try again...");
        }
        Document review = new Document("email", email).append("user name", username).
                append("text", message);
        Mongo.coll4.insertOne(review);
        //preparing to send email
        MailBot mail = new MailBot();
        mail.setupServerProperties();
        try {
            mail.draftEmail(email,"New Password","Hi "+username+" thank you for your feedback." );
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Main.showPage("EmailSent.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
