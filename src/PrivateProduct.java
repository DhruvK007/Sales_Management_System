import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PrivateProduct extends PublicProduct {
    public PrivateProduct(){}
    public String getLastRecord(LinkedHashSet<Product> productLinkedHashSet) {

        if(productLinkedHashSet.isEmpty()){
            return "\u001B[31m"+"No Record Found!!!"+ "\u001B[0m";
        }

        //Returns the last record in temp
        Product temp = productLinkedHashSet.stream().skip(productLinkedHashSet.size()-1).findFirst().get() ;
        return ("\nCode: "+temp.getCode()+"\nName: "+temp.getName()+"\nPrice: "+temp.getPrice()+"\nQuantity: "+temp.getQuantity());
    }

    public int getRecordNumberByCode(LinkedHashSet<Product> productLinkedHashSet){
        System.out.print("\nEnter the Product Code: ");
        Scanner sc = new Scanner(System.in);
        String checkCode = sc.next();

        if(productLinkedHashSet.stream().anyMatch(obj->obj.getCode().equals(checkCode))) {
            //It creates list of All Codes and then returns the index+1 of the Entered Code
            return productLinkedHashSet.stream().map(obj -> obj.getCode()).collect(Collectors.toList()).indexOf(checkCode)+1;
        }
        return -1;
    }
}
