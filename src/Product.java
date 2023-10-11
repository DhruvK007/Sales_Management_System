public class Product {
    private String code;
    private String name;
    private double price;
    private long quantity;
    // Constructor
    public Product(String code, String name, double price, long quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity=quantity;
    }

    //Empty default constructor to solve problem in inheritance
    public Product(){}

    // Getters and setters
    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
    public void updateQuantity(long quantity){
        this.quantity-=quantity;
    }
    public void addQuantity(long quantity){
        this.quantity+=quantity;
    }
    //To check whether stock>=quantity i.e. Required Stock available or Not
    public boolean checkQuantity(long quantity){
        return !(this.quantity-quantity<0);
    }
}





