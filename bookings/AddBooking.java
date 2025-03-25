package bookings;

import java.sql.*;
import static printing.Printing.*;

public class AddBooking {

  public static void main(String args[]) {
    String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
    String url = "jdbc:ucanaccess://" + databasePath;

    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement checkService = null;
    PreparedStatement checkMechanic = null;
    PreparedStatement checkVehicle = null;
    PreparedStatement checkCost = null;
    ResultSet rs = null;
    try {
      conn = DriverManager.getConnection(url);
      pstmt = conn.prepareStatement(
            "insert into service_booking(bid,vid,serviceId,mechanicId,service_date,status,total_cost,payment_status) values (?,?,?,?,date(),'Pending',?,?);");

      int bid = inputInt("Enter the bid");
      int vid = 0;
      int mechanicId = 0;
      int serviceId = 0;

      String smsg = "Enter the serviceId";
      String mmsg = "Enter the mechanicID";
      String vmsg = "Enter the vid";

      while (true) {

        vid = inputInt(vmsg);
        checkVehicle = conn.prepareStatement("select count(*) from vehicle where vid = ?");
        checkVehicle.setInt(1, vid);
        rs = checkVehicle.executeQuery();
        rs.next();
        int vehicleCount = rs.getInt(1);
        if (vehicleCount == 0) {
          vmsg = "That vid doesn't exist, Enter again";
        } else {
          break;
        }
      }

      while (true) {

        serviceId = inputInt(smsg);
        checkService = conn.prepareStatement("select count(*) from services where serviceId = ?");
        checkService.setInt(1, serviceId);
        rs = checkService.executeQuery();
        rs.next();
        int serviceCount = rs.getInt(1);
        if (serviceCount == 0) {
          smsg = "That serviceId does not exist, Enter again";
        } else {
          break;
        }
      }

      while (true) {

        mechanicId = inputInt(mmsg);
        checkMechanic = conn.prepareStatement("select count(*) from mechanics where mechanicId= ?");
        checkMechanic.setInt(1, mechanicId);
        rs = checkMechanic.executeQuery();
        rs.next();
        int mechanicCount = rs.getInt(1);
        if (mechanicCount == 0) {
          mmsg = "That mechanicId does not exist, Enter again";
        } else {
          break;
        }
      }

      checkCost = conn.prepareStatement("Select price from services where serviceid = ?");
      checkCost.setInt(1, serviceId);
      rs = checkCost.executeQuery();
      rs.next();
      int cost = rs.getInt(1);

      int totalCost = (int) Math.round(cost + cost * 0.28);
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
        println("Rows Inserted");
      } else {
        println("An error occured");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (checkMechanic != null) {
          checkMechanic.close();
        }
        if (checkService != null) {
          checkService.close();
        }
        if (checkCost != null) {
          checkCost.close();
        }
        if (checkVehicle != null) {
          checkVehicle.close();
        }
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