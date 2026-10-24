import java.util.Scanner;

/**
 * Main class: a small console program that lets a customer buy pets.
 * It shows selection (if / switch), loops (for / while) and
 * attribute changes through getters and setters.
 */
public class PetShopMain {

    public static void main(String[] args) {

        // ----- Create objects using both constructors -----
        Pet[] pets = new Pet[5];
        pets[0] = new Pet("P001", "Mochi", "Cat", 4, 850.00, true, true);
        pets[1] = new Pet("P002", "Buddy", "Dog", 10, 1500.00, true, true);
        pets[2] = new Pet("P003", "Cotton", "Rabbit", 3, 120.00, false, true);
        pets[3] = new Pet("P004", "Nemo", "Fish", 8, 25.00, true, true);
        pets[4] = new Pet();                       // default constructor
        pets[4].setPetId("P005");                  // fill in through setters
        pets[4].setName("Coco");
        pets[4].setSpecies("Hamster");
        pets[4].setAgeInMonths(5);
        pets[4].setPrice(45.00);
        pets[4].setVaccinated(true);

        Customer customer = new Customer();        // default constructor
        customer.setCustomerId("C001");
        customer.setName("Aiman");
        customer.setPhone("012-3456789");
        customer.setTotalSpent(900.00);
        customer.setLoyaltyPoints(90);

        Customer guest = new Customer("C002", "Sofea", "019-8765432", "Gold", 320, 3200.00, true);

        Scanner input = new Scanner(System.in);

        // ----- 1. Loop through the inventory (for loop) -----
        System.out.println("===== PET INVENTORY =====");
        for (int i = 0; i < pets.length; i++) {
            pets[i].displayInfo();
        }

        System.out.println("\n===== CUSTOMERS =====");
        customer.displayInfo();
        guest.displayInfo();

        // ----- 2. Selection: check each pet before selling (if / else if) -----
        System.out.println("\n===== SALE CHECK =====");
        for (Pet p : pets) {
            if (p.canBeSold()) {
                System.out.println(p.getName() + " is ready to be sold.");
            } else if (!p.isVaccinated()) {
                System.out.println(p.getName() + " cannot be sold yet (not vaccinated).");
            } else {
                System.out.println(p.getName() + " is no longer available.");
            }
        }

        // ----- 3. Menu loop (while + switch) -----
        System.out.println("\n===== SHOP MENU for " + customer.getName() + " =====");
        boolean running = true;
        while (running) {
            System.out.println("\n1. Buy a pet");
            System.out.println("2. View my account");
            System.out.println("3. Update my phone number");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int choice = input.hasNextInt() ? input.nextInt() : 4;
            input.nextLine(); // clear the leftover newline

            switch (choice) {
                case 1:
                    System.out.print("Enter pet ID (e.g. P001): ");
                    String id = input.nextLine().trim();
                    Pet found = null;
                    for (Pet p : pets) {                  // search through the array
                        if (p.getPetId().equalsIgnoreCase(id)) {
                            found = p;
                            break;
                        }
                    }
                    if (found == null) {
                        System.out.println("No pet with that ID.");
                    } else if (!found.canBeSold()) {
                        System.out.println(found.getName() + " cannot be sold right now.");
                    } else {
                        double discount = customer.getDiscountRate();
                        double finalPrice = found.calculateDiscountedPrice(discount);
                        System.out.printf("%s costs RM%.2f after %.0f%% discount.%n",
                                found.getName(), finalPrice, discount);
                        found.setAvailable(false);        // mark as sold via setter
                        customer.addPurchase(finalPrice); // update customer record
                        System.out.println("Purchase done. Membership now: "
                                + customer.getMembership());
                    }
                    break;

                case 2:
                    customer.displayInfo();
                    break;

                case 3:
                    System.out.print("New phone number: ");
                    customer.setPhone(input.nextLine());
                    System.out.println("Phone updated to " + customer.getPhone());
                    break;

                case 4:
                    running = false;
                    System.out.println("Thanks for visiting!");
                    break;

                default:
                    System.out.println("Please pick 1 to 4.");
            }
        }

        // ----- 4. Show the Gold member's discount for comparison -----
        System.out.printf("%n%s (%s) enjoys a %.0f%% discount.%n",
                guest.getName(), guest.getMembership(), guest.getDiscountRate());
    }
}
