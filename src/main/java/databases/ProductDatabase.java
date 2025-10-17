/*
  writers:
    Abdoalrahman khedr
*/
package databases;

import generics.Product;
import java.io.*;
import java.util.*;

public class ProductDatabase extends GenericDatabase<Product> {

    public ProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public Product createRecordFrom(String line) {
        if (line == null) return null;

        // line representation form: ProductId,productName,manufacturerName,supplierName,quantity,price
        String[] elements = line.split(",");

        // if line representation is false it will return null
        if (elements.length < 6) {
            System.out.println("The line representation of product is false");
            return null;
        }

        String productId = elements[0];
        String productName = elements[1];
        String manufacturerName = elements[2];
        String supplierName = elements[3];

        int quantity;
        float price;

        try {
            quantity = Integer.parseInt(elements[4]);
            price = Float.parseFloat(elements[5]);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format in product data!" + e.getMessage());
            return null; // skip this record and return null if numbers are invalid
        }

        Product createdProduct = new Product(productId, productName, manufacturerName, supplierName, quantity, price);
        return createdProduct;
    }
}
