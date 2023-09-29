import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

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
        System.out.print("Enter the Product Code: ");
        Scanner sc = new Scanner(System.in);
        String addCode = sc.next();

        //To prevent Product Code Duplication
        if(listOfProducts.stream().anyMatch(obj->obj.getCode().equals(addCode))){
            System.out.println("Please Enter a Unique code for the product!!!");
        }

        else {
            System.out.print("Enter the Product Name: ");
            String addName = sc.next();

            System.out.print("Enter the Product Price: ");
            double addPrice = sc.nextInt();

            System.out.print("Enter the Product Quantity: ");
            long addQuantity = sc.nextLong();

            //Add to the list
            listOfProducts.add(new PublicProduct(addCode, addName, addPrice, addQuantity));
            System.out.println("Product Added Successfully...");
        }
    }

    public void modifyProduct() {

        if(listOfProducts.isEmpty()){
            System.out.println("No Product Records Found!!!");
            return;
        }

        System.out.print("Enter the Code of the Product you want to modify: ");
        Scanner sc = new Scanner(System.in);
        String modifyCode=sc.next();

        //To check whether the product of the entered code exists
        if(!listOfProducts.stream().anyMatch(obj->obj.getCode().equals(modifyCode))){
            System.out.println("Please Enter a Code of the Product that is Available!!!");
        }

        else{
            //To display before Modification
            Product display= listOfProducts.stream().filter(obj -> obj.getCode().equals(modifyCode)).findFirst().get();

            System.out.println("\nProduct Code   Product Name     Product Price    Product Quantity");
            System.out.print(String.format("%" + -12 + "s", display.getCode()));
            System.out.print("   ");
            System.out.print(String.format("%" + -14 + "s", display.getName()));
            System.out.print("   ");
            System.out.print(String.format("%" + -13 + "s", display.getPrice()));
            System.out.print("   ");
            System.out.print(String.format("%" + -16 + "s", display.getQuantity()));
            System.out.println("");
            System.out.println("\nWhich Field of the Product do you want to Modify?");

            System.out.println("Enter 1 for Changing Name");
            System.out.println("Enter 2 for Changing Price");
            System.out.println("Enter 3 for Changing Quantity\n");

            System.out.print("Enter your choice: ");
            int choice= sc.nextInt();

            if(choice==1){
                System.out.print("Enter the Name you want to update: ");
                String updatedName = sc.next();

                //Updating name using setName setter Method
                listOfProducts.stream().filter(obj -> obj.getCode().equals(modifyCode)).findFirst().get().setName(updatedName);

                System.out.println("Name changed successfully to "+updatedName);
            }
            else if(choice==2){
                System.out.print("Enter the Price you want to update: ");
                double updatedPrice = sc.nextDouble();

                //Updating Price using setPrice setter Method
                listOfProducts.stream().filter(obj -> obj.getCode().equals(modifyCode)).findFirst().get().setPrice(updatedPrice);
                System.out.println("Price changed successfully to "+updatedPrice);
            }
            else if(choice==3){
                System.out.print("Enter the Quantity you want to update: ");
                long updatedQuantity = sc.nextLong();
                if(updatedQuantity<0){
                    System.out.println("Quantity Cannot be Negative!!!");
                    return;
                }
                //Updating Quantity using setQuantity setter Method
                listOfProducts.stream().filter(obj -> obj.getCode().equals(modifyCode)).findFirst().get().setQuantity(updatedQuantity);
                System.out.println("Quantity changed successfully to "+updatedQuantity);
            }

            else{
                System.out.println("Please Enter a Valid Input!!!");
            }
        }
    }

    public void listProducts() {
        if(listOfProducts.isEmpty()){
            System.out.println("\nNo Product Records Found!!!");
            return;
        }

        // To Display the list of products
        System.out.println("\nProduct Code   Product Name     Product Price    Product Quantity");
        for(Product p: listOfProducts){
            System.out.print(String.format("%" + -12 + "s", p.getCode()));
            System.out.print("   ");
            System.out.print(String.format("%" + -14 + "s", p.getName()));
            System.out.print("   ");
            System.out.print(String.format("%" + -13 + "s", p.getPrice()));
            System.out.print("    ");
            System.out.print(String.format("%" + -16 + "s", p.getQuantity()));
            System.out.println("");
        }
    }

    public void purchaseProduct() {
        if(listOfProducts.isEmpty()){
            System.out.println("No Product Records Found!!!");
            return;
        }

        LinkedHashSet<Product> bill= new LinkedHashSet<Product>();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("\nEnter the Product Code of the Product you want to Purchase");
            System.out.println("Enter Checkout in code When you are Finished Buying\n");
            System.out.print("Enter the Product code: ");
            String purchaseCode=sc.next();

            //To Checkout after Adding items to Cart
            if(purchaseCode.equals("Checkout")){
                Account.getBill(listOfProducts,bill);
                break;
            }
            else{

                //To check whether the entered code exists in product list or not
                if(listOfProducts.stream().anyMatch(obj->obj.getCode().equals(purchaseCode))){
                    System.out.print("Enter the Quantity: ");
                    long quantity=sc.nextLong();
                    Product temp =  listOfProducts.stream().filter(obj->obj.getCode().equals(purchaseCode)).findFirst().get();
                    if(temp.checkQuantity(quantity)){
                        bill.add(new Product(purchaseCode,temp.getName(),temp.getPrice(),quantity));
                    }
                    else{
                        System.out.println("\nSorry for your Inconvenience but that much stock is not available for the required item.");
                        System.out.println("Stock Available: "+temp.getQuantity());
                    }
                }
                else{
                    System.out.println("\nPlease Enter a valid Product Code!!");
                }

            }
        }
    }

    public void deleteProduct() {
        System.out.print("Enter the code of the Product you want to Delete: ");
        Scanner sc = new Scanner(System.in);
        String deleteCode = sc.next();

        //To check whether the entered code exists in product list or not
        if (listOfProducts.stream().anyMatch(obj->obj.getCode().equals(deleteCode))){
            //Removes Object with the Entered Code
            listOfProducts.removeIf(obj -> obj.getCode().equals(deleteCode));
            System.out.println("The product with code:"+deleteCode+"was deleted Successfully.");
        }
        else{
            System.out.println("\nPlease Enter a valid Product Key!!");
        }

    }
}