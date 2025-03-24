

import java.sql.*;

public class ListTables {

    public static void main(String[] args) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb"; // Your database path
        String url = "jdbc:ucanaccess://" + databasePath;

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            String dropTableSQL = "DROP TABLE SERVICE_HISTORY"; // Replace with your table name

            stmt.executeUpdate(dropTableSQL);

            System.out.println("SERVICE_HISTORY table deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}