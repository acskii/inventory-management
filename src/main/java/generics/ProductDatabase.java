/*
  writers:
    Abdoalrahman khedr
*/
package databases;


import entities.Product;
import java.io.*;
import java.util.*;

public class ProductDatabase extends GenericDatabase<Product> {

    public ProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public Product createRecordFrom(String line) {
        if(line==null) return null;
        //line repersentation form:ProductId,productName,manufacturerName,supplierName,quantity,price
        String elements[]=line.split(",");
        //if line repersentation is false it will return false
        if(elements.length<6){
            System.out.println("The ine repersentation of product is false");
            return null;
        }
        String ProductId=elements[0];
        String productName=elements[1];
        String manufacturerName=elements[2];
        String supplierName=elements[3];
        int quantity=Integer.parseInt(elements[4]);
        float price=Float.parseFloat(elements[5]);
        Product createdProducted=new Product(ProductId,productName,manufacturerName,supplierName,quantity,price);
        return createdProducted; 
    }
}
