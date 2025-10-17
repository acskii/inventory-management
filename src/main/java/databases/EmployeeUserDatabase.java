package databases;

import generics.EmployeeUser;

// Andrew :)
/*
    Update as of 17/10/2025:
    Teammate @Abdoalrahmankhedr compiled most of the methods I commited before
    and added it in GenericDatabase for other database implementations
    Modified code to avoid repeating methods
*/

public class EmployeeUserDatabase extends GenericDatabase<EmployeeUser> {

    public EmployeeUserDatabase(String filename) {
        super(filename);
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
}
