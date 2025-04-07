package vehicles;

import java.sql.*;
import static printing.Printing.*;

public class DisplayVehicles {

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);
            pstmt = conn.prepareStatement("Select * from vehicle");
            rs = pstmt.executeQuery();

            println(line);

          
            printf("%-6s ", "VID");
            printf("%-20s ", "Vehicle No");
            printf("%-6s ", "CID");
            printf("%-20s ", "Make");
            printf("%-20s ", "Model");
            printf("%-6s ", "Year");
            println();

            println(line);

            while (rs.next()) {
                printf("%-6d ", rs.getInt("VID"));
                printf("%-20s ", rs.getString("V_NO"));
                printf("%-6d ", rs.getInt("customerId"));
                printf("%-20s ", rs.getString("make"));
                printf("%-20s ", rs.getString("model"));
                printf("%-6d ", rs.getInt("year"));
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