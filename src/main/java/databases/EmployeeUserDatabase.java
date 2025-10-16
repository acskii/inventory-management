package databases;

import generics.EmployeeUser;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;

public class EmployeeUserDatabase extends Database<EmployeeUser> {

    public EmployeeUserDatabase(String filename) {
        super(filename);
    }

    @Override
    public void insertRecord(EmployeeUser record) {
        /* Inherited method didn't check for uniqueness of records */
        if (record == null) return;
        if (!contains(record.getSearchKey())) this.records.add(record);
    }

    @Override
    public void deleteRecord(String key) {
        // Iterate through all records and remove only the one
        // with matching search keys
        this.records.removeIf((user) -> user.getSearchKey().equals(key));
    }

    @Override
    public boolean contains(String key) {
        // Iterate through all records and return true
        // only if matching search keys are found
        for (EmployeeUser user : this.records) {
            if (user.getSearchKey().equals(key)) return true;
        }

        return false;
    }

    @Override
    public EmployeeUser getRecord(String key) {
        // Iterate through all records and return the record
        // with matching search keys
        // otherwise return null
        for (EmployeeUser user : this.records) {
            if (user.getSearchKey().equals(key)) return user;
        }

        return null;
    }

    @Override
    public EmployeeUser createRecordFrom(String line) {
        // Note:
        // EmployeeUser.lineRepresentation() returns:
        //      "{employeeId},{name},{email},{address},{phoneNumber}"
        //           [0]        [1]    [2]     [3]        [4]
        if (line == null) return null; // Makes sure no null was passed

        String[] data = line.split("\\s*,\\s*");

        // If the given string doesn't represent EmployeeUser as expected
        if (data.length < 5 || line.isEmpty()) {
            // POSSIBLE: Unnecessary prints
            System.out.println("[EmployeeUserDatabase]: Invalid line representation for EmployeeUser was given");
            return null;
        }

        String employeeId = data[0];
        String name = data[1];
        String email = data[2];
        String address = data[3];
        String phoneNumber = data[4];

        return new EmployeeUser(employeeId, name, email, address, phoneNumber);
    }

    @Override
    public void readFile() {
        File file = new File(this.filename);

        // Check if the file path is absolute and if the file exists
        // File must be a .txt
        if (!file.exists() || !file.getName().endsWith(".txt")) {
            System.out.println("[EmployeeUserDatabase]: Unable to read file");
            return;
        }

        try (Scanner reader = new Scanner(file)) {
            // Create Scanner resource and cleanup

            // Reset records before reading
            this.records.clear();
            // As long as the file has lines,
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                EmployeeUser user = createRecordFrom(line);
                insertRecord(user);
            }
            System.out.println("[EmployeeUserDatabase]: Successfully read all records!");
        } catch (FileNotFoundException err) {
            System.out.println("[EmployeeUserDatabase]: File was not found");
        }
    }

    @Override
    public void saveToFile() {
        File file = new File(this.filename);

        try {
            if (!file.exists() && file.getName().endsWith(".txt")) {
                if (file.createNewFile()) System.out.println("[EmployeeUserDatabase]: Successfully created new file!");
                else {
                    System.out.println("[EmployeeUserDatabase]: Failed to create new file");
                    return;
                }
            }
        } catch (IOException err) {
            System.out.println("[EmployeeUserDatabase]: Failed to create new file");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filename))) {
            // Create BufferedWriter resource and cleanup

            StringBuilder sb = new StringBuilder();
            // Build a string from all record line representations
            // and write that to file
            for (EmployeeUser user : this.records) {
                sb.append(user.lineRepresentation()).append("\n");
            }
            writer.write(sb.toString());
            System.out.println("[EmployeeUserDatabase]: Successfully wrote to file!");
        } catch (IOException err) {
            System.out.println("[EmployeeUserDatabase]: File can not be accessed, perhaps it is being used by another program..");
        }
    }
}
