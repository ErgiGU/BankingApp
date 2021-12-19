package pleasefivebank.EntryPage;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pleasefivebank.Main;
import pleasefivebank.Objects.Transaction;

import java.io.IOException;

public class TransactionsController {

    String date = "";
    String receiver = "";
    String receiverIBAN = "";
    Double quantity = 0.00;
    String concept = "";
    String status = "";
    Transaction transaction;

    @FXML
    private TextField scrollbar;
    @FXML
    protected void TransactionsMenu() {
        //make a transaction
        transaction = new Transaction(date, receiver, receiverIBAN, quantity, concept, "approved");
        //add it to the transaction collection
        transaction.save();
        //request a transaction
        transaction = new Transaction(date, receiver, receiverIBAN, quantity, concept, "pending");
    }
}
