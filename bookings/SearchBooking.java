package bookings;

import java.sql.*;
import static printing.Printing.*;

public class SearchBooking {

  public static void main(String args[]) {
    String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
    String url = "jdbc:ucanaccess://" + databasePath;

    ResultSet rs = null;
    Connection conn = null;

    try {
      conn = DriverManager.getConnection(url);
      int bid = inputInt("Enter Booking ID: ");
      rs = searchRecord(conn, "BID", "SERVICE_BOOKING", bid);

      if (rs.next()) {
        println("Booking Details:\n");
        println("Booking ID: " + rs.getInt("BID"));
        println("Vehicle ID: " + rs.getInt("VID"));
        println("Service ID: " + rs.getInt("serviceId"));
        println("Mechanic ID: " + rs.getInt("mechanicId"));
        println("Service Date: " + rs.getDate("SERVICE_DATE"));
        println("Status: " + rs.getString("STATUS"));
        println("Total Cost: " + rs.getInt("TOTAL_COST") + "\n");
      } else {
        showMsg("Booking was not found.");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
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