package pleasefivebank.Utillities;


import java.text.DecimalFormat;

public class Utillities {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public static String trunc(double number) {
        return decimalFormat.format(number);
    }

}
