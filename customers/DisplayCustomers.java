package customers;

import java.sql.*;
import static printing.Printing.*;

public class DisplayCustomers {

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);
            pstmt = conn.prepareStatement("Select * from customers");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                printf("%-6d ", rs.getInt("customerId"));
                printf("%-20s ", rs.getString("name"));
                printf("%-50s ", rs.getString("address"));
                printf("%-10s ", rs.getString("phone"));
                printf("%-30s ", rs.getString("email"));
                println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
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