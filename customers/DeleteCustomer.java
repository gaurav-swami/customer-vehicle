package customers;

import java.sql.*;
import static printing.Printing.*;
public class DeleteCustomer{

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        PreparedStatement pstmt = null;
        

        try {
          conn = DriverManager.getConnection(url);
          pstmt = conn.prepareStatement("delete from customers where id = ?");
          
          int id = inputInt("Enter the id");
          int val = pstmt.executeUpdate();
          if (val>0){
            println("Rows Inserted");
          }
          else{
            println("An error occured");
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
}