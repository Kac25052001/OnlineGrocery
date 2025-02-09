import java.util.Random;

public class Product {

    private String productName;
    private String productID;
    private String productDescription;
    private double productPrice;
    private int productQuantity;


    public String generateRandomNumber() {
        Random random = new Random();
        long min = 1000000000L; // Minimum 10-digit number
        long max = 9999999999L; // Maximum 10-digit number
        long randomNumber = min + (long) (random.nextDouble() * (max - min));
        return String.valueOf(randomNumber);
    }

    public Product(String productName, String productDescription, double productPrice, int productQuantity) {
        this.productName = productName;
        
        this.productID = generateRandomNumber();
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

}
