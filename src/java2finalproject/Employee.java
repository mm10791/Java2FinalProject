import java.io.Serializable;
public class Employee implements Serializable {
    //variables
    private String firstName;
    private String lastName;
    private String position;
    private int id;

    //set variables
    public Employee(String firstName, String lastName, String position, int id){
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.id = id;
    }

    //getters and setters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String fullString() {
        return "Employee info: First Name: " + firstName + ", Last Name: " + lastName + ", ID: " + id + ", Position: " + position;
    }    

    public static Employee parseEmployee(String str) {
        String[] parts = str.split(", ");
        String firstName = parts[1].substring(parts[1].indexOf("First Name:") + 12);
        String lastName = parts[2].substring(parts[2].indexOf("Last Name:") + 11);
        String position = parts[3].substring(parts[3].indexOf("Position:") + 9);
        int id = Integer.parseInt(parts[4].substring(parts[4].indexOf("ID:") + 4));

        Employee employee = new Employee(firstName, lastName, position, id);
        return employee;
    }
}
