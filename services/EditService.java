package services;

import java.sql.*;
import static printing.Printing.*;

public class EditService {

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(url);

            int serviceId = getValidId(conn, "services", "serviceId");
            while (true) {
                String menu = """
                        --------------------------------------------------------------
                        1. Update Service Name
                        2. Update Description
                        3. Update Price
                        4. Exit
                        --------------------------------------------------------------""";
                println(menu);
                int choice = inputInt("Enter a choice:");

                switch (choice) {
                    case 1:
                        String serviceName = input("Enter the service name:");
                        pstmt = conn.prepareStatement("UPDATE SERVICES SET serviceName = ? WHERE serviceId = ?");
                        pstmt.setString(1, serviceName);
                        pstmt.setInt(2, serviceId);
                        break;
                    case 2:
                        String description = input("Enter the description:");
                        pstmt = conn.prepareStatement("UPDATE SERVICES SET description = ? WHERE serviceId = ?");
                        pstmt.setString(1, description);
                        pstmt.setInt(2, serviceId);
                        break;
                    case 3:
                        int price = inputInt("Enter the price:");
                        pstmt = conn.prepareStatement("UPDATE SERVICES SET price = ? WHERE serviceId = ?");
                        pstmt.setInt(1, price);
                        pstmt.setInt(2, serviceId);
                        break;
                    case 4:
                        return;
                    default:
                        println("Invalid choice! Please try again.");
                        continue;
                }

                if (choice >= 1 && choice <= 3) {
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