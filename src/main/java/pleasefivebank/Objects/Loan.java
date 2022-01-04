package pleasefivebank.Objects;

public class Loan{
    private String status;
    private long quantity;
    private String dueDate;
    private boolean checkbox;
    private int estPayBack;
    private  int loanPeriod;

    public Loan(){
        this.status = "pending";
        this.quantity = 0;
        this.dueDate = "";
        this.checkbox = false;
        this.estPayBack = 0;
        this.loanPeriod = 0;
    }
    //juan and carlotta
    public Loan(String amountPerMonth,String AccountIBAN,String EstPayBackTime,String status, String LoanPeriod){
        //we make a toDocument and toDatabase here
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
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