package customers;
import static printing.Printing.*;

public class Customers{
  public static void main(String args[]){
    while (true) {
            String menu = """
--------------------------------------------------------------
1. Display all Customers
2. Add a Customer
3. Edit a Customer
4. Delete a Customer
5. Exit
--------------------------------------------------------------
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
                    // case 4:
                    //     //DeleteCustomer.main(null);
                    //     break;
                    case 5:

                        return;
                   
                    default:
                        println( "Invalid choice! Please try again.");
                }
            } catch (NumberFormatException e) {
                println("Some Error Occured");
            }
        }
  }
}   