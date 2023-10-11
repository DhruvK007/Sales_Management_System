import java.util.LinkedHashSet;
import java.util.Scanner;

public class PublicProduct extends Product {

    //Stores the list of Products
    private LinkedHashSet<Product> listOfProducts;
    public PublicProduct() {
        listOfProducts=new LinkedHashSet<Product>();
        listOfProducts.add(new Product("A","Chocolate",100,500));
    }
    public LinkedHashSet<Product> getListOfProducts() {
        return listOfProducts;
    }
    public void addProduct() {

        //Take input from the user for the product to be inputted
        System.out.println("\nEnter the details for the Product to be added\n");
        System.out.print("Enter the Product Code: ");
        Scanner sc = new Scanner(System.in);
        String addCode = sc.next();

        //To prevent Product Code Duplication
        if(listOfProducts.stream().anyMatch(obj->obj.getCode().equals(addCode))){
            System.out.println("\u001B[31m"+"Please Enter a Unique code for the product!!!"+"\u001B[0m");
        }

        else {
            System.out.print("Enter the Product Name: ");
            String addName = sc.next();

            System.out.print("Enter the Product Price: ");
            double addPrice = sc.nextInt();

            System.out.print("Enter the Product Quantity: ");
            long addQuantity = sc.nextLong();

            //Add to the list
            listOfProducts.add(new Product(addCode, addName, addPrice, addQuantity));
            System.out.println("\u001B[32m"+"Product Added Successfully..."+"\u001B[0m");
        }
    }

    public void modifyProduct() {

        //To check whether any product exists or not
        if(listOfProducts.isEmpty()){
            System.out.println("\u001B[31m"+"No Product Records Found!!!"+"\u001B[0m");
            return;
        }

        System.out.print("Enter the Code of the Product you want to modify: ");
        Scanner sc = new Scanner(System.in);
        String modifyCode=sc.next();

        //To check whether the product of the entered code exists
        if(listOfProducts.stream().noneMatch(obj->obj.getCode().equals(modifyCode))){
            System.out.println("\u001B[31m"+"Please Enter a Code of the Product that is Available!!!"+"\u001B[0m");
        }

        else{
            //To display before Modification
            Product display= listOfProducts.stream().filter(obj -> obj.getCode().equals(modifyCode)).findFirst().get();

            System.out.println("\nProduct Code   Product Name     Product Price    Product Quantity");

            //Used String.format to Left align the Values, it is just like printf in C

            System.out.print(String.format("%" + -12 + "s", display.getCode()));
            System.out.print("   ");
            System.out.print(String.format("%" + -14 + "s", display.getName()));
            System.out.print("   ");
            System.out.print(String.format("%" + -13 + "s", display.getPrice()));
            System.out.print("   ");
            System.out.print(String.format("%" + -16 + "s", display.getQuantity()));
            System.out.println("\n\nWhich Field of the Product do you want to Modify?");

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

                System.out.println("\u001B[32m"+"Name changed successfully to "+updatedName+"\u001B[0m");
            }
            else if(choice==2){
                System.out.print("Enter the Price you want to update: ");
                double updatedPrice = sc.nextDouble();

                if(updatedPrice<0){
                    System.out.println("\u001B[31m"+"Price Cannot be Negative!!!"+"\u001B[0m");
                    return;
                }

                //Updating Price using setPrice setter Method
                listOfProducts.stream().filter(obj -> obj.getCode().equals(modifyCode)).findFirst().get().setPrice(updatedPrice);
                System.out.println("\u001B[32m"+"Price changed successfully to "+updatedPrice+"\u001B[0m");
            }
            else if(choice==3){
                System.out.print("Enter the Quantity you want to update: ");
                long updatedQuantity = sc.nextLong();

                //To handle user entering Negative Quantities
                if(updatedQuantity<0){
                    System.out.println("\u001B[31m"+"Quantity Cannot be Negative!!!"+"\u001B[0m");
                    return;
                }
                //Updating Quantity using setQuantity setter Method
                listOfProducts.stream().filter(obj -> obj.getCode().equals(modifyCode)).findFirst().get().setQuantity(updatedQuantity);
                System.out.println("\u001B[32m"+"Quantity changed successfully to "+updatedQuantity+"\u001B[0m");
            }

            else{
                System.out.println("\u001B[31m"+"Please Enter a Valid Input!!!"+"\u001B[0m");
            }
        }
    }

    public void listProducts() {

        //To check whether any product exist or not
        if(listOfProducts.isEmpty()){
            System.out.println("\u001B[31m"+"No Product Records Found!!!"+"\u001B[0m");
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
            System.out.println();
        }
    }

    public void purchaseProduct() {

        //To check whether any product exist or not
        if(listOfProducts.isEmpty()){
            System.out.println("\u001B[31m"+"No Product Records Found!!!"+"\u001B[0m");
            return;
        }

        listProducts();

        LinkedHashSet<Product> bill= new LinkedHashSet<Product>();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("\nEnter the Product Code of the Product you want to Purchase");
            System.out.println("Enter Checkout in code When you are Finished Buying\n");
            System.out.print("Enter the Product code: ");
            String purchaseCode=sc.next();

            //To check out after Adding items to Cart
            if(purchaseCode.equals("Checkout")){
                //Function call by static getBill method in account
                //We pass the listOfProducts and items purchased i.e. bill
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
                        if(bill.stream().anyMatch(obj->obj.getCode().equals(purchaseCode))){
                            if(!temp.checkQuantity((bill.stream().filter(obj->obj.getCode().equals(purchaseCode)).findFirst().get().getQuantity())+quantity)){
                                System.out.println("\u001B[31m"+"\nSorry for your Inconvenience but that much stock is not available for the required item."+"\u001B[0m");
                                System.out.println("Stock Available: "+temp.getQuantity());
                            }
                            else {
                                bill.stream().filter(obj -> obj.getCode().equals(purchaseCode)).findFirst().get().addQuantity(quantity);
                            }
                        }
                        else {
                            bill.add(new Product(purchaseCode, temp.getName(), temp.getPrice(), quantity));
                        }
                    }
                    else{
                        System.out.println("\u001B[31m"+"\nSorry for your Inconvenience but that much stock is not available for the required item."+"\u001B[0m");
                        System.out.println("Stock Available: "+temp.getQuantity());
                    }
                }
                else{
                    System.out.println("\u001B[31m"+"\nPlease Enter a valid Product Code!!"+"\u001B[0m");
                }

            }
        }
    }

    public void deleteProduct() {

        //To check if any products exist
        if(listOfProducts.isEmpty()){
            System.out.println("\u001B[31m"+"No Product Records Found!!"+"\u001B[0m");
            return;
        }

        System.out.print("Enter the code of the Product you want to Delete: ");
        Scanner sc = new Scanner(System.in);
        String deleteCode = sc.next();

        //To check whether the entered code exists in product list or not
        if (listOfProducts.stream().anyMatch(obj->obj.getCode().equals(deleteCode))){
            //Removes Object with the Entered Code
            listOfProducts.removeIf(obj -> obj.getCode().equals(deleteCode));
            System.out.println("\u001B[32m"+"The product with code:"+deleteCode+" was deleted Successfully."+"\u001B[0m");
        }
        else{
            System.out.println("\u001B[31m"+"\nPlease Enter a valid Product Key!!"+"\u001B[0m");
        }

    }
}