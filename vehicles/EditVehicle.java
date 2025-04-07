package vehicles;

import java.sql.*;
import static printing.Printing.*;

public class EditVehicle {

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        PreparedStatement pstmt = null;

        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);

            int vehicleId = getValidId(conn, "vehicle", "vid");

            while (true) {
                String menu = """
                        --------------------------------------------------------------
                        1. Update Vehicle Number
                        2. Update Customer ID
                        3. Update Make
                        4. Update Model
                        5. Update Year
                        6. Exit
                        --------------------------------------------------------------""";
                println(menu);
                int choice = inputInt("Enter a choice:");

                switch (choice) {
                    case 1:
                        String vNo = input("Enter the vehicle number:");
                        pstmt = conn.prepareStatement("UPDATE vehicle SET V_NO = ? WHERE VID = ?");
                        pstmt.setString(1, vNo);
                        pstmt.setInt(2, vehicleId);
                        break;
                    case 2:
                        int customerId = inputInt("Enter the customer ID:");
                        pstmt = conn.prepareStatement("UPDATE vehicle SET customerId = ? WHERE VID = ?");
                        pstmt.setInt(1, customerId);
                        pstmt.setInt(2, vehicleId);
                        break;
                    case 3:
                        String make = input("Enter the make:");
                        pstmt = conn.prepareStatement("UPDATE vehicle SET make = ? WHERE VID = ?");
                        pstmt.setString(1, make);
                        pstmt.setInt(2, vehicleId);
                        break;
                    case 4:
                        String model = input("Enter the model:");
                        pstmt = conn.prepareStatement("UPDATE vehicle SET model = ? WHERE VID = ?");
                        pstmt.setString(1, model);
                        pstmt.setInt(2, vehicleId);
                        break;
                    case 5:
                        int year = inputInt("Enter the year:");
                        pstmt = conn.prepareStatement("UPDATE vehicle SET year = ? WHERE VID = ?");
                        pstmt.setInt(1, year);
                        pstmt.setInt(2, vehicleId);
                        break;
                    case 6:
                        return;
                    default:
                        showMsg("Invalid choice! Please try again.");
                        continue;
                }

                if (choice >= 1 && choice <= 5) {
                    int val = pstmt.executeUpdate();
                    if (val > 0) {
                        showMsg("Row Updated");
                    } else {
                        showMsg("An error occurred or no rows were updated.");
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
                if (rs != null) {
                    rs.close();
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