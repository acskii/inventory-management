package roles;

import databases.EmployeeUserDatabase;
import generics.EmployeeUser;

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
}
