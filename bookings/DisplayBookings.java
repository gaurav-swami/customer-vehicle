package bookings;
import java.text.SimpleDateFormat;
import java.sql.*;
import static printing.Printing.*;
public class DisplayBookings {

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);
            pstmt = conn.prepareStatement("Select * from service_booking");
            rs = pstmt.executeQuery(); 

        
            printf("%-6s %-6s %-10s %-12s %-15s %-20s %-10s %-20s\n", "BID", "VID", "ServiceID", "MechanicID", "ServiceDate", "Status", "Cost", "PaymentStatus");
            printf("------------------------------------------------------------------------------------------------------------------------------------\n");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Format for ISO 8601 date

            while (rs.next()) {
                String formattedDate = dateFormat.format(rs.getDate("SERVICE_DATE")); // Format the date

                printf("%-6d %-6d %-10d %-12d %-15s %-20s %-10d %-20s\n",
                      rs.getInt("BID"),
                      rs.getInt("VID"),
                      rs.getInt("serviceId"),
                      rs.getInt("mechanicId"),
                      formattedDate, // Use the formatted date
                      rs.getString("STATUS"),
                      rs.getInt("TOTAL_COST"),
                      rs.getString("PAYMENT_STATUS")); // Get and print PAYMENT_STATUS
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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