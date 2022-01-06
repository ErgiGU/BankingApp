package pleasefivebank.Utilities;


import java.text.DecimalFormat;


//linus
//method to truncate numbers for the loans page
public class Utilities {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public static double trunc(double number) {
        return Double.parseDouble(decimalFormat.format(number));
    }

}
