package services;

import java.sql.*;
import static printing.Printing.*;

public class SearchService {

  public static void main(String args[]) {
    String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
    String url = "jdbc:ucanaccess://" + databasePath;

    ResultSet rs = null;
    Connection conn = null;

    try {
      conn = DriverManager.getConnection(url);
      String menu = """
          -----------------------------------------------------------------------------------------------
          Enter the column to search through service
          1. Service Id
          2. Service Name
          -----------------------------------------------------------------------------------------------
                      """;

      println(menu);

      int choice = inputInt("Enter your choice ");

      switch (choice) {
        case 1:
          int id = inputInt("Enter the Service ID: ");
          rs = searchRecord(conn, "serviceId", "SERVICES", id);
          break;

        case 2:
          String serviceName = input("Enter the Service Name: ");
          rs = searchRecord(conn, "serviceName", "SERVICES", serviceName);
          break;

        default:
          println("Invalid Choice");
          return;
      }

      if (rs.next()) {

        println("Service Details:\n");
        println("Service ID: " + rs.getInt("serviceId"));
        println("Service Name: " + rs.getString("serviceName"));
        println("Description: " + rs.getString("description"));
        println("Price: " + rs.getInt("price") + "\n");

      } else {
        println("Service was not found.");
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