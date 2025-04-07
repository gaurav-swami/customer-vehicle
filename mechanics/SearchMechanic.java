package mechanics;

import java.sql.*;
import static printing.Printing.*;

public class SearchMechanic {

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
          1. Mechanic ID
          2. Mechanic Mobile Number
          -----------------------------------------------------------------------------------------------
                              """;

      println(menu);

      int choice = inputInt("Enter your choice ");

      switch (choice) {
        case 1:
          int id = inputInt("Enter the Mechanic ID: ");
          rs = searchRecord(conn, "mechanicId", "MECHANICS", id);
          break;

        case 2:
          String phone = input("Enter the Mechanic Mobile Number: ");
          rs = searchRecord(conn, "phone", "MECHANICS", phone);
          break;

        default:
          println("Invalid Choice");
          return;
      }

      if (rs.next()) {

        println("Mechanic Details:\n");
        println("Mechanic ID: " + rs.getInt("mechanicId"));
        println("Name: " + rs.getString("name"));
        println("Phone: " + rs.getString("phone") + "\n");

      } else {
        println("Mechanic was not found.");
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