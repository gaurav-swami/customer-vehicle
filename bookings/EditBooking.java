package bookings;

import java.sql.*;
import static printing.Printing.*;

public class EditBooking {

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement checkBooking = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url);

            int bookingId = inputInt("Enter Booking ID to update");
            checkBooking = conn.prepareStatement("select count(*) from service_booking where bid = ?");
            checkBooking.setInt(1,bookingId);
            rs = checkBooking.executeQuery();
            rs.next();
            int bookingCount = rs.getInt(1);

            if (bookingCount== 0){
              println("booking Id does not exist");
              return;
            }
            while (true) {
                String menu = """
--------------------------------------------------------------
1. Update Mechanic ID
2. Update Status 
3. Update Payment Status
5. Exit
--------------------------------------------------------------""";
                println(menu);
                int choice = inputInt("Enter a choice:");

                switch (choice) {
                    case 1:
                        int mechanicId = inputInt("Enter the Mechanic ID");
                        pstmt = conn.prepareStatement("update service_booking SET mechanicId = ? WHERE bid = ?");
                        pstmt.setInt(1, mechanicId );
                        pstmt.setInt(2, bookingId);
                        break;
                    case 2:
                        int state = inputInt("Enter the Status : (1-Completed/0-Pending)");
                        pstmt = conn.prepareStatement("UPDATE service_booking SET status = ? WHERE bid = ?");
                        String status = state==0?"Pending":"Completed";
                        pstmt.setString(1, status);
                        pstmt.setInt(2, bookingId);
                        break;
                    case 3:
                        int payStatus = inputInt("Enter the Payment Status : (1-Paid/0-Unpaid)");
                        pstmt = conn.prepareStatement("UPDATE service_booking SET payment_status = ? WHERE bid = ?");
                        String paymentStatus = payStatus==0?"Unpaid":"Paid";
                        pstmt.setString(1, paymentStatus);
                        pstmt.setInt(2, bookingId);
                        break;
                    // case 4:
                    //     String email = input("Enter the email:");
                    //     pstmt = conn.prepareStatement("UPDATE service_booking SET email = ? WHERE customerid = ?");
                    //     pstmt.setString(1, email);
                    //     pstmt.setInt(2, bookingId);
                    //     break;
                    case 5:
                        return;
                    default:
                        println("Invalid choice! Please try again.");
                        continue;
                }

                if (choice >= 1 && choice <= 4) { 
                    int val = pstmt.executeUpdate();
                    if (val > 0) {
                        println("Row Updated");
                    } else {
                        println("An error occurred or no rows were updated.");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (checkBooking != null) {
                    checkBooking.close();
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