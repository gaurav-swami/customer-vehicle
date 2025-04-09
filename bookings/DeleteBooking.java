package bookings;

import java.sql.*;
import static printing.Printing.*;

public class DeleteBooking {

  public static void main(String args[]) {
    String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
    String url = "jdbc:ucanaccess://" + databasePath;

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = DriverManager.getConnection(url);
      int bookingId = getValidId(conn, "service_booking", "bid");
      if (hasPendingPayment(conn, bookingId, "bid")) {
        showMsg("Cannot delete this booking as it has pending payment on services. ");
        return;
      }

      if (hasPendingBookings(conn, bookingId, "bid")) {
        showMsg("Cannot delete the booking as the service is still pending.");
        return;
      }

      pstmt = conn.prepareStatement("delete from service_booking where bid = ?");
      pstmt.setInt(1, bookingId);

      int val = pstmt.executeUpdate();
      if (val > 0) {
        showMsg("Booking deleted successfully.");
      } else {
        showMsg("an error occurred.");
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