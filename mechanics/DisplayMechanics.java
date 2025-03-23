package mechanics;

import java.sql.*;
import static printing.Printing.*;

public class DisplayMechanics {

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);
            pstmt = conn.prepareStatement("SELECT * FROM MECHANICS");
            rs = pstmt.executeQuery();

            printf("%-12s %-25s %-20s\n", "Mechanic ID", "Name", "Phone");
            printf("----------------------------------------------------------\n");

            while (rs.next()) {
                printf("%-12d %-25s %-20s\n", rs.getInt("mechanicId"), rs.getString("name"), rs.getString("phone"));
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