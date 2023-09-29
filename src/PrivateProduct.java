import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class PrivateProduct extends Product {
    // Define private product methods here
    public PrivateProduct(String code, String name, double price,long quantity) {
        super(code, name, price,quantity);
    }

    public String getLastRecordCode(LinkedHashSet<Product> productLinkedHashSet) {
        // Implement logic to return the code of the last record in the product file
        Product temp= productLinkedHashSet.stream().skip(listOfProducts.size()-1).findFirst().get();
        if(temp==null){
            return "No Record Found!!!";
        }
        return ("Code: "+temp.getCode()+"\nName: "+temp.getName()+"\nPrice: "+temp.getPrice()+"\nQuantity: "+temp.getQuantity());
    }

    public int getRecordNumberByCode(String checkCode,LinkedHashSet<Product> productLinkedHashSet){

        if(productLinkedHashSet.stream().anyMatch(obj->obj.getCode().equals(checkCode))) {
            return productLinkedHashSet.stream().map(obj -> obj.getCode()).collect(Collectors.toList()).indexOf(checkCode);
        }
        return -1;
    }
}
