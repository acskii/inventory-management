import roles.AdminRole;
import roles.EmployeeRole;

import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
        // main code here
        System.out.println("---- AdminRole ----");
        AdminRole admin = new AdminRole();
        System.out.println("--------");

        /* Add Test Employees */
        admin.addEmployee("E100", "Andrew",
                "test@gmail.com", "Alexandria", "01345678901");
        admin.addEmployee("E200", "Hassan",
                "test2@gmail.com", "Alexandria", "01345678901");
        admin.addEmployee("E300", "Ahmed",
                "test3@gmail.com", "Alexandria", "01345678901");

        /* Remove one of them */
        admin.removeEmployee("P200");   // Oops :P
        admin.removeEmployee("E200");

        /* Print the remaining */
        for (var e : admin.getListOfEmployees()) System.out.println(e.lineRepresentation());
        System.out.println("--------");

        /* Save & Reload */
        admin.logout();
        admin = new AdminRole();
        System.out.println("--------");
        for (var e : admin.getListOfEmployees()) System.out.println(e.lineRepresentation());
        System.out.println("--------");

        /* ---- */

        System.out.println("---- EmployeeRole ----");
        EmployeeRole employee = new EmployeeRole();
        System.out.println("--------");

        /* Add Test Products */
        employee.addProduct("P100", "Laptop", "Dell", "TechSupplier", 10, 1200.99f);
        employee.addProduct("P200", "Mouse", "Logitech", "PeripheralCo", 50, 25.50f);
        employee.addProduct("P300", "Keyboard", "Microsoft", "OfficeSupply", 30, 45.75f);

        /* Make some purchases */
        LocalDate today = LocalDate.now();
        employee.purchaseProduct("0123456789", "P100", today);
        employee.purchaseProduct("1023456789", "P200", today);

        /* Apply payment for one purchase */
        employee.applyPayment("0123456789", today);

        /* Try to return a product */
        LocalDate returnDate = today.plusDays(5);   // After 5 days
        double refund = employee.returnProduct("0123456789", "P100", today, returnDate);
        System.out.println("Refund amount: " + refund);

        /* Print current state */
        for (var p : employee.getListOfProducts()) System.out.println(p.lineRepresentation());
        System.out.println("--------");

        for (var cp : employee.getListOfPurchasingOperations()) System.out.println(cp.lineRepresentation());
        System.out.println("--------");

        /* Save & Reload */
        employee.logout();
        employee = new EmployeeRole();
        System.out.println("--------");

        for (var p : employee.getListOfProducts()) System.out.println(p.lineRepresentation());
        System.out.println("--------");

        for (var cp : employee.getListOfPurchasingOperations()) System.out.println(cp.lineRepresentation());
        System.out.println("--------");
    }
}
