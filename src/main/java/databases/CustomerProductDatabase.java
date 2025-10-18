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
        LocalDate purchaseDate;

        try {
            purchaseDate = LocalDate.parse(parts[2], CustomerProduct.DATE_APPEAR);
        } catch (Exception e) {
            System.out.println("Invalid date format in line: " + line);

            return null;

        }

        boolean paid;
        try {
            paid = Boolean.parseBoolean(parts[3]);
        } catch (Exception e) {
            System.out.println("Invalid paid flag in line: " + line);
            paid = false; // default to false
        }

        CustomerProduct record = new CustomerProduct(customerSSN, productID, purchaseDate);
        record.setPaid(paid);
        return record;
    }

}


