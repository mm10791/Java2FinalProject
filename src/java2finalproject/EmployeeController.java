import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import java.util.*;

public class EmployeeController {
    EmployeeModel model = new EmployeeModel();
    Employee currentEmployee = null;
    int currentIndex = -1;

    //elements
    @FXML
    private ListView<Employee> lvEmployee;

    @FXML
    private Label lblFirstName;

    @FXML
    private Label lblId;

    @FXML
    private Label lblLastName;

    @FXML
    private Label lblPosition;

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextArea txtResult;

    //event handler methods 
    @FXML
    void showList(ActionEvent event) {
        System.out.println("Showing list view");
        showListView();
    }
    @FXML
    void searchResult(ActionEvent event) {
        setResult(model.searchResult(getFirstName(), getLastName(), getPosition(), getId()));
    }
    @FXML
    void addEmployee(ActionEvent event) {
        System.out.println("Adding new Employee");
        setView("EmployeeAddView");
    }
    @FXML
    void editEmployee(ActionEvent event) {

    }
    @FXML
    void openEmployee(MouseEvent event) {
        System.out.println("Showing Employee details");
        currentEmployee = (Employee) lvEmployee.getSelectionModel().getSelectedItem();
        currentIndex = lvEmployee.getSelectionModel().getSelectedIndex();

        setView("EmployeeEditView");
        setEmployee(currentEmployee);
    }
    @FXML
    void clearAll(ActionEvent event) {
        txtFirstName.setText("");
        txtLastName.setText("");
        txtId.setText("");
        txtPosition.setText("");
    }
    @FXML
    void saveEmployee(ActionEvent event) {
        System.out.println("New employee saved");
        Employee employee = getEmployee();
        model.addEmployee(employee);
        showListView();
    }
    @FXML
    void deleteEmployee(ActionEvent event) {
	System.out.println("Deleting Employee");
	model.deleteEmployee(currentIndex);
	showListView();
    }

    public void showListView() {
        setView("EmployeeListView");
        ArrayList<Employee> employeeList = model.readAllEmployees();
        lvEmployee.getItems().setAll(employeeList);
    }

    public void setView(String name) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(name + ".fxml"));

            loader.setController(this);

            Pane view = loader.load();
            mainbody.setCenter(view);
        } catch(Exception e) {
            System.out.print("Error loading" + name + ".fxml " + e);
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
}

