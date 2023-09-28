import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

//For making bills
public class Account {

    public static void getBill(LinkedHashSet<Product>listOfProducts,LinkedHashSet<Product> bill) {
        displayBill(bill);
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Do you want to edit the Bill?");
            System.out.println("Enter 1 for Editing");
            System.out.println("Enter 2 to Pay the Bill");
            int choice = sc.nextInt();
            if (choice == 1) {
                editBill(bill);
            } else if (choice == 2) {
                System.out.println("Updated Bill");
                displayBill(bill);
                for (Product obj:bill){
                    listOfProducts.stream().filter(p->p.getCode().equals(obj.getCode())).findFirst().get().updateQuantity(obj.getQuantity());
                }
                System.out.println("\n\nThank You for Purchasing.");
                break;
            }
        }
    }
    public static void editBill(LinkedHashSet<Product> bill){

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the Product Code in the bill where you want to make changes?");
        String code=sc.next();
        if(bill.stream().filter(obj->obj.getCode().equals(code)).findFirst().isPresent()){
            System.out.println("Enter 1 for Editing the Quantity");
            System.out.println("Enter 2 to Deleting the product from the bill");
            int choice=sc.nextInt();
            if(choice==1){
                System.out.print("Enter the new Quantity: ");
                long quantity = sc.nextLong();
                bill.stream().filter(obj->obj.getCode().equals(code)).findFirst().get().setQuantity(quantity);
                System.out.println("Quantity changed Successfully!!!");
            }else if(choice==2) {
                bill.removeIf(obj->obj.getCode().equals(code));
                System.out.println("Product Removed from bill Successfully");
            }else {
                System.out.println("Please Enter a valid Choice!!!");
            }
        }
        else{
            System.out.println("Please Enter a Valid Product Code from the Bill!!!");
        }
    }

    public static void displayBill(LinkedHashSet<Product> bill){
        double Total = 0;
        for (Product a : bill) {
            Total = Total +(a.getQuantity() *a.getPrice());
        }
        System.out.println("Code_of_product   Name_of_product   Qnt.   Price ");
        for (Product a : bill) {


            System.out.print(String.format("%" + -15 + "s", a.getCode()));
            System.out.print("   ");
            System.out.print(String.format("%" + -15 + "s", a.getName()));
            System.out.print("   ");
            System.out.print(String.format("%" + -4 + "s", a.getQuantity()));
            System.out.print("   ");
            System.out.print(String.format("%" + -5 + "s", a.getPrice()));
            System.out.println("");

        }
        System.out.println(String.format("%" + 49 + "s", "_______"));
        System.out.print(String.format("%"+41+"s","TOTAl:-"));
        System.out.print("  ");
        System.out.println(String.format("%" + 3 + "s", Total));
    }
}
