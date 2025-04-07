package vehicles;

import java.sql.*;
import static printing.Printing.*;

public class DeleteVehicle {

  public static void main(String args[]) {
    String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
    String url = "jdbc:ucanaccess://" + databasePath;

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = DriverManager.getConnection(url);
      int vehicleId = getValidId(conn, "vehicle", "VID");
      if (hasPendingPayment(conn, vehicleId, "vid")) {
        showMsg("Cannot delete this vehicle as it has pending payment on services. ");
        return;
      }

      if (hasPendingBookings(conn, vehicleId, "vid")) {
        showMsg("Cannot delete the vehicle as it has pending bookings.");
        return;
      }

      PreparedStatement deleteVehicleBookings = conn.prepareStatement(
          "delete from service_booking WHERE vid = ? ");
      deleteVehicleBookings.setInt(1, vehicleId);
      deleteVehicleBookings.executeUpdate();
      deleteVehicleBookings.close();

      pstmt = conn.prepareStatement("delete from vehicle where VID = ?");
      pstmt.setInt(1, vehicleId);

      int val = pstmt.executeUpdate();
      if (val > 0) {
        showMsg("Vehicle deleted successfully.");
      } else {
        showMsg("Vehicle not found or an error occurred.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      println("Database error: " + e.getMessage());
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