import java.sql.*;

public class DisplayData {

    public static void main(String[] args) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            // Display CUSTOMERS data
            System.out.println("CUSTOMERS:");
            rs = stmt.executeQuery("SELECT * FROM CUSTOMERS");
            System.out.printf("%-5s %-20s %-50s %-30s %-25s%n", "ID", "Name", "Address", "Phone", "Email");
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-50s %-30s %-25s%n", rs.getInt("customerId"), rs.getString("name"), rs.getString("address"), rs.getString("phone"), rs.getString("email"));
            }
            System.out.println();

            // Display VEHICLE data
            System.out.println("VEHICLE:");
            rs = stmt.executeQuery("SELECT * FROM VEHICLE");
            System.out.printf("%-5s %-10s %-10s %-15s %-15s %-5s%n", "VID", "V_NO", "CustID", "Make", "Model", "Year");
            while (rs.next()) {
                System.out.printf("%-5d %-10s %-10d %-15s %-15s %-5d%n", rs.getInt("VID"), rs.getString("V_NO"), rs.getInt("customerId"), rs.getString("make"), rs.getString("model"), rs.getInt("year"));
            }
            System.out.println();

            // Display SERVICES data
            System.out.println("SERVICES:");
            rs = stmt.executeQuery("SELECT * FROM SERVICES");
            // Increased space for Name and Description
            System.out.printf("%-5s %-30s %-60s %-10s%n", "ID", "Name", "Description", "Price");
            while (rs.next()) {
                // Increased space for Name and Description
                System.out.printf("%-5d %-30s %-60s %-10d%n", rs.getInt("serviceId"), rs.getString("serviceName"), rs.getString("description"), rs.getInt("price"));
            }
            System.out.println();

            // Display MECHANICS data
            System.out.println("MECHANICS:");
            rs = stmt.executeQuery("SELECT * FROM MECHANICS");
            System.out.printf("%-5s %-20s %-15s%n", "ID", "Name", "Phone");
            while (rs.next()) {
                System.out.printf("%-5d %-20s %-15s%n", rs.getInt("mechanicId"), rs.getString("name"), rs.getString("phone"));
            }
            System.out.println();

            // Display SERVICE_BOOKING data
            System.out.println("SERVICE_BOOKING:");
            rs = stmt.executeQuery("SELECT * FROM SERVICE_BOOKING");
            System.out.printf("%-5s %-5s %-5s %-5s %-15s %-15s %-10s %-15s%n", "BID", "VID", "SvcID", "MechID", "Service Date", "Status", "Cost", "Payment Status");
            while (rs.next()) {
                System.out.printf("%-5d %-5d %-5d %-5d %-15s %-15s %-10d %-15s%n", rs.getInt("BID"), rs.getInt("VID"), rs.getInt("serviceId"), rs.getInt("mechanicId"), rs.getDate("SERVICE_DATE"), rs.getString("STATUS"), rs.getInt("TOTAL_COST"), rs.getString("PAYMENT_STATUS"));
            }
            System.out.println();

            // Display SERVICE_HISTORY data
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}