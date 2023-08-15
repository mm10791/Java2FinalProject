import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class EmployeeModel{	
	//Write to file
	private File employeeFile = new File("employees.txt");

	public void writeAllEmployees(ArrayList<Employee> employeeList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(employeeFile))) {
            oos.writeObject(employeeList);
        } catch (IOException e) {
            System.out.println("Error writing employee: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> readAllEmployees() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(employeeFile))) {
            employeeList = (ArrayList<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading employee: " + e.getMessage());
            e.printStackTrace();
        }
        return employeeList;
    }

    //Search for employee
    public ArrayList<Employee> searchResult(String firstName, String lastName, String position, String id) {
        ArrayList<Employee> employeeList = readAllEmployees();
        ArrayList<Employee> searchResults = new ArrayList<>();
    
        for (Employee employee : employeeList) {
            if (employee.getFirstName().contains(firstName) ||
                employee.getLastName().contains(lastName) ||
                employee.getPosition().contains(position) ||
                String.valueOf(employee.getId()).contains(id)) {
                searchResults.add(employee);
            }
        }
        return searchResults;
    }
    


    //Update/edit the employee info 
    public void updateEmployee(int index, Employee employee) {
        ArrayList<Employee> employeeList = readAllEmployees();
        if (index >= 0 && index < employeeList.size()) {
            employeeList.set(index, employee);
            writeAllEmployees(employeeList);
        }
    }

    public void saveEmployeesToFile() {
        ArrayList<Employee> employeeList = readAllEmployees();
        writeAllEmployees(employeeList);
    }


	public void addEmployee(Employee employee){
		ArrayList<Employee> employeeList = readAllEmployees();
		employeeList.add(employee);
		writeAllEmployees(employeeList);
	}

	public void deleteEmployee(int index) {
        ArrayList<Employee> employeeList = readAllEmployees();
        //Check bounds
        if (index >= 0 && index < employeeList.size()) {
            employeeList.remove(index);
            writeAllEmployees(employeeList);
        }
    }
}