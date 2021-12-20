package pleasefivebank.Objects;

public class Loan {
    private String status;
    private long quantity;
    private String dueDate;
    private boolean checkbox;

    public Loan(){
        this.status = "pending";
        this.quantity = 0;
        this.dueDate = "";
        this.checkbox = false;
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

    //juan
    public void changeCheckBox(){
        if(checkbox){
            checkbox = false;
        } else{ checkbox = true; }
    }
}
