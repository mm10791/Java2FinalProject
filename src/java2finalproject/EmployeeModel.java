import java.io.*;
import java.util.*;

public class EmployeeModel{	
	//employees.txt
	File employeeFile = new File("employees.txt");

	public void writeAllEmployees(ArrayList<Employee> employeeList){
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(employeeFile);
			for(Employee employee: employeeList){
				pw.println(employee.fullString());
			}
		}catch(Exception e) {
			System.out.println("Error writing employee");
		}finally {
			if(pw != null) pw.close();
		}
	}

	public ArrayList<Employee> readAllEmployees(){
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		Scanner scan = null;
		try{
			scan = new Scanner(employeeFile);
			while(scan.hasNext()){
				String employeeLine = scan.nextLine();
				Employee employee = Employee.parseEmployee(employeeLine);
				employeeList.add(employee);
			}

		}catch(Exception e) {
			System.out.println("Error reading employee");
		}finally {
			if(scan != null) scan.close();
		}

		return employeeList;
	}

	public void addEmployee(Employee employee){
		ArrayList<Employee> employeeList = readAllEmployees();
		employeeList.add(employee);
		writeAllEmployees(employeeList);
	}

	public void deleteEmployee(int index){
        ArrayList<Employee> employeeList = readAllEmployees();
        employeeList.remove(index);
        writeAllEmployees(employeeList);
    }
    

}