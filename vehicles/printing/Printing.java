package printing;

import javax.swing.JOptionPane;

public class Printing {

    public static void println(String message) {
        System.out.println(message);
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(int message) {
        System.out.println(message);
    }
    public static void println() {
        System.out.println(); 
    }

    public static void print(int message) {
        System.out.print(message);
    }

    public static String input(String message) {
        return JOptionPane.showInputDialog(null, message);
    }

    public static int inputInt(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE;
        }
    }

    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

  
}