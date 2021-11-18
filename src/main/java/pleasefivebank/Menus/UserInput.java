package pleasefivebank.Menus;

import java.util.Scanner;

public class UserInput {
    private static Scanner input = new Scanner(System.in);

    public static byte readByte(String message) {
        System.out.print(message);
        byte value = input.nextByte();
        input.nextLine();
        return value;
    }

    public static double readDouble(String message) {
        System.out.print(message);
        double value = input.nextDouble();
        input.nextLine();
        return value;
    }

    public static int readInt(String message) {
        System.out.print(message);
        int numOfStudents = input.nextInt();
        input.nextLine();
        return numOfStudents;
    }

    public static String readLine(String message) {
        System.out.print(message);
        String sentence = input.nextLine();
        return sentence;
    }
}

