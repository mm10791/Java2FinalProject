import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import java.util.*;

public class EmployeeController {
    //Elements
    EmployeeModel model = new EmployeeModel();
    private Employee currentEmployee = null;
    private int currentIndex = -1;

    @FXML private ListView<Employee> lvEmployee;
    @FXML private BorderPane mainbody;
    @FXML private Label lblFirstName, lblId, lblLastName, lblPosition;
    @FXML private TextField txtFirstName, txtId, txtLastName, txtPosition, txtResult, txtSearchString;

     //event handler methods 
     @FXML
     void showList(ActionEvent event) {
         System.out.println("Showing Employee list...");
         showListView();
     }
     @FXML
    void searchResult(ActionEvent event) {
        ArrayList<Employee> searchResults = model.searchResult(getSearchString());
        lvEmployee.getItems().setAll(searchResults);
    }

 
     @FXML
     void addEmployee(ActionEvent event) {
         System.out.println("Adding Employee...");
         setView("EmployeeAddView");
     }
     @FXML
     void editEmployee(ActionEvent event) {
         if (currentEmployee != null && currentIndex != -1) {
             System.out.println("Editing Employee...");
 
             //Update the properties of the currentEmployee
             currentEmployee.setFirstName(txtFirstName.getText());
             currentEmployee.setLastName(txtLastName.getText());
             currentEmployee.setPosition(txtPosition.getText());
 
 
             //Update the employee in the model and save changes
             model.updateEmployee(currentIndex, currentEmployee);
             model.saveEmployeesToFile();
 
             showListView();
         }
     }
 
 
     @FXML
     void openEmployee(MouseEvent event) {
         System.out.println("Showing Employee details...");
         Employee selectedEmployee = lvEmployee.getSelectionModel().getSelectedItem();
         if (selectedEmployee != null) {
             currentEmployee = selectedEmployee;
             currentIndex = lvEmployee.getSelectionModel().getSelectedIndex();
     
             setView("EmployeeEditView");
             setEmployee(currentEmployee);
         }
     }
 
     @FXML
     void clearAll(ActionEvent event) {
         txtSearchString.setText("");
     }
     @FXML
     void saveEmployee(ActionEvent event) {
         System.out.println("Saving new Employee...");
         Employee employee = getEmployee();
         model.addEmployee(employee);
         showListView();
     }
     @FXML
     void deleteEmployee(ActionEvent event) {
     System.out.println("Deleting Employee...");
     model.deleteEmployee(currentIndex);
     showListView();
     }
 
     public void showListView() {
         setView("EmployeeListView");
         ArrayList<Employee> employeeList = model.readAllEmployees();
         lvEmployee.getItems().setAll(employeeList);
     }
 
     public void setView(String name) {
         try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource(name + ".fxml"));
             loader.setController(this);
 
             Pane view = loader.load();
             mainbody.setCenter(view);
             //contentPane.getChildren().setAll(view); // Replace content in the contentPane
         }  catch(Exception e) {
             System.out.print("Error loading" + name + ".fxml " + e.getMessage());
             e.printStackTrace();
         }
     }
 
     public Employee getEmployee() {
         int id = Integer.parseInt(txtId.getText()); 
         return new Employee(txtFirstName.getText(), txtLastName.getText(), txtPosition.getText(), id);
 
     }
 
     public void setEmployee(Employee employee) {
         txtFirstName.setText(employee.getFirstName());
         txtLastName.setText(employee.getLastName());
         txtPosition.setText(employee.getPosition());
         txtId.setText(Integer.toString(employee.getId()));
     }
     
    public String getSearchString(){
        return txtSearchString.getText();
    }
    

     public void setResult(String value){
        txtResult.setText(value);
    }
 }
 
 