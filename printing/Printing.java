package printing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Printing {

    public static void println(String message) {
        System.out.println(message);
    }

    public static void println() {
        System.out.println();
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(int message) {
        System.out.println(message);
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

    public static int getValidId(Connection conn, String tableName, String idColumnName) throws SQLException {
        PreparedStatement checkId = null;
        ResultSet rs = null;
        int id;
        String promptMessage = "Enter the " + idColumnName + "";
        String errorMessage = "This " + idColumnName + " doesn't exist. Please Enter again";
        while (true) {
            id = inputInt(promptMessage);
            checkId = conn.prepareStatement("Select count(*) from " + tableName + " where " + idColumnName + " = ?");
            checkId.setInt(1, id);
            rs = checkId.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            if (count == 0) {
                promptMessage = errorMessage;
            } else {
                checkId.close();
                rs.close();
                return id;
            }
        }
    }
}