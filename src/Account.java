import java.util.ArrayList;

//For making bills
public class Account {

    public static void getBill(ArrayList<Product> bill) {
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
