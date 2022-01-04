package pleasefivebank.Objects;

import org.bson.Document;
import pleasefivebank.Mongo;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Loan{
    private String status;
    private String amountPerMonth;
    private String loanPeriod;
    private String accountIBAN;
    private String interestRate;


    private long quantity;
    private boolean checkbox;


    public Loan(){
        this.status = "pending";
        this.quantity = 0;
        this.checkbox = false;
    }
    //juan and carlotta
    public Loan(String amountPerMonth,String AccountIBAN,String LoanPeriod, String interestRate){
        this.status = "pending";
        this.amountPerMonth = amountPerMonth;
        this.accountIBAN = AccountIBAN;
        this.loanPeriod = LoanPeriod;
        this.interestRate = interestRate;
        toDatabase();
    }

    //juan and carlotta
    public Loan(String amountPerMonth,String AccountIBAN,String LoanPeriod, String interestRate, String display){
        this.status = "pending";
        this.amountPerMonth = amountPerMonth;
        this.accountIBAN = AccountIBAN;
        this.loanPeriod = LoanPeriod;
        this.interestRate = interestRate;
    }

    public Document toDocument() {
        Document doc = new Document("amountPerMonth",this.amountPerMonth).append("accountIBAN",this.accountIBAN)
                .append("loanPeriod",this.loanPeriod).append("status",this.status).append("interestRate",this.interestRate);
        return doc;
    }

    public void toDatabase(){
        Mongo.coll3.insertOne(toDocument());
        toFile();
    }

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
    public String getAmountPerMonth() {
        return amountPerMonth;
    }

    public void setAmountPerMonth(String amountPerMonth) {
        this.amountPerMonth = amountPerMonth;
    }

    public String getLoanPeriod() {
        return loanPeriod;
    }

    public void setLoanPeriod(String loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public String getAccountIBAN() {
        return accountIBAN;
    }

    public void setAccountIBAN(String accountIBAN) {
        this.accountIBAN = accountIBAN;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String amountPerMonth) {
        this.interestRate = interestRate;
    }






    //juan
    public void changeCheckBox(){
        if(checkbox){
            checkbox = false;
        } else{ checkbox = true; }
    }

    //Linus
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