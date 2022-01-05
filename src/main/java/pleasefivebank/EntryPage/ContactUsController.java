package pleasefivebank.EntryPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    //andreea
    @FXML
    void BackToEntryPage() {
        try {
            Main.showPage("Entry-Page.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    //andreea
    @FXML
    void SendPressed(ActionEvent event) throws IOException {
        try {
            String email = EmailTextField.getText();
            String name = NameTextField.getText();
            String message = MessageField.getText();
            PasswordGen gen = new PasswordGen();
            MailBot mail = new MailBot();
            mail.setupServerProperties();
            mail.draftEmail(email,"your dick","dear fuck " + gen.GeneratePassword());
            mail.sendEmail();
            //validate data then add to database
            /*Document review = new Document("email", email).append("name", name).
                    append("text", message);
            Mongo.coll.insertOne(review);*/
            Main.showPage("Entry-Page.fxml");
        } catch (IOException | MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
