package roles;

import databases.EmployeeUserDatabase;
import generics.EmployeeUser;

import java.util.regex.Pattern;

// Andrew :)

public class AdminRole implements Role {
    private final EmployeeUserDatabase database;

    public AdminRole() {
        /* The filename can be changed to someplace else later on */
        this.database = new EmployeeUserDatabase("employees.txt");
        // Initialise database previous records
        database.readFile();
    }

    public void addEmployee(String employeeId, String name, String email,
                            String address, String phoneNumber) {
        if (validateEmail(email) == null) {
            System.out.println("[AdminRole]: Email given is invalid");
            return;
        }

        if (validatePhoneNumber(phoneNumber) == null) {
            System.out.println("[AdminRole]: Phone number given is invalid");
            return;
        }

        if (!employeeId.startsWith("E")) {
            System.out.println("[AdminRole]: Employee ID must start with an E");
            return;
        }

        EmployeeUser user = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        database.insertRecord(user);
    }

    public EmployeeUser[] getListOfEmployees() {
        return database.returnAllRecords().toArray(new EmployeeUser[0]);
    }

    public void removeEmployee(String key) {
        database.deleteRecord(key);
    }

    @Override
    public void logout() {
        database.saveToFile();
    }

    private String validateEmail(String email) {
        /* Only return null if the email is invalid */

        if (email == null) return null;
        if (!email.contains("@")) return null;
        // (Any number of character [\w+]) [followed by] @ [followed by] . (At least 2 characters [\w{2,}])
        String pattern = "^\\w+@\\w+\\.\\w{2,}$";

        if (!Pattern.matches(pattern, email)) return null;
        else return email;
    }

    private String validatePhoneNumber(String phoneNumber) {
        /* Only corrected sized numbers that start with 01 are valid */
        if (phoneNumber.length() != 11) return null;
        if (!phoneNumber.startsWith("01")) return null;
        return phoneNumber;
    }
}
