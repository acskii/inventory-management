package databases;

import generics.CustomerProduct;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
/*
by Hassan Mohamed
 */


public class CustomerProductDatabase extends GenericDatabase<CustomerProduct> {

    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    public CustomerProduct createRecordFrom(String line) {
        String[] parts = line.split(",");

        if (parts.length < 4) {
            System.out.println("Invalid line format: " + line);
            return null;
        }

        String customerSSN = parts[0];
        String productID = parts[1];
        LocalDate purchaseDate = LocalDate.parse(parts[2], CustomerProduct.DATE_APPEAR);
        boolean paid = Boolean.parseBoolean(parts[3]);

        CustomerProduct record = new CustomerProduct(customerSSN, productID, purchaseDate);
        record.setPaid(paid);
        return record;
    }
}


