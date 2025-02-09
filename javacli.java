
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class FoodMart {

    public ArrayList<Customers> customers;
    public ArrayList<Product> products;

    public FoodMart() {
        customers = new ArrayList<Customers>();
        products = new ArrayList<Product>();
    }
}

enum Changes {
    NAME, ADDRESS, PHONE, EMAIL, PASSWORD
}

public class javacli {

    public static void printMainMenu() {
        System.out.println("Enter Your Choice");
        System.out.println("1. Customer");
        System.out.println("2. Administrator");
        System.out.println("3. Exit");
    }

    public static void printCustomerSubMenu(){
        System.out.println("Enter Your Choice");
        System.out.println("1. Add Customer Details");
		System.out.println("2. Update Customer Details");
        System.out.println("3. Unregister Customer");
        
        System.out.println("4. Back");
        System.out.println("5. Exit");
    }

    public static void printAdminSubMenu(){
        System.out.println("Enter Your Choice");
        System.out.println("1. Add Product Details");
        System.out.println("2. Update Product Details");
        System.out.println("3. Delete Product Details");
        System.out.println("4. Search Customer Details");
        System.out.println("5. View Product Details");
        System.out.println("6. View Highest Price Product");
        System.out.println("7. Back");
        System.out.println("8. Exit");
    }

    

    public static void main(String[] args) {
        System.out.println("Food Mart CLI");

        FoodMart foodMart = new FoodMart();
        Scanner scanner = new Scanner(System.in);

        // Main Menu

        while(true){
            printMainMenu();
            int choice;
            try{
                choice = Integer.parseInt(scanner.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Invalid Choice Please enter a INTEGER only");
                continue;
            }
            switch(choice){

                case 1:

                    // Customer Menu

                    while(true){
                        printCustomerSubMenu();
                        boolean flag = false;
                        int custChoice;
                        try{
                            custChoice = Integer.parseInt(scanner.nextLine());
                        }
                        catch(NumberFormatException e){
                            System.out.println("Invalid Choice Please enter a INTEGER only");
                            continue;
                        }
                        switch(custChoice){

                            // Add Customer Details
                            case 1:
                                System.out.print("Enter Customer Name: ");
                                String customerName = scanner.nextLine();
                                if(customerName.isEmpty()){
                                    System.out.println("Name cannot be empty");
                                    break;
                                }
                                if(customerName.length() > 50){
                                    System.out.println("Name should be less than 50 characters");
                                    break;
                                }
                                System.out.print("Enter Customer Address: ");
                                String customerAddress = scanner.nextLine();
                                if(customerAddress.isEmpty()){
                                    System.out.println("Address cannot be empty");
                                    break;
                                }
                                if(customerAddress.length() > 100){
                                    System.out.println("Address should be less than 100 characters");
                                    break;
                                }
                                String customerPhone;
                                try{
                                    System.out.print("Enter Customer Phone: ");
                                    customerPhone = String.valueOf(Long.parseLong(scanner.nextLine()));
                                    if (customerPhone.length() != 10) {
                                        throw new NumberFormatException();
                                    }

                                }
                                catch(NumberFormatException e){
                                    System.out.println("Invalid Phone Number Please enter a Valid Phone Number only");
                                    break;
                                }

                                System.out.print("Enter Customer Email: ");
                                String customerEmail = scanner.nextLine();

                                if(customerEmail.isEmpty()){
                                    System.out.println("Email cannot be empty");
                                    break;
                                }
                                if(!customerEmail.contains("@")){
                                    System.out.println("Please enter a valid email");
                                    break;
                                }
                                if(customerEmail.length() > 50){
                                    System.out.println("Email should be less than 50 characters");
                                    break;
                                }

                                for(Customers c : foodMart.customers){
                                    if(c.getCustomerEmail().equals(customerEmail)){
                                        System.out.println("Email already exists");
                                        break;
                                    }
                                }

                                System.out.print("Enter Customer Password: ");
                                String customerPassword = scanner.nextLine();
                                if(customerPassword.isEmpty()){
                                    System.out.println("Password cannot be empty");
                                    break;
                                }
                                if(customerPassword.length() < 8){
                                    System.out.println("Password should be atleast 8 characters");
                                    break;
                                }
                                Customers customer = new Customers(customerName, customerAddress, customerPhone, customerEmail, customerPassword);
                                foodMart.customers.add(customer);
                                System.out.println("Customer Added Successfully");
                                System.out.println("Customer ID: " + customer.getCustomerID());
                                break;


                            // Update Customer Details
                            case 2:
                                System.out.println("Enter Customer Email");
                                String email = scanner.nextLine();
                                if(email.isEmpty()){
                                    System.out.println("Email cannot be empty");
                                    break;
                                }
                                if(email.length() > 50){
                                    System.out.println("Email should be less than 50 characters");
                                    break;
                                }
                                if(!email.contains("@")){
                                    System.out.println("Please enter a valid email");
                                    break;
                                }
                                System.out.println("Enter Customer Password");
                                String password = scanner.nextLine();

                                if(password.isEmpty()){
                                    System.out.println("Password cannot be empty");
                                    break;
                                }
                                if(password.length() < 8){
                                    System.out.println("Password should be atleast 8 characters");
                                    break;
                                }

                                try {
                                    MessageDigest md = MessageDigest.getInstance("MD5");
                                    byte[] messageDigest = md.digest(password.getBytes());
                                    BigInteger no = new BigInteger(1, messageDigest);
                                    String hashText = no.toString(16);
                                    while (hashText.length() < 32) {
                                        hashText = "0" + hashText;
                                    }
                                    password = hashText;
                                } catch (NoSuchAlgorithmException e) {
                                    throw new RuntimeException(e);
                                }

                                boolean found = false;
                                for(Customers c : foodMart.customers){
                                    if(c.getCustomerEmail().equals(email)){
                                        found = true;
                                        if(c.getCustomerPassword().equals(password)){
                                            System.out.print("Enter new name or press enter to skip: ");
                                            String name = scanner.nextLine();

                                            if(!name.isEmpty()){
                                                if(name.length() > 50){
                                                    System.out.println("Name should be less than 50 characters");
                                                    break;
                                                }
                                                c.setCustomerName(name);
                                            }
                                            System.out.print("Enter new address or press enter to skip: ");
                                            String address = scanner.nextLine();
                                            if(!address.isEmpty()){
                                                if(address.length() > 100){
                                                    System.out.println("Address should be less than 100 characters");
                                                    break;
                                                }
                                                c.setCustomerAddress(address);
                                            }
                                            System.out.print("Enter new phone or press enter to skip: ");
                                            String phone = scanner.nextLine();
                                            if(!phone.isEmpty()){
                                                try{
                                                    phone = String.valueOf(Long.parseLong(phone));
                                                    if (phone.length() != 10) {
                                                        throw new NumberFormatException();
                                                    }
                                                    c.setCustomerPhone(phone);
                                                }
                                                catch(NumberFormatException e){
                                                    System.out.println("Invalid Phone Number Please enter a Valid Phone Number only");
                                                    break;
                                                }
                                            }
                                            System.out.print("Enter new email or press enter to skip: ");
                                            String newEmail = scanner.nextLine();
                                            if(!newEmail.isEmpty()){
                                                if(!newEmail.contains("@")){
                                                    System.out.println("Please enter a valid email");
                                                    break;
                                                }
                                                if(newEmail.length() > 50){
                                                    System.out.println("Email should be less than 50 characters");
                                                    break;
                                                }
                                                for(Customers cust : foodMart.customers){
                                                    if(cust.getCustomerEmail().equals(newEmail)){
                                                        System.out.println("Email already exists");
                                                        break;
                                                    }
                                                }
                                                c.setCustomerEmail(newEmail);
                                            }
                                            System.out.print("Enter new password or press enter to skip: ");
                                            String newPassword = scanner.nextLine();
                                            if(!newPassword.isEmpty()){
                                                if(newPassword.length() < 8){
                                                    System.out.println("Password should be atleast 8 characters");
                                                    break;
                                                }
                                                try {
                                                    MessageDigest md = MessageDigest.getInstance("MD5");
                                                    byte[] messageDigest
                                                    = md.digest(newPassword.getBytes());
                                                    BigInteger no = new BigInteger(1, messageDigest);
                                                    String hashText = no.toString(16);
                                                    while (hashText.length() < 32) {
                                                        hashText = "0" + hashText;
                                                    }
                                                    newPassword = hashText;
                                                } catch (NoSuchAlgorithmException e) {
                                                    throw new RuntimeException(e);
                                                }
                                                c.setCustomerPassword(newPassword);
                                            }
                                            System.out.println("Customer Details Updated Successfully");
                                        }
                                        else{
                                            System.out.println("Invalid Password");
                                        }
                                        break;
                                    }        
                                }
                                if(!found){
                                    System.out.println("Customer not found");
                                }
                                break;

                            // Unregister Customer
                            case 3:

                                System.out.print("Enter Customer Email: ");
                                String email1 = scanner.nextLine();
                                if(email1.isEmpty()){
                                    System.out.println("Email cannot be empty");
                                    break;
                                }
                                if(email1.length() > 50){
                                    System.out.println("Email should be less than 50 characters");
                                    break;
                                }
                                if(!email1.contains("@")){
                                    System.out.println("Please enter a valid email");
                                    break;
                                }

                                String password1 = scanner.nextLine();
                                if(password1.isEmpty()){
                                    System.out.println("Password cannot be empty");
                                    break;
                                }
                                if(password1.length() < 8){
                                    System.out.println("Password should be atleast 8 characters");
                                    break;
                                }

                                try {
                                    MessageDigest md = MessageDigest.getInstance("MD5");
                                    byte[] messageDigest
                                    = md.digest(password1.getBytes());

                                    BigInteger no = new BigInteger(1, messageDigest);
                                    String hashText = no.toString(16);
                                    while (hashText.length() < 32) {
                                        hashText = "0" + hashText;
                                    }
                                    password1 = hashText;
                                } catch (NoSuchAlgorithmException e) {
                                    throw new RuntimeException(e);
                                }


                                boolean found1 = false;
                                for(Customers c : foodMart.customers){
                                    if(c.getCustomerEmail().equals(email1)){
                                        found1 = true;
                                        if(c.getCustomerPassword().equals(password1)){
                                            c.setCustomerStatus("Inactive");
                                            System.out.println("Customer Unregistered Successfully");
                                            break;
                                        }
                                        else{
                                            System.out.println("Invalid Password");
                                        }
                                    }
                                }
                                if(!found1){
                                    System.out.println("Customer not found");
                                }
                                break;

                            // Back
                            case 4:
                                flag = true;
                                break;

                            // Exit
                            case 5:
                                System.exit(0);
                            default:
                                System.out.println("Invalid Choice");
                        }
                        if(flag){
                            break;
                        }
                    }
                    break;

                // Administrator Menu
                case 2:

                    while(true){
                        printAdminSubMenu();
                        boolean flag = false;
                        int prodChoice;
                        try{
                            prodChoice = Integer.parseInt(scanner.nextLine());
                        }
                        catch(NumberFormatException e){
                            System.out.println("Invalid Choice Please enter a INTEGER only");
                            continue;
                        }
                        switch(prodChoice){

                            // Add Product Details
                            case 1:

                                System.out.print("Enter Product Name: ");
                                String productName = scanner.nextLine();
                                if(productName.isEmpty()){
                                    System.out.println("Name cannot be empty");
                                    break;
                                }
                                if(productName.length() > 50){
                                    System.out.println("Name should be less than 50 characters");
                                    break;
                                }
                                System.out.print("Enter Product Description: ");
                                String productDescription = scanner.nextLine();
                                if(productDescription.isEmpty()){
                                    System.out.println("Description cannot be empty");
                                    break;
                                }
                                if(productDescription.length() > 100){
                                    System.out.println("Description should be less than 100 characters");
                                    break;
                                }
                                System.out.print("Enter Product Price: ");
                                double productPrice;
                                try{
                                    productPrice = Double.parseDouble(scanner.nextLine());
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Invalid Price Please enter a DOUBLE only");
                                    break;
                                }
                                if(productPrice <= 0){
                                    System.out.println("Price should be greater than 0");
                                    break;
                                }
                                System.out.print("Enter Product Quantity: ");
                                int productQuantity;
                                try{
                                    productQuantity = Integer.parseInt(scanner.nextLine());
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Invalid Quantity Please enter a INTEGER only");
                                    break;
                                }
                                if(productQuantity <= 0){
                                    System.out.println("Quantity should be greater than 0");
                                    break;
                                }
                                Product product = new Product(productName, productDescription, productPrice, productQuantity);
                                foodMart.products.add(product);
                                System.out.println("Product Added Successfully");
                                System.out.println("Product ID: " + product.getProductID());
                                break;

                            
                            // Update Product Details
                            case 2:

                                System.out.print("Enter Product ID: ");

                                String id;
                                try{
                                    id = String.valueOf(Long.parseLong(scanner.nextLine()));
                                    if(id.length() != 10){
                                        throw new NumberFormatException();
                                    }
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Invalid ID Please enter a 10 digit number only");
                                    break;
                                }        
                                boolean found = false;
                                for(Product p : foodMart.products){
                                    if(p.getProductID().equals(id)){
                                        found = true;
                                        System.out.print("Enter new name or press enter to skip: ");
                                        String name = scanner.nextLine();
                                        if(!name.isEmpty()){
                                            if(name.length() > 50){
                                                System.out.println("Name should be less than 50 characters");
                                                break;
                                            }
                                            p.setProductName(name);
                                        }
                                        System.out.print("Enter new description or press enter to skip: ");
                                        String description = scanner.nextLine();
                                        if(!description.isEmpty()){
                                            if(description.length() > 100){
                                                System.out.println("Description should be less than 100 characters");
                                                break;
                                            }
                                            p.setProductDescription(description);
                                        }
                                        System.out.print("Enter new price or press enter to skip: ");
                                        String priceString = scanner.nextLine();
                                        if(!priceString.isEmpty()){
                                            double price;
                                            try{
                                                price = Double.parseDouble(priceString);
                                            }
                                            catch(NumberFormatException e){
                                                System.out.println("Invalid Price Please enter a DOUBLE only");
                                                break;
                                            }
                                            if(price <= 0){
                                                System.out.println("Price should be greater than 0");
                                                break;
                                            }
                                            p.setProductPrice(price);
                                        }
                                        System.out.print("Enter new quantity or press enter to skip: ");
                                        
                                        String quantityString = scanner.nextLine();
                                        if(!quantityString.isEmpty()){
                                            int quantity;
                                            try{
                                                quantity = Integer.parseInt(quantityString);
                                            }
                                            catch(NumberFormatException e){
                                                System.out.println("Invalid Quantity Please enter a INTEGER only");
                                                break;
                                            }
                                            if(quantity <= 0){
                                                System.out.println("Quantity should be greater than 0");
                                                break;
                                            }
                                            p.setProductQuantity(quantity);
                                        }
                                        
                                        System.out.println("Product Details Updated Successfully");
                                        break;
                                    }
                                }
                                if(!found){
                                    System.out.println("Product not found");
                                }
                                break;

                            // Delete Product Details
                            case 3:

                                System.out.print("Enter Product ID: ");
                                long productID;
                                try{
                                    productID = Long.parseLong(scanner.nextLine());
                                    if(String.valueOf(productID).length() != 10){
                                        throw new NumberFormatException();
                                    }
                                }
                                catch(NumberFormatException e){
                                    System.out.println("Invalid ID Please enter a 10 digit number only");
                                    break;
                                }
                                boolean found1 = false;
                                for(Product p : foodMart.products){
                                    if(p.getProductID().equals(productID)){
                                        found1 = true;
                                        foodMart.products.remove(p);
                                        System.out.println("Product Deleted Successfully");
                                        break;
                                    }
                                }
                                if(!found1){
                                    System.out.println("Product not found");
                                }
                                break;

                            // Search Customer Details
                            case 4:

                                System.out.print("Enter Customer Email: ");
                                String email = scanner.nextLine();
                                if(email.isEmpty()){
                                    System.out.println("Email cannot be empty");
                                    break;
                                }
                                if(email.length() > 50){
                                    System.out.println("Email should be less than 50 characters");
                                    break;
                                }
                                if(!email.contains("@")){
                                    System.out.println("Please enter a valid email");
                                    break;
                                }
                                boolean found2 = false;
                                for(Customers c : foodMart.customers){
                                    if(c.getCustomerEmail().equals(email)){
                                        found2 = true;
                                        System.out.println("Customer Name: " + c.getCustomerName());
                                        System.out.println("Customer Address: " + c.getCustomerAddress());
                                        System.out.println("Customer Phone: " + c.getCustomerPhone());
                                        System.out.println("Customer Email: " + c.getCustomerEmail());
                                        System.out.println("Customer ID: " + c.getCustomerID());
                                        break;
                                    }
                                }
                                if(!found2){
                                    System.out.println("Customer not found");
                                }
                                break;

                            // View Product Details
                            case 5:

                                if(foodMart.products.isEmpty()){
                                    System.out.println("No Products Found");
                                    break;
                                }

                                foodMart.products.sort(
                                    new Comparator<Product>(){
                                        public int compare(Product p1, Product p2){
                                            return p1.getProductQuantity() - p2.getProductQuantity();
                                        }
                                    }
                                );

                                for(Product p : foodMart.products){
                                    System.out.println("Product Name: " + p.getProductName());
                                    System.out.println("Product ID: " + p.getProductID());
                                    System.out.println("Product Description: " + p.getProductDescription());
                                    System.out.println("Product Price: " + p.getProductPrice());
                                    System.out.println("Product Quantity: " + p.getProductQuantity());
                                    System.out.println();
                                }
                                break;

                            // View Highest Price Product
                            case 6:

                                if(foodMart.products.isEmpty()){
                                    System.out.println("No Products Found");
                                    break;
                                }

                                double maxPrice = 0;
                                Product maxProduct = null;
                                for(Product p : foodMart.products){
                                    if(p.getProductPrice() > maxPrice){
                                        maxPrice = p.getProductPrice();
                                        maxProduct = p;
                                    }
                                }
                                if(maxProduct != null){
                                    System.out.println("Product Name: " + maxProduct.getProductName());
                                    System.out.println("Product ID: " + maxProduct.getProductID());
                                    System.out.println("Product Description: " + maxProduct.getProductDescription());
                                    System.out.println("Product Price: " + maxProduct.getProductPrice());
                                    System.out.println("Product Quantity: " + maxProduct.getProductQuantity());
                                }
                                else{
                                    System.out.println("No Products Found");
                                }
                                break;
                            
                            case 7:
                                flag = true;
                                break;
                            case 8:
                                System.exit(0);
                            default:
                                System.out.println("Invalid Choice");
                        }
                        if(flag){
                            break;
                        }
                    }
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");

            }
        }
    }
}