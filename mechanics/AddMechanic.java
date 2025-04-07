package mechanics;

import java.sql.*;
import static printing.Printing.*;

public class AddMechanic {

    public static void main(String args[]) {
      String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
      String url = "jdbc:ucanaccess://" + databasePath;

      Connection conn = null;
      PreparedStatement pstmt = null;

      try {
        conn = DriverManager.getConnection(url);

        int mechanicId = inputInt("Enter the mechanic ID");
        String name = input("Enter the mechanic name");
        String phone = input("Enter the mechanic phone number");

        pstmt = conn.prepareStatement("INSERT INTO MECHANICS (mechanicId, name, phone) VALUES (?, ?, ?)");

        pstmt.setInt(1, mechanicId);
        pstmt.setString(2, name);
        pstmt.setString(3, phone);

        int val = pstmt.executeUpdate();
        if (val > 0) {
          showMsg("Mechanic added successfully");
        } else {
          showMsg("Failed to add mechanic");
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