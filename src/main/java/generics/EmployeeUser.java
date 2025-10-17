package generics;

// Andrew :)

public class EmployeeUser implements Generic {
    /* Members */
    private String employeeId;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;

    /* All arguments constructor */
    public EmployeeUser(String employeeId, String name, String email,
                        String address, String phoneNumber) {
        this.employeeId = employeeId;
        this.email = email;
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /* Implementation of interface */
    @Override
    public String lineRepresentation() {
        return String.format(
                "%s,%s,%s,%s,%s",
                this.employeeId, this.name, this.email,
                this.address, this.phoneNumber
        );
    }

    @Override
    public String getSearchKey() {return this.employeeId;}

    /* Getters */
    public String getName() {return this.name;}
    public String getEmail() {return this.email;}
    public String getAddress() {return this.address;}
    public String getPhoneNumber() {return this.phoneNumber;}
}
