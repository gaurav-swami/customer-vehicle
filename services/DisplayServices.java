package services;

import java.sql.*;
import static printing.Printing.*;

public class DisplayServices {

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);
            pstmt = conn.prepareStatement("SELECT * FROM SERVICES");
            rs = pstmt.executeQuery();

            printf("%-12s %-25s %-40s %-10s\n", "Service ID", "Service Name", "Description", "Price");
            printf("--------------------------------------------------------------------------------------\n");

            while (rs.next()) {
                printf("%-12d %-25s %-40s %-10d\n", rs.getInt("serviceId"), rs.getString("serviceName"), rs.getString("description"), rs.getInt("price"));
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