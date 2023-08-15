import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class EmployeeModel{	
	//Write to file
	private File employeeFile = new File("employees.txt");

	public void writeAllEmployees(ArrayList<Employee> employeeList) {
        System.out.println("writing all employees... ("+ employeeList.size() +")");
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(employeeFile))) {
    
            for (Employee employee : employeeList) {
                dos.writeInt(employee.getId());
                dos.writeUTF(employee.getFirstName());
                dos.writeUTF(employee.getLastName());
                dos.writeUTF(employee.getPosition());
            }
            dos.close();
        } catch (IOException e) {
            System.out.println("Error writing employee: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> readAllEmployees() {
        System.out.println("reading all employees...");
        ArrayList<Employee> employeeList = new ArrayList<>();
        try(DataInputStream dis = new DataInputStream(new FileInputStream(employeeFile))){
            int id = dis.readInt();
            String firstname = dis.readUTF();
            String lastname = dis.readUTF();
            String pos = dis.readUTF();
            Employee emp = new Employee(firstname, lastname, pos, id);

            employeeList.add(emp);
        }catch(Exception e){

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
    

    //Search for employee
    public ArrayList<Employee> searchResult(String searchString) {
        ArrayList<Employee> employeeList = readAllEmployees();
        ArrayList<Employee> searchResults = new ArrayList<>();
        
        for (Employee employee : employeeList) {
            if (employee.getFirstName().contains(searchString) ||
                employee.getLastName().contains(searchString) ||
                employee.getPosition().contains(searchString) ||
                String.valueOf(employee.getId()).contains(searchString)) {
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