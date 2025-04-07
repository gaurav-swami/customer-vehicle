package mechanics;

import static printing.Printing.*;

public class Mechanics {
    public static void main(String args[]) {
        while (true) {
            String menu = """
                    --------------------------------------------------------------
                    1. Display all Mechanics
                    2. Add a Mechanic
                    3. Edit a Mechanic
                    4. Delete a Mechanic
                    6. Search a Mechanic
                    5. Exit
                    --------------------------------------------------------------
                                        """;
            println(menu);
            int choice = inputInt("Enter a choice");

            try {
                switch (choice) {
                    case 1:
                        DisplayMechanics.main(null);
                        break;
                    case 2:
                        AddMechanic.main(null);
                        break;
                    case 3:
                        EditMechanic.main(null);
                        break;
                    case 4:
                        DeleteMechanic.main(null);
                        break;
                    case 5:
                        SearchMechanic.main(null);
                        break;
                    case 6:
                        return;
                    default:
                        showMsg("Invalid choice! Please try again.");
                }
            } catch (NumberFormatException e) {
                showMsg("Some Error Occurred");
            }
        }
    }
}