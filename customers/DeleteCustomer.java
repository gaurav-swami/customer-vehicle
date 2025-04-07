package customers;

import java.sql.*;
import static printing.Printing.*;

public class DeleteCustomer {

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url);
            pstmt = conn.prepareStatement("DELETE FROM customers WHERE email = ?");

            String email = "saiyamsomani@gmail.com"; // fixed email
            pstmt.setString(1, email);

            int val = pstmt.executeUpdate();
            if (val > 0) {
                showMsg("Customer deleted");
            } else {
                showMsg("No customer found with that email");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
