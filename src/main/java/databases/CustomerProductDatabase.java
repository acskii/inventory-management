package databases;

import generics.CustomerProduct;
import java.io.*;
import java.time.LocalDate;
import java.util.*;


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

    @Override
    public void readFile() {
        // Just reuse the functionality from GenericDatabase
        super.readFile();
    }

    @Override
    public void saveToFile() {
        // Reuse parent method for saving
        super.saveToFile();
    }

    @Override
    public boolean contains(String key) {
        for (CustomerProduct record : this.records) {
            String recordKey = record.getCustomerSSN() + "," +
                    record.getProductID() + "," +
                    record.getPurchaseDate().format(CustomerProduct.DATE_APPEAR);
            if (recordKey.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public CustomerProduct getRecord(String key) {
        for (CustomerProduct record : this.records) {
            String recordKey = record.getCustomerSSN() + "," +
                    record.getProductID() + "," +
                    record.getPurchaseDate().format(CustomerProduct.DATE_APPEAR);
            if (recordKey.equals(key)) {
                return record;
            }
        }
        return null;
    }

    @Override
    public void deleteRecord(String key) {
        this.records.removeIf(record -> {
            String recordKey = record.getCustomerSSN() + "," +
                    record.getProductID() + "," +
                    record.getPurchaseDate().format(CustomerProduct.DATE_APPEAR);
            return recordKey.equals(key);
        });
    }
}
