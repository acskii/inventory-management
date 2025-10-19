package roles;

import databases.ProductDatabase;
import databases.CustomerProductDatabase;
import generics.Product;
import generics.CustomerProduct;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Pattern;

/*
    writers:
    Ahmed Sherif

    editor:
    Andrew Sameh
*/

public class EmployeeRole implements Role {

    private final ProductDatabase productsDatabase;
    private final CustomerProductDatabase customerProductDatabase;

   
    private  final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    public EmployeeRole() {
        this.productsDatabase = new ProductDatabase("products.txt");
        this.customerProductDatabase = new CustomerProductDatabase("customers_products.txt");

        this.productsDatabase.readFile();
        this.customerProductDatabase.readFile();
    }

  
    public void addProduct(String productId, String productName, String manufacturerName, String supplierName, int quantity, float price) {
        if (!productId.startsWith("P")) {
            System.out.println("[EmployeeRole]: Product ID must start with a P");
            return;
        }
        Product p = new Product(productId, productName, manufacturerName, supplierName, quantity, price);
        productsDatabase.insertRecord(p);
    }


    public Product[] getListOfProducts() {
        List<Product> list = productsDatabase.returnAllRecords();
        return list.toArray(new Product[0]);
    }

   
    public CustomerProduct[] getListOfPurchasingOperations() {
        List<CustomerProduct> list = customerProductDatabase.returnAllRecords();
        return list.toArray(new CustomerProduct[0]);
    }

   
    public boolean purchaseProduct(String customerSSN, String productId, LocalDate purchaseDate) {
        if (validateSSN(customerSSN) == null) {
            System.out.println("[EmployeeRole]: customer SSN given is not valid");
            return false;
        }
        Product p = productsDatabase.getRecord(productId);

        if (p == null || p.getquantity() <= 0)
            return false;

        p.setQuantity(p.getquantity() - 1);

        CustomerProduct purchase = new CustomerProduct(customerSSN, productId, purchaseDate);

        customerProductDatabase.insertRecord(purchase);

        productsDatabase.deleteRecord(productId);
        productsDatabase.insertRecord(p);

        return true;
    }

    public double returnProduct(String customerSSN, String productId, LocalDate purchaseDate, LocalDate returnDate) {
        if (validateSSN(customerSSN) == null) {
            System.out.println("[EmployeeRole]: customer SSN given is not valid");
            return -1;
        }
        if (returnDate.isBefore(purchaseDate)) return -1;

        Product p = productsDatabase.getRecord(productId);
        if (p == null) return -1;

        String key = customerSSN + "," + productId + "," + purchaseDate.format(FMT);
        if (!customerProductDatabase.contains(key)) return -1;

        long days = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (days > 14) return -1;

    
        p.setQuantity(p.getquantity() + 1);
    
        customerProductDatabase.deleteRecord(key);
        productsDatabase.deleteRecord(productId);
        productsDatabase.insertRecord(p);

        return p.getprice();
    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {
        if (validateSSN(customerSSN) == null) {
            System.out.println("[EmployeeRole]: customer SSN given is not valid");
            return false;
        }

        String dateStr = purchaseDate.format(FMT);
        for (CustomerProduct x : customerProductDatabase.returnAllRecords()) {
            if (x.getCustomerSSN().equals(customerSSN) && x.getPurchaseDate().format(FMT).equals(dateStr)&& !x.isPaid()) {

                x.setPaid(true);
               
                customerProductDatabase.deleteRecord(x.getSearchKey());
                customerProductDatabase.insertRecord(x);
                return true;
            }
        }
        return false;
    }

    @Override
    public void logout() {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }

    private String validateSSN(String customerSSN) {
        /* Only valid if the customer SSN is made up of 10 numbers */
        if (customerSSN.length() != 10) return null;
        if (Pattern.matches("[0-9]{10}", customerSSN)) return customerSSN;
        return null;
    }
}
