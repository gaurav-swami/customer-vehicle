import java.sql.*;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InsertData{

    public static void main(String[] args) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;
        Connection conn = null;
        Statement stmt = null;
        Random rand = new Random();

        try {
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            // Delete existing SERVICE_BOOKING data
            stmt.executeUpdate("DELETE FROM SERVICE_BOOKING");
            System.out.println("Existing SERVICE_BOOKING data deleted.");

            for (int i = 0; i < 10; i++) {
                int bookingId = 101 + i;
                int vehicleId = 101 + rand.nextInt(10); // Random vehicle from available vehicles
                int serviceId = 101 + rand.nextInt(10); // Random service from available services
                int mechanicId = 101 + rand.nextInt(10); // Random mechanic from available mechanics

                // Generate a random date within a realistic range (e.g., last 2 years)
                LocalDate startDate = LocalDate.now().minusYears(2);
                long startEpochDay = startDate.toEpochDay();
                long endEpochDay = LocalDate.now().toEpochDay();
                long randomEpochDay = startEpochDay + rand.nextInt((int) (endEpochDay - startEpochDay));
                LocalDate serviceDate = LocalDate.ofEpochDay(randomEpochDay);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDate = serviceDate.format(formatter);
                String serviceStatus = rand.nextBoolean() ? "Completed" : "Pending";
                String paymentStatus = rand.nextBoolean() ? "Paid" : "Unpaid";
                int totalCost = rand.nextInt(5000) + 1000;

                // Insert into SERVICE_BOOKING
                stmt.executeUpdate("INSERT INTO SERVICE_BOOKING (BID, VID, serviceId, mechanicId, SERVICE_DATE, STATUS, TOTAL_COST, PAYMENT_STATUS) VALUES (" + bookingId + ", " + vehicleId + ", " + serviceId + ", " + mechanicId + ", '" + formattedDate + "', '" + serviceStatus + "', " + totalCost + ", '" + paymentStatus + "')");
            }

            System.out.println("10 SERVICE_BOOKING records inserted with realistic dates and payment status.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}