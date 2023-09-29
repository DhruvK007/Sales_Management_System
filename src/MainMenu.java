import java.util.Scanner;
public class MainMenu {
    public static void displayMainMenu() {

        PublicProduct obj = new PublicProduct();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nMain Menu:");
            System.out.println("1. Purchase Products");
            System.out.println("2. List Products");
            System.out.println("3. Edit Products");
            System.out.println("4. Lifetime Bills/Product Report");
            System.out.println("5. Add Products");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    obj.purchaseProduct();
                    break;
                case 2:
                    obj.listProducts();
                    break;
                case 3:
                    obj.modifyProduct();
                    break;
                case 4:
                    Account.getReport(obj.listOfProducts);
                    break;
                case 5:
                    obj.addProduct();
                    break;
                case 0:
                    System.out.println("Thank You for using our Sales Management System.");
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("\u001B[31m"+"Invalid choice. Try again."+"\u001B[0m"+"\n");
            }
        } while (choice != 0);
    }
}


