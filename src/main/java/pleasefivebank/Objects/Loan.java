package pleasefivebank.Objects;

import org.bson.Document;
import pleasefivebank.Mongo;

import java.io.FileWriter;
import java.io.IOException;

public class Loan{
    //attributes for the loan
    private String status;
    private String amountPerMonth;
    private String loanPeriod;
    private String accountIBAN;
    private String interestRate;

    //this two attributes are not saved because they are not relevant to the user
    private long quantity;
    private boolean checkbox;

    //this is a constructor used to showcase information in the GUI
    //Linus
    public Loan(){
        this.status = "pending";
        this.quantity = 0;
        this.checkbox = false;
    }
    //juan and carlotta
    //this constructor is used when the user applies for a loan
    //it initializes the neccesary variables and also saves the data in the database and the JSON
    public Loan(String amountPerMonth,String AccountIBAN,String LoanPeriod, String interestRate){
        this.status = "pending";
        this.amountPerMonth = amountPerMonth;
        this.accountIBAN = AccountIBAN;
        this.loanPeriod = LoanPeriod;
        this.interestRate = interestRate;
        toDatabase();
    }

    //juan and carlotta
    //this constructor is used to convert the database objects into actual objects
    //to showcase them in tables using observableLists
    public Loan(String amountPerMonth,String AccountIBAN,String LoanPeriod, String interestRate, String display){
        this.status = "pending";
        this.amountPerMonth = amountPerMonth;
        this.accountIBAN = AccountIBAN;
        this.loanPeriod = LoanPeriod;
        this.interestRate = interestRate;
    }

    //method to convert a loan into a object
    public Document toDocument() {
        Document doc = new Document("amountPerMonth",this.amountPerMonth).append("accountIBAN",this.accountIBAN)
                .append("loanPeriod",this.loanPeriod).append("status",this.status).append("interestRate",this.interestRate);
        return doc;
    }

    //method to introduce the loan in the database and the file
    public void toDatabase(){
        Mongo.coll3.insertOne(toDocument());
        toFile();
    }
    //method to write the loan inside the file
    public void toFile(){
        try {
            FileWriter writer = new FileWriter("Loans.json", true);
            writer.write(toDocument().toJson() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //juan
    //this method makes sure the user presses the checkbox accepting the terms
    public void changeCheckBox(){
        if(checkbox){
            checkbox = false;
        } else{ checkbox = true; }
    }

    //Linus
    //this makes an interest object to showcase information in the loans GUI page
    public Interest totalCosts(double quantity, int estPayBack, int loanPeriod){
        //the quantity the user specifies are monthly payments
        quantity = quantity*loanPeriod*12;
        double monthlyPayBack = quantity/estPayBack/12;
        double total = 0;
        double rentOnly =0;
        double originalQuantity = quantity;

        for(int i = estPayBack*12; i>0; i--) {

            double rent = quantity * (0.0214/12); //Our interest rate
            quantity = quantity - monthlyPayBack;
            double paybackWRent = monthlyPayBack + rent;
            total = total + paybackWRent;
            rentOnly = total - originalQuantity;
        }
        return new Interest(total, rentOnly);
    }
}