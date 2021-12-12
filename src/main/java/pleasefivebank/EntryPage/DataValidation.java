package pleasefivebank.EntryPage;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DataValidation {
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
    public static boolean dataLength(String inputTextField, Label inputLabel, String validationText, String requiredLength){
        boolean isDataLength = true;
        String validationString = null;
        if(!inputTextField.matches("\\b\\w" +"{" + requiredLength + "}" + "\\b")){
            //isDataLength = false;
            validationString = validationText;
        }
        return isDataLength;
    }

    //Ergi
    public static boolean textAlphabet(String inputTextField, Label inputLabel, String validationText){
        String validationString = null;
        boolean isAlphabet = true;
        if(!inputTextField.matches("[a-z A-Z] +")){
            isAlphabet = false;
            validationString = validationText;
        }
        inputLabel.setText(validationString);

        return isAlphabet;
    }

    //Ergi
    public static boolean textNumeric(String inputTextField, Label inputLabel, String validationText){
        boolean isNumeric = true;
        String  validationString = null;
        if(!inputTextField.matches("[0-9] +")){
            isNumeric = false;
            validationString = validationText;
        }
        inputLabel.setText(validationString);
        return isNumeric;
    }

    //Ergi (Java email validation permitted by RFC 5322)
    public static boolean emailFormat(String inputTextField, Label inputLabel, String validationText){
        boolean isEmail = true;
        String validationString = null;
        if(!inputTextField.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
            isEmail = false;
            validationString = validationText;
        }
        inputLabel.setText(validationString);
        return isEmail;
    }

    public static boolean postalCode(String inputTextField, Label inputLabel, String validationText, String requiredLength){
        boolean isPostal = true;
        String validationString = null;
        if(!inputTextField.matches("\\d{5}")){
            isPostal=false;
            validationString = validationText;
        }
        inputLabel.setText(validationString);
        return isPostal;
    }
}
