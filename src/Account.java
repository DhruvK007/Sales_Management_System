import java.util.LinkedHashSet;
import java.util.Scanner;

//For making bills
public class Account {
        public static LinkedHashSet<LinkedHashSet<Product>> billList= new LinkedHashSet<LinkedHashSet<Product>>();

        public static void getBill(LinkedHashSet<Product>listOfProducts,LinkedHashSet<Product> bill) {
            if(bill.isEmpty()){
                System.out.println("No Items found in the Cart. Please Try Again!!!");
                return;
            }
        displayBill(bill);
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("Do you want to edit the Bill?");
            System.out.println("\nEnter 1 for Editing the Bill");
            System.out.println("Enter 2 to Pay the Bill");
            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();
            if (choice == 1) {
                editBill(listOfProducts,bill);
            } else if (choice == 2) {
                System.out.println("Final Bill");
                displayBill(bill);
                billList.add(bill);
                for (Product obj:bill){
                    listOfProducts.stream().filter(p->p.getCode().equals(obj.getCode())).findFirst().get().updateQuantity(obj.getQuantity());
                }
                System.out.println("\n\nThank You for Purchasing.");
                break;
            }
        }
    }
    public static void editBill(LinkedHashSet<Product>listOfProducts,LinkedHashSet<Product> bill){

        Scanner sc = new Scanner(System.in);
        System.out.println("\nPlease enter the Product Code in the bill where you want to make changes?");
        System.out.print("Code: ");
        String code=sc.next();
        if(bill.stream().anyMatch(obj->obj.getCode().equals(code))){
            System.out.println("\nEnter 1 for Editing the Quantity");
            System.out.println("Enter 2 to Deleting the product from the bill");
            System.out.print("Enter Your Choice: ");
            int choice=sc.nextInt();
            if(choice==1){
                System.out.print("Enter the new Quantity: ");
                long quantity = sc.nextLong();
                if(!listOfProducts.stream().filter(obj->obj.getCode().equals(code)).findFirst().get().checkQuantity(quantity)){
                    System.out.println("\nSorry for your Inconvenience but that much stock is not available for the required item.");
                    System.out.println("Stock Available: "+listOfProducts.stream().filter(obj->obj.getCode().equals(code)).findFirst().get().getQuantity()+"\n");
                }
                else {
                    bill.stream().filter(obj -> obj.getCode().equals(code)).findFirst().get().setQuantity(quantity);
                    System.out.println("Quantity changed Successfully!!!");
                }
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
        if(bill.isEmpty()){
            System.out.println("No Items found in the Cart. Please Try Again!!!");
            return;
        }
        System.out.println("\nCode_of_product   Name_of_product   Qnt.   Price ");
        for (Product a : bill) {


            System.out.print(String.format("%" + -15 + "s", a.getCode()));
            System.out.print("   ");
            System.out.print(String.format("%" + -15 + "s", a.getName()));
            System.out.print("   ");
            System.out.print(String.format("%" + -4 + "s", a.getQuantity()));
            System.out.print("   ");
            System.out.print(String.format("%" + -5 + "s", a.getPrice()+"\n"));

        }
        System.out.println(String.format("%" + 49 + "s", "_______"));
        System.out.print(String.format("%"+41+"s","TOTAl:-"));
        System.out.print("  ");
        System.out.println(String.format("%" + 3 + "s", totalAmountPaid(bill)));
    }

    public static void getReport(LinkedHashSet<Product>listOfProducts){
        if(billList.isEmpty()){
            System.out.println("No Bill Records Found!!!");
            return;
        }

        System.out.println("\nEnter 1 for Bill Report");
        System.out.println("Enter 2 for Product Report");
        System.out.print("Enter Your Choice: ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        if(choice==1){
            getBillReport();
        } else if (choice==2) {
            getProductReport(listOfProducts);
        }
        else {
            System.out.println("Enter a Valid Choice!!!");
        }
    }
    public static void getProductReport(LinkedHashSet<Product>listOfProducts){
        System.out.println("Enter the Code of the Product");
        Scanner sc = new Scanner(System.in);
        String code = sc.next();
        if(listOfProducts.stream().anyMatch(obj->obj.getCode().equals(code))) {
            double totalSales = listOfProducts.stream().filter(obj->obj.getCode().equals(code)).findFirst().get().getPrice();
            long totalProduct=0;
            for(LinkedHashSet<Product> p: billList){
                for(Product o:p){
                    if(o.getCode().equals(code)){
                        totalProduct+=o.getQuantity();
                    }
                }
            }
            totalSales*=totalProduct;
            System.out.println("Product_Code   Product_Name   Total_Product_Sold   Total_Sale_Value");
            System.out.print(String.format("%" + -15 + "s", code));
            System.out.print(String.format("%" + -15 + "s", listOfProducts.stream().filter(obj->obj.getCode().equals(code)).findFirst().get().getName()));
            System.out.print(String.format("%" + -21 + "s", totalProduct));
            System.out.print(totalSales+"\n");
        }else{
            System.out.println("Please Enter Valid Product  Code!!!");
        }
    }
    public static void getBillReport(){
        int id=1;
        System.out.println("\nBill_ID  Total_Items_Purchased   Amount_Paid");
        for(LinkedHashSet<Product> obj: billList){
            System.out.print(String.format("%" + -10 + "s", id++));
            System.out.print(String.format("%" + -24 + "s",totalItems(obj)));
            System.out.println(totalAmountPaid(obj));
        }

        System.out.println("\nDo you want to View any bill or Delete any bill?");
        System.out.print("Enter Y for Yes / N for No: ");
        Scanner sc =new Scanner(System.in);
        String choice=sc.next();
        if(choice.equals("Y")){
            System.out.println("\nEnter 1 to view any Full Bill");
            System.out.println("Enter 2 to delete any Bill");
            System.out.print("\nEnter your Choice: ");
            int select=sc.nextInt();
            if(select==1){
                System.out.print("Enter the ID of the Bill: ");
                long bid= sc.nextInt();
                displayBill(billList.stream().skip(bid-1).findFirst().get());
            } else if (select==2) {
                System.out.print("Enter the ID of the Bill: ");
                long bid= sc.nextInt();
                billList.remove(billList.stream().skip(bid-1).findFirst().get());
                System.out.println("Bill with id "+bid+"was deleted successfully.");
            }else{
                System.out.println("Please Enter a Valid Choice!!!");
            }
        }
        else if(choice.equals("N")){
            System.out.println("No Problem.");
            return;
        }
        else{
            System.out.println("Please Enter a Valid Choice!!!");
        }
    }

    public static double totalAmountPaid(LinkedHashSet<Product> obj){
        double Total = 0;
        for (Product a : obj) {
            Total = Total +(a.getQuantity() *a.getPrice());
        }
        return Total;
    }

    public static long totalItems(LinkedHashSet<Product> obj){
            long total=0;
            for(Product p: obj) {
                total+=p.getQuantity();
            }
            return total;
    }
}
