package pleasefivebank.EntryPage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pleasefivebank.Main;

import java.io.IOException;

public class EmailPendingApprovalController {

    @FXML
    public void toEntryPage(ActionEvent event) {
        try {
            Main.showPage("Entry-Page.fxml");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
