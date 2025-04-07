package customers;

import java.sql.*;
import static printing.Printing.*;

public class SearchCustomer {

  public static void main(String args[]) {
    String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
    String url = "jdbc:ucanaccess://" + databasePath;

    ResultSet rs = null;
    Connection conn = null;

    try {
      conn = DriverManager.getConnection(url);
      int id = inputInt("Enter the customer ID: ");
      rs = searchRecord(conn, "customerId", "Customers", id);

      if (rs.next()) {
        println("Customer Details:");
        println("Customer ID: " + rs.getInt("customerId"));
        println("Name: " + rs.getString("name"));
        println("Address: " + rs.getString("address"));
        println("Phone: " + rs.getString("phone"));
        println("Email: " + rs.getString("email"));

      } else {
        println("Customer with ID " + id + " not found.");
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