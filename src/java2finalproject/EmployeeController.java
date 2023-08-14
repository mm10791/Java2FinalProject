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
    private GridPane mainbody;
    
    @FXML
    private Pane contentPane;

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
        ArrayList<Employee> searchResults = model.searchResult(
            txtFirstName.getText(),
            txtLastName.getText(),
            txtPosition.getText(),
            txtId.getText()
        );

    StringBuilder resultText = new StringBuilder();
    for (Employee employee : searchResults) {
        resultText.append(employee.fullString()).append("\n");
    }

    txtResult.setText(resultText.toString());
}

    @FXML
    void addEmployee(ActionEvent event) {
        System.out.println("Adding new Employee");
        setView("EmployeeAddView");
    }
    @FXML
    void editEmployee(ActionEvent event) {
        if (currentEmployee != null && currentIndex != -1) {
            System.out.println("Editing Employee");

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
        System.out.println("Showing Employee details");
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(name + ".fxml"));
            loader.setController(this);

            Pane view = loader.load();
            contentPane.getChildren().setAll(view); // Replace content in the contentPane
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
}

