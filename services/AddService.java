package services;

import java.sql.*;
import static printing.Printing.*;

public class AddService {

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url);

            int serviceId = inputInt("Enter the service ID");
            String serviceName = input("Enter the service name");
            String description = input("Enter the service description");
            int price = inputInt("Enter the service price");

            pstmt = conn.prepareStatement(
                    "INSERT INTO SERVICES (serviceId, serviceName, description, price) VALUES (?, ?, ?, ?)");

            pstmt.setInt(1, serviceId);
            pstmt.setString(2, serviceName);
            pstmt.setString(3, description);
            pstmt.setInt(4, price);

            int val = pstmt.executeUpdate();
            if (val > 0) {
                showMsg("Service added successfully");
            } else {
                showMsg("Failed to add service");
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