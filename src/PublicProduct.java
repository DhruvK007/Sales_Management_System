import java.util.LinkedHashSet;
import java.util.Scanner;

public class PublicProduct extends Product {

    // Define public product methods here
    public PublicProduct(String code, String name, double price,long quantity) {
        super(code, name, price,quantity);
        listOfProducts=new LinkedHashSet<Product>();
    }

    public void addProduct() {

        //Take input from the user for the product to be inputted
        System.out.println("Enter the details for the Product to be added\n");
        System.out.println("Enter the Product Code:");
        Scanner sc = new Scanner(System.in);
        String code = sc.next();

        System.out.println("Enter the Product Name:");
        String name = sc.next();

        System.out.println("Enter the Product Price:");
        double price = sc.nextInt();

        System.out.println("Enter the Product Quantity:");
        long quantity = sc.nextLong();

        //Add to the list
        listOfProducts.add(new PublicProduct(code,name,price,quantity));
        System.out.println("Product Added Successfully...");
    }

    public void modifyProduct() {
        // Implement logic to modify a public product
    }

    public void listProducts() {
        // To Display the list of products
        System.out.println("Product Code   Product Name     Product Price    Product Quantity");
        for(Product p: listOfProducts){
            System.out.print(String.format("%" + -12 + "s", getCode()));
            System.out.print("   ");
            System.out.print(String.format("%" + -14 + "s", getName()));
            System.out.print("   ");
            System.out.print(String.format("%" + -13 + "s", getPrice()));
            System.out.print("   ");
            System.out.print(String.format("%" + -16 + "s", getQuantity()));
            System.out.println("");
        }
    }

    public void purchaseProduct() {
        // Implement logic to purchase a public product\
        System.out.println("Enter the Product Code of the Product you want to Purchase");
        System.out.println("Enter -1 When you are Finished Buying");
        while(true){

        }
    }

    public void deleteProduct() {
        // Implement logic to delete a public product
    }
}