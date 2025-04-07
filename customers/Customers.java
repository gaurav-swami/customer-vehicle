package customers;

import static printing.Printing.*;

public class Customers {
    public static void main(String args[]) {
        while (true) {
            String menu = """
                    -----------------------------------------------------------------------------------------------
                    1. Display all Customers
                    2. Add a Customer
                    3. Edit a Customer
                    4. Delete a Customer
                    5. Search a Customer
                    6. Exit
                    -----------------------------------------------------------------------------------------------
                                """;
            println(menu);
            int choice = inputInt("Enter a choice");

            try {
                switch (choice) {
                    case 1:
                        DisplayCustomers.main(null);
                        break;
                    case 2:
                        AddCustomer.main(null);
                        break;
                    case 3:
                        EditCustomer.main(null);
                        break;
                    case 4:
                        DeleteCustomer.main(null);
                        break;
                    case 5:
                        SearchCustomer.main(null);
                        break;
                    case 6:

                        return;

                    default:
                        showMsg("Invalid choice! Please try again.");
                }
            } catch (NumberFormatException e) {
                println("Some Error Occured");
            }
        }
    }
}