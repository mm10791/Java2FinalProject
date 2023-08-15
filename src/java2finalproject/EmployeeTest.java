import java.util.ArrayList;

public class EmployeeTest {
    public static void main(String[] args) {
        Employee employee1 = new Employee("John", "Doe", "assistant manager", 12345);
        Employee employee2 = new Employee("Jane", "Doe", "general manager", 23456);

        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);

        EmployeeModel model = new EmployeeModel();

        model.writeAllEmployees(employeeList);
        System.out.println(model.readAllEmployees());

        model.addEmployee(new Employee("Peter", "Parker", "cashier", 34567));
        System.out.println(model.readAllEmployees());

        model.deleteEmployee(1);
        System.out.println(model.readAllEmployees());
    }
}