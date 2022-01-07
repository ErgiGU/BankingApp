package pleasefivebank.Utilities;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.text.DecimalFormat;


//linus
//method to truncate numbers for the loans page
public class Utilities {


    public static double trunc(double value){
        value = value * 100.0;
        int temp = (int) value;
        return temp / 100.0;

    }

    //Ergi
    //this method allows for pop up screens when the user
    //fails three times with the password by freezing the account
    public static void popup(String message, BorderPane pane, StackPane stackPane){
        BoxBlur blur = new BoxBlur(3, 3, 3);
        JFXDialogLayout layout = new JFXDialogLayout();
        JFXButton button = new JFXButton("OK");
        button.setStyle("-fx-background-color: #1275EA; -fx-text-fill: white;");
        JFXDialog dialog = new JFXDialog(stackPane, layout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mousevent) -> {
            dialog.close();
        });
        layout.setHeading(new Label(message));
        layout.setActions(button);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
            pane.setEffect(null);
        });
        pane.setEffect(blur);
    }

}
