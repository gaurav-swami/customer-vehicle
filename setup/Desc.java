import java.sql.*;

public class Desc {

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            // UCanAccess does not support DESCRIBE TABLE directly.
            // We use a workaround by querying the system tables.
            rs = stmt.executeQuery(
                    "SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE " +
                            "FROM INFORMATION_SCHEMA.COLUMNS " +
                            "WHERE TABLE_NAME = 'SERVICE_BOOKING'");

            System.out.println("Vehicle Table Description:");
            System.out.println("---------------------------");
            System.out.println("Column Name\tData Type\tNullable");
            System.out.println("---------------------------\n");

            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                String dataType = rs.getString("DATA_TYPE");
                String isNullable = rs.getString("IS_NULLABLE");

                System.out.printf("%-15s\t%-10s\t%-8s\n", columnName, dataType, isNullable);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}