package vehicles;

import java.sql.*;
import static printing.Printing.*;

public class SearchVehicle {

  public static void main(String args[]) {
    String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
    String url = "jdbc:ucanaccess://" + databasePath;

    ResultSet rs = null;
    Connection conn = null;

    try {
      conn = DriverManager.getConnection(url);
      String menu = """
          -----------------------------------------------------------------------------------------------
          Enter the column
          1. Vehicle ID
          2. Customer Number
          -----------------------------------------------------------------------------------------------
                      """;

      println(menu);

      int choice = inputInt("Enter your choice ");

      switch (choice) {
        case 1:
          int id = inputInt("Enter the Vehicle ID: ");
          rs = searchRecord(conn, "vid", "vehicle", id);
          break;

        case 2:
          String vno = input("Enter the Vehicle Number: ");
          rs = searchRecord(conn, "v_no", "vehicle", vno);
          break;

        default:
          println("Invalid Choice");
          break;
      }

      if (rs.next()) {

        println("Vehicle Details:\n");
        println("Vehicle ID: " + rs.getInt("VID"));
        println("Vehicle Number: " + rs.getString("V_NO"));
        println("Customer ID: " + rs.getInt("customerId"));
        println("Make: " + rs.getString("make"));
        println("Model: " + rs.getString("model"));
        println("Year: " + rs.getInt("year") + "\n");

      } else {
        println("Vehicle was not found.");
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
