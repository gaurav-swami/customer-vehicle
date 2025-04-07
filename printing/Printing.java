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

    public static void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public static String input(String message) {
        return JOptionPane.showInputDialog(null, message);
    }

    public static int inputInt(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                message = "Invalid input. " + message;
            }
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

    public static boolean hasPendingBookings(Connection conn, int id, String idColumnName) throws SQLException {
        PreparedStatement checkPending = null;
        ResultSet rs = null;
        try {
            String query = "SELECT COUNT(*) FROM service_booking WHERE " + idColumnName + " = ? AND status = 'Pending'";
            checkPending = conn.prepareStatement(query);
            checkPending.setInt(1, id);
            rs = checkPending.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (checkPending != null) {
                checkPending.close();
            }
        }
        return false;
    }

    public static boolean hasPendingPayment(Connection conn, int id, String idColumnName) throws SQLException {
        PreparedStatement checkPending = null;
        ResultSet rs = null;
        try {
            String query = "SELECT COUNT(*) FROM service_booking WHERE " + idColumnName
                    + " = ? AND payment_status = 'Unpaid'";
            checkPending = conn.prepareStatement(query);
            checkPending.setInt(1, id);
            rs = checkPending.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (checkPending != null) {
                checkPending.close();
            }
        }
        return false;
    }

    public static ResultSet searchRecord(Connection conn, String keyName, String tableName, int id)
            throws SQLException {

        PreparedStatement searchIt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM " + tableName + " WHERE " + keyName + " = ?";
            searchIt = conn.prepareStatement(query);
            searchIt.setInt(1, id);
            rs = searchIt.executeQuery();

            return rs;
        } catch (SQLException e) {

            
            println("An error occurred during searchRecord: " + e.getMessage());
            throw e;

        } finally {
            if (searchIt != null) {
                searchIt.close();
            }
        }

    }

    public static ResultSet searchRecord(Connection conn, String keyName, String tableName, String value)
            throws SQLException {
        PreparedStatement searchIt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM " + tableName + " WHERE " + keyName + " = ?";
            searchIt = conn.prepareStatement(query);
            searchIt.setString(1, value);
            rs = searchIt.executeQuery();

            return rs;
        } catch (SQLException e) {

            println("An error occurred during searchRecord: " + e.getMessage());
            throw e;

        } finally {
            if (searchIt != null) {
                searchIt.close();
            }
            if (rs != null) {
                rs.close();
            }

        }
    }

}