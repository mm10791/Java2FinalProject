public class Employee {
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

    public String fullString() {
        return "Employee info: " + firstName + " " + lastName + ", ID " + id + ", Position " + position;
    }

    //parse the variables
    public static Employee parseEmployee(String str){
        String[] parts = str.split(", ");
        String firstName = parts[0].substring(parts[0].indexOf(":") + 1);
        String lastName = parts[1];
        String position = parts[2];
        int id = Integer.parseInt(parts[3].substring(parts[3].indexOf("ID ") + 3));

        Employee employee = new Employee(firstName, lastName, position, id);
        return employee;
    }

}