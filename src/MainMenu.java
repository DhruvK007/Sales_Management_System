import java.util.Scanner;
public class MainMenu {
    public static void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Main Menu:");
            System.out.println("1. Purchase Products");
            System.out.println("2. List Products");
            System.out.println("3. Edit Products");
            System.out.println("4. Bills Report");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Handle Purchase Products
                    break;
                case 2:
                    // Handle List Products
                    break;
                case 3:
                    // Handle Edit Products
                    break;
                case 4:
                    // Handle Bills Report
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }
}


