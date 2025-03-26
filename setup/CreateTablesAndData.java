import java.sql.*;

public class CreateTablesAndData {

    public static void main(String[] args) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

            // Create CUSTOMERS table
            stmt.executeUpdate("""
                    CREATE TABLE CUSTOMERS (
                        customerId INT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        address VARCHAR(255),
                        phone VARCHAR(20) UNIQUE,
                        email VARCHAR(255) UNIQUE
                    )
                    """);
            System.out.println("CUSTOMERS table created.");

            // Create VEHICLE table
            stmt.executeUpdate("""
                    CREATE TABLE VEHICLE (
                        VID INT PRIMARY KEY,
                        V_NO VARCHAR(20) UNIQUE,
                        customerId INT,
                        make VARCHAR(255),
                        model VARCHAR(255),
                        year INT,
                        FOREIGN KEY (customerId) REFERENCES CUSTOMERS(customerId)
                    )
                    """);
            System.out.println("VEHICLE table created.");

            // Create SERVICES table
            stmt.executeUpdate("""
                    CREATE TABLE SERVICES (
                        serviceId INT PRIMARY KEY,
                        serviceName VARCHAR(255) NOT NULL,
                        description TEXT,
                        price INT
                    )
                    """);
            System.out.println("SERVICES table created.");

            // Create MECHANICS table
            stmt.executeUpdate("""
                    CREATE TABLE MECHANICS (
                        mechanicId INT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        phone VARCHAR(20)
                    )
                    """);
            System.out.println("MECHANICS table created.");

            // Create SERVICE_BOOKING table
            stmt.executeUpdate("""
                    CREATE TABLE SERVICE_BOOKING (
                        BID INT PRIMARY KEY,
                        VID INT,
                        serviceId INT,
                        mechanicId INT,
                        SERVICE_DATE DATE,
                        STATUS VARCHAR(50),
                        TOTAL_COST INT,
                        FOREIGN KEY (VID) REFERENCES VEHICLE(VID),
                        FOREIGN KEY (serviceId) REFERENCES SERVICES(serviceId),
                        FOREIGN KEY (mechanicId) REFERENCES MECHANICS(mechanicId)
                    )
                    """);
            System.out.println("SERVICE_BOOKING table created.");

            // Create SERVICE_HISTORY table

            System.out.println("All tables created.");

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