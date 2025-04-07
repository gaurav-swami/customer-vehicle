package customers;

import java.sql.*;
import static printing.Printing.*;

public class AddCustomer {

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url);
            pstmt = conn
                    .prepareStatement("insert into customers(customerId,name,address,phone,email) values (?,?,?,?,?);");

            int id = inputInt("Enter the id");
            String name = input("Enter the name");
            String address = input("Enter the address");
            String phone = input("Enter the phone");
            String email = input("Enter the email");

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);

            int val = pstmt.executeUpdate();
            if (val > 0) {
                showMsg("Row Inserted");
            } else {
                showMsg("An error occured");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {

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