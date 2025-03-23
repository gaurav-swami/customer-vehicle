package customers;

import java.sql.*;
import static printing.Printing.*;

public class EditCustomer {

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url);

            int customerId = inputInt("Enter Customer ID to update");

            while (true) {
                String menu = """
--------------------------------------------------------------
1. Update Name
2. Update Address
3. Update phone
4. Update email
5. Exit
--------------------------------------------------------------""";
                println(menu);
                int choice = inputInt("Enter a choice:");

                switch (choice) {
                    case 1:
                        String name = input("Enter the name:");
                        pstmt = conn.prepareStatement("UPDATE customers SET name = ? WHERE customerid = ?");
                        pstmt.setString(1, name);
                        pstmt.setInt(2, customerId);
                        break;
                    case 2:
                        String address = input("Enter the address:");
                        pstmt = conn.prepareStatement("UPDATE customers SET address = ? WHERE customerid = ?");
                        pstmt.setString(1, address);
                        pstmt.setInt(2, customerId);
                        break;
                    case 3:
                        String phone = input("Enter the phone:");
                        pstmt = conn.prepareStatement("UPDATE customers SET phone = ? WHERE customerid = ?");
                        pstmt.setString(1, phone);
                        pstmt.setInt(2, customerId);
                        break;
                    case 4:
                        String email = input("Enter the email:");
                        pstmt = conn.prepareStatement("UPDATE customers SET email = ? WHERE customerid = ?");
                        pstmt.setString(1, email);
                        pstmt.setInt(2, customerId);
                        break;
                    case 5:
                        return;
                    default:
                        println("Invalid choice! Please try again.");
                        continue;
                }

                if (choice >= 1 && choice <= 4) { 
                    int val = pstmt.executeUpdate();
                    if (val > 0) {
                        println("Row Updated");
                    } else {
                        println("An error occurred or no rows were updated.");
                    }
                }
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