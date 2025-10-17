/*
writers:
Abdoalrahman khedr
*/
package generics;
import generics.Generic;

public class Product implements Generic  {
    //Attributes
    private String ProductId;
    private String productName;
    private String manufacturerName;
    private String supplierName;
    private int quantity;
    private float price;
    //Constructor
    public Product(String ProductId,String productName,String manufacturerName,String supplierName,int quantity,float price){
        this.ProductId=ProductId;
        this.productName=productName;
        this.manufacturerName=manufacturerName;
        this.supplierName=supplierName;
        this.quantity=quantity;
        this.price=price;
    } 
    //Getters
    public  String getProductId(){return this.ProductId;};
    public  String getproductName(){return this.productName;};
    public  String getmanufacturerName(){return this.manufacturerName;};
    public  String getsupplierName(){return this.supplierName;};
    public  int getquantity(){return this.quantity;};
    public  float getprice(){return this.price;};
    //Setter for quantity
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    //Implementation of interface
    @Override
    public String lineRepresentation() {
        return String.format(
                "%s,%s,%s,%s,%d,%f",
                this.ProductId, this.productName, this.manufacturerName,
                this.supplierName, this.quantity,this.price
        );
    }
    @Override
    public String getSearchKey() {
        return this.ProductId;
    }
}
