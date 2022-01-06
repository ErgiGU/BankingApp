package pleasefivebank.Objects;

import org.bson.Document;
import pleasefivebank.Mongo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Loan{
    //attributes for the loan
    private String status;
    private double totalAmount;
    private double amountPerMonth;
    private int loanPeriod;
    private String accountIBAN;
    private double amountLeft;

    //this two attributes are not saved because they are not relevant to the user
    //Linus
    public Loan(double amountPerMonth, String accountIBAN,int loanPeriod, double totalAmount, double amountLeft,String status){
        this.status = status;
        this.accountIBAN = accountIBAN;
        this.amountPerMonth = amountPerMonth;
        this.amountLeft = amountLeft;
        this.loanPeriod = loanPeriod;
        this.totalAmount = totalAmount;
    }

    //method to convert a loan into a object
    public Document toDocument() {
        Document doc = new Document("amountPerMonth",this.amountPerMonth).append("accountIBAN",this.accountIBAN).append("totalAmount",this.totalAmount)
                .append("amountLeft", this.amountLeft).append("loanPeriod",this.loanPeriod).append("status",this.status);
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

    public double getAmountLeft() {return amountLeft;}

    public void setAmountLeft(double amountLeft) {this.amountLeft = amountLeft;}

    public double getAmountPerMonth() {return amountPerMonth;}

    public double getTotalAmount() {return totalAmount;}

    public int getLoanPeriod() {return loanPeriod;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    //Linus
    //this makes an interest object to showcase information in the loans GUI page
    public static Interest totalCosts(double amount, int estPayBack, int loanPeriod){
        //the quantity the user specifies are monthly payments
        amount = amount*(loanPeriod*12);
        double monthlyPayBack = amount/estPayBack/12;
        double total = 0;
        double interestOnly =0;
        double originalQuantity = amount;

        for(int i = estPayBack*12; i>0; i--) {

            double interest = amount * (0.0214/12); //Our interest rate
            amount = amount - monthlyPayBack;
            double paybackWRent = monthlyPayBack + interest;
            total = total + paybackWRent;
            interestOnly = total - originalQuantity;
        }
        return new Interest(total, interestOnly);
    }
}