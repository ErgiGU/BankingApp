package pleasefivebank.Objects;
//Linus
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
