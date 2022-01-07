package pleasefivebank.Objects;
//Linus
//this object is made to showcase total cost and rent in the GUI for the loans page
public class Interest  {
    private  double total;
    private double rentOnly;
    public Interest(double total, double rentOnly) {
        this.total = total;
        this.rentOnly = rentOnly;
    }
    public double getTotal(){ return total;}
    public  double getRentOnly(){return  rentOnly;}
}
