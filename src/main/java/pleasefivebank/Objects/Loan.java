package pleasefivebank.Objects;

public class Loan {
    private String status;
    private long quantity;
    private String dueDate;

    public Loan(String status, long quantity, String dueDate){
        this.status = "pending";
        this.quantity = quantity;
        this.dueDate = dueDate;
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
}
