package mechanics;

import java.sql.*;
import static printing.Printing.*;

public class EditMechanic {

    public static void main(String args[]) {
        String databasePath = "C:/Users/gaura/VM/VM/javadatabase.mdb";
        String url = "jdbc:ucanaccess://" + databasePath;

        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement checkMechanic = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url);

            int mechanicId = inputInt("Enter Mechanic ID to update");

            checkMechanic = conn.prepareStatement("SELECT COUNT(*) FROM MECHANICS WHERE mechanicId = ?");
            checkMechanic.setInt(1, mechanicId);
            rs = checkMechanic.executeQuery();
            rs.next();
            int mechanicCount = rs.getInt(1);

            if (mechanicCount == 0) {
                println("Mechanic ID does not exist");
                return;
            }

            while (true) {
                String menu = """
--------------------------------------------------------------
1. Update Name
2. Update Phone
3. Exit
--------------------------------------------------------------""";
                println(menu);
                int choice = inputInt("Enter a choice:");

                switch (choice) {
                    case 1:
                        String name = input("Enter the name:");
                        pstmt = conn.prepareStatement("UPDATE MECHANICS SET name = ? WHERE mechanicId = ?");
                        pstmt.setString(1, name);
                        pstmt.setInt(2, mechanicId);
                        break;
                    case 2:
                        String phone = input("Enter the phone:");
                        pstmt = conn.prepareStatement("UPDATE MECHANICS SET phone = ? WHERE mechanicId = ?");
                        pstmt.setString(1, phone);
                        pstmt.setInt(2, mechanicId);
                        break;
                    case 3:
                        return;
                    default:
                        println("Invalid choice! Please try again.");
                        continue;
                }

                if (choice >= 1 && choice <= 2) {
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
                if (rs != null) {
                    rs.close();
                }
                if (checkMechanic != null) {
                    checkMechanic.close();
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