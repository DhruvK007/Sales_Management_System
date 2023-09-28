import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PublicProduct extends Product {

    // Define public product methods here
    public PublicProduct(String code, String name, double price,long quantity) {
        super(code, name, price,quantity);
        listOfProducts=new LinkedHashSet<Product>();
    }

    public PublicProduct() {
        super();
    }
    public void addProduct() {

        //Take input from the user for the product to be inputted
        System.out.println("\nEnter the details for the Product to be added\n");
        System.out.print("Enter the Product Code:");
        Scanner sc = new Scanner(System.in);
        String code = sc.next();

        System.out.print("Enter the Product Name:");
        String name = sc.next();

        System.out.print("Enter the Product Price:");
        double price = sc.nextInt();

        System.out.print("Enter the Product Quantity:");
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
        // Implement logic to purchase a public product
        System.out.println("\nEnter the Product Code of the Product you want to Purchase");
        System.out.println("Enter Checkout in code When you are Finished Buying");
        ArrayList<Product> bill= new ArrayList<Product>();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Enter the Product code: ");
            String code=sc.next();
            if(code.equals("Checkout")){
                PublicAccount.getBill(bill);
                break;
            }
            else{
                if(listOfProducts.stream().filter(obj -> obj.getCode().equals(code)).findFirst().isPresent()){
                    System.out.print("Enter the Quantity: ");
                    long quantity=sc.nextLong();
                    Product temp =  listOfProducts.stream().filter(obj->obj.getCode().equals(code)).findFirst().get();
                    bill.add(new Product(code,temp.getName(),temp.getPrice(),quantity));
                }
                else{
                    System.out.println("\nPlease Enter a valid Product Key!!");
                }

            }
        }
    }

    public void deleteProduct() {
        // Implement logic to delete a public product
    }
}