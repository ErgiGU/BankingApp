package pleasefivebank.Registration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class RegistrationController4 {

    @FXML
    private Button BackButton;

    @FXML
    private Button BackToEntryPageButton;

    @FXML
    private ImageView NextButton;

    @FXML
    private ComboBox<?> UniversityOption;

    @FXML
    private ImageView backButton;



    @FXML
    void BackPressed(ActionEvent event) {
        //go back to page 3 of registration
    }

    @FXML
    void BackToEntryPagePressed(ActionEvent event) {
        //go back to entryPage
    }

    @FXML
    void NextPressed(ActionEvent event) {
        //get input
        // String university = UniversityOption.
        //go to login page

    }

}
