package pleasefivebank.EntryPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pleasefivebank.Main;

import java.io.IOException;

public class EmailPendingApprovalController {

    //this is the controller for the page that appears when you finish registering
    //it is just a confirmation page that offers the option to go back to the login
    //page once the registration process is complete
    @FXML
    public void ToEntryPage(ActionEvent event) {
        try {
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
