package bookings;

import java.sql.*;
import static printing.Printing.*;

public class AddBooking {

  public static void main(String args[]) {
    String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
    String url = "jdbc:ucanaccess://" + databasePath;

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
      conn = DriverManager.getConnection(url);
      pstmt = conn.prepareStatement(
          "insert into service_booking(bid,vid,serviceId,mechanicId,service_date,status,total_cost,payment_status) values (?,?,?,?,date(),'Pending',?,?);");

      int bid = inputInt("Enter the bid");
      int vid = getValidId(conn, "vehicle", "vid");
      int mechanicId = getValidId(conn, "mechanics", "mechanicId");
      int serviceId = getValidId(conn, "services", "serviceId");
      int totalCost = getTotalCost(conn, serviceId); // function defined under this main function 
      int payStatus = inputInt("Enter the status(paid-1/unpaid-0)");
      String paymentStatus = payStatus == 0 ? "Unpaid" : "Paid";

      pstmt.setInt(1, bid);
      pstmt.setInt(2, vid);
      pstmt.setInt(3, serviceId);
      pstmt.setInt(4, mechanicId);
      pstmt.setInt(5, totalCost);
      pstmt.setString(6, paymentStatus);

      int val = pstmt.executeUpdate();
      if (val > 0) {
        showMsg("Rows Inserted");
      } else {
        showMsg("An error occured");
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

  public static int getTotalCost(Connection conn, int serviceId) throws SQLException {
    PreparedStatement checkCost = conn.prepareStatement("Select price from services where serviceid = ?");
    checkCost.setInt(1, serviceId);
    ResultSet rs = checkCost.executeQuery();
    rs.next();
    int cost = rs.getInt(1);
    int totalCost = (int) Math.round(cost + cost * 0.28);
    checkCost.close();
    rs.close();
    return totalCost;
  }
}