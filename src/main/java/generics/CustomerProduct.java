import generics.Generic;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/*
by Hassan Mohamed
 */


public class CustomerProduct implements Generic {
    private final String customerSSN;
    private final String productID;
    private final LocalDate purchaseDate;
    private boolean paid;
    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate){
        this.customerSSN=customerSSN;
        this.productID=productID;
        this.purchaseDate=purchaseDate;
    }
    private static final DateTimeFormatter DATE_APPEAR = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public String getCustomerSSN(){
        return customerSSN;
    }
    public String getProductID(){
        return productID;
    }
    public LocalDate getPurchaseDate(){
        return purchaseDate;
    }
    @Override
    public String lineRepresentation(){
        return customerSSN + "," + productID +"," + purchaseDate.format(DATE_APPEAR) + "," + paid;

    }


    public boolean isPaid() {
        return paid;
    }
    public void setPaid(boolean paid){
        this.paid=paid;

    }
    @Override
    public String getSearchKey(){
        return customerSSN;

    }

}