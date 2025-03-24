
import static printing.Printing.*;
import customers.Customers;
import vehicles.Vehicles;
import mechanics.Mechanics;
import services.Services;
import bookings.Bookings;

class VehicleManagement {
    public static void main(String args[]) {
        while (true) {
            String menu = """
                    --------------------------------------------------------------
                    1. Manage Customers
                    2. Manage Vehicles
                    3. Manage Mechanics
                    4. Manage Services
                    5. Manage Bookings
                    6. Exit
                    --------------------------------------------------------------
                                """;
            println(menu);

            int choice = inputInt("Enter a choice");

            try {

                switch (choice) {
                    case 1:
                        Customers.main(null);
                        break;
                    case 2:
                        Vehicles.main(null);
                        break;
                    case 3:
                        Mechanics.main(null);
                        break;
                    case 4:
                        Services.main(null);
                        break;
                    case 5:
                        Bookings.main(null);
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        println("Invalid choice! Please try again.");
                }
            } catch (NumberFormatException e) {
                println("Some Error Occured");
            }
        }
    }
}
