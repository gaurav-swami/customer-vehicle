package mechanics;

import java.sql.*;
import static printing.Printing.*;

//103          Vikram Singh              9027420026

public class DeleteMechanic {

  public static void main(String args[]) {
    String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
    String url = "jdbc:ucanaccess://" + databasePath;

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = DriverManager.getConnection(url);
      int mechanicId = getValidId(conn, "mechanics", "mechanicId");

      if (hasPendingBookings(conn, mechanicId, "mechanicId")) {
        showMsg("Cannot delete the mechanic as this mechanic has pending services.");
        return; // Exit the main method
      }

      PreparedStatement updateBookings = conn.prepareStatement(
          "UPDATE service_booking SET mechanicId = ? WHERE mechanicId = ? AND status = 'Completed'");
      updateBookings.setInt(1, 0);
      updateBookings.setInt(2, mechanicId);
      updateBookings.executeUpdate();
      updateBookings.close();

      pstmt = conn.prepareStatement("delete from mechanics where mechanicId = ?");
      pstmt.setInt(1, mechanicId);

      int val = pstmt.executeUpdate();
      if (val > 0) {
        showMsg("Mechanic Deleted Successfully");
      } else {
        showMsg("An error occurred");
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