package services;

import java.sql.*;
import static printing.Printing.*;

public class DeleteService {

  public static void main(String args[]) {
    String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
    String url = "jdbc:ucanaccess://" + databasePath;

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = DriverManager.getConnection(url);
      int serviceId = getValidId(conn, "services", "serviceId");

      if (hasPendingBookings(conn, serviceId, "serviceId")) {
        println("Cannot delete the service as it has pending bookings.");
        return;
      }

      PreparedStatement updateCompletedBookings = conn.prepareStatement(
          "UPDATE service_booking SET serviceId = ? WHERE serviceId = ? AND status = 'Completed'");
      updateCompletedBookings.setInt(1, 0);
      updateCompletedBookings.setInt(2, serviceId);
      updateCompletedBookings.executeUpdate();
      updateCompletedBookings.close();

      pstmt = conn.prepareStatement("delete from services where serviceId = ?");
      pstmt.setInt(1, serviceId);

      int val = pstmt.executeUpdate();
      if (val > 0) {
        println("Service deleted successfully.");
      } else {
        println("Service not found or an error occurred.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      System.err.println("Database error: " + e.getMessage());
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