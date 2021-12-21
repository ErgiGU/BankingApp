package pleasefivebank.EntryPage;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DataValidation {//marked as final because it is a utility class and it cannot be instantiated


    //Ergi
    public static boolean textFieldIsEmpty(String inputTextField, Label inputLabel, String validationText){
        boolean isEmpty = false;
        String validationString = null;
        if(inputTextField.isBlank()){
            isEmpty = true;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return isEmpty;
    }

    //Ergi
    public static boolean passwordsMatch(String password,String confirmPassword, Label inputLabel, String validationText){
        boolean matches =true;
        String validationString = null;
        if(!password.equals(confirmPassword) || password.isBlank()){
            matches = false;
            validationString = validationText;

        }
        inputLabel.setText(validationString);
        return matches;
    }

    //Ergi (Java email validation permitted by RFC 5322)
    public static boolean validateField(String inputTextField, Label inputLabel, String regex, String validationText ){
        boolean isValidated = true;
        String validationString = null;
        if(!inputTextField.matches(regex)){
            isValidated = false;
            validationString = validationText;
        }
        inputLabel.setText(validationString);
        return isValidated;
    }


}
