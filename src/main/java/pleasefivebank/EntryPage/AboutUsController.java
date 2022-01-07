package pleasefivebank.EntryPage;

import javafx.fxml.FXML;
import pleasefivebank.Main;

import java.io.IOException;

public class AboutUsController {

    //this page only showcases predetermined information
    //thus the only thing needed in this controller is a
    //method to go back to the entry page

    //juan
    @FXML
    void BackToEntryPage() {
        try {
            Main.showPage("Entry-Page.fxml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
