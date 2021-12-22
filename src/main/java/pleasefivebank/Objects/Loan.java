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

    public Loan(double total, double totalRent) {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getEstPayBack(){return estPayBack;}

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
