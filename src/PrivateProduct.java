public class PrivateProduct extends Product {
    // Define private product methods here
    public PrivateProduct(String code, String name, double price,long quantity) {
        super(code, name, price,quantity);
    }

    public String getLastRecordCode() {
        // Implement logic to return the code of the last record in the product file
        return null;
    }

    public int getRecordNumberByCode(String code) {
        // Implement logic to return the record number of the given code in the product file
        return -1;
    }

    public void displayRecord() {
        // Implement logic to display a private product record
    }

    public void modifyRecord() {
        // Implement logic to modify a private product record
    }

    public void deleteRecord() {
        // Implement logic to delete a private product record
    }
}
