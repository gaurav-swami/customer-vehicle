package bookings;

import static printing.Printing.*;

public class Bookings {
    public static void main(String args[]) {
        while (true) {
            String menu = """
                    --------------------------------------------------------------
                    1. Display all Service Bookings
                    2. Add a Service Booking
                    3. Edit a Service Booking
                    4. Delete a Service Booking
                    5. Search a Service Booking
                    5. Exit
                    --------------------------------------------------------------
                                """;
            println(menu);
            int choice = inputInt("Enter a choice");

            try {
                switch (choice) {
                    case 1:
                        DisplayBookings.main(null);
                        break;
                    case 2:
                        AddBooking.main(null);
                        break;
                    case 3:
                        EditBooking.main(null);
                        break;
                    case 4:
                        DeleteBooking.main(null);
                        break;
                    case 5:
                        SearchBooking.main(null);
                        break;
                    case 6:
                        return;

                    default:
                        println("Invalid choice! Please try again.");
                }
            } catch (NumberFormatException e) {
                println("Some Error Occured");
            }
        }
    }
}