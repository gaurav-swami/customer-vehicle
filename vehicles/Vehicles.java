package vehicles;

import static printing.Printing.*;

public class Vehicles {
  public static void main(String args[]) {
    while (true) {
      String menu = """
          --------------------------------------------------------------
          1. Display all Vehicles
          2. Add a Vehicles
          3. Edit a Vehicles
          4. Delete a Vehicles
          5. Search a Vehile
          7. Exit
          --------------------------------------------------------------
                      """;
      println(menu);
      int choice = inputInt("Enter a choice");

      try {
        switch (choice) {
          case 1:
            DisplayVehicles.main(null);
            break;
          case 2:
            AddVehicle.main(null);
            break;
          case 3:
            EditVehicle.main(null);
            break;
          // // case 4:
          // // //DeleteCustomer.main(null);
          // // break;
          case 5:
            SearchVehicle.main(null);
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