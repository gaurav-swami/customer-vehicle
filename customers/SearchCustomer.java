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
      String menu = """
          -----------------------------------------------------------------------------------------------
          Search by column
          1. Customer Id
          2. Customer Mobile
          3. Customer Email
          -----------------------------------------------------------------------------------------------
                      """;

      println(menu);

      int choice = inputInt("Enter your choice ");

      switch (choice) {
        case 1:
          int id = inputInt("Enter the Customer ID: ");
          rs = searchRecord(conn, "customerId", "Customers", id);
          break;

        case 2:
          String mob = input("Enter the Customer ID: ");
          rs = searchRecord(conn, "mobile", "Customers", mob);
          break;

        case 3:
          String email = input("Enter the Customer Email");
          rs = searchRecord(conn, "email", "Customers", email);
          break;

        default:
          showMsg("Invalid Choice");
          break;
      }

      if (rs.next()) {

        println("Customer Details:\n");
        println("Customer ID: " + rs.getInt("customerId"));
        println("Name: " + rs.getString("name"));
        println("Address: " + rs.getString("address"));
        println("Phone: " + rs.getString("phone"));
        println("Email: " + rs.getString("email") + "\n");

      } else {
        showMsg("Customer was not found.");
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