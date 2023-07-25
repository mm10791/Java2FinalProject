import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainSearch extends Application{
    TextField txtID;
    TextField txtName;
    TextField txtPosition;
    TextArea txtResult;
    Button btnSearch;
    public void start(Stage primaryStage){
        primaryStage.setTitle("Search");
        BorderPane pane = new BorderPane();
        BorderPane input = new BorderPane();
        GridPane fields = new GridPane();
        Button btnClear = new Button("Clear");
        HBox buttons = new HBox();

        Label lblName = new Label("Name: ");
        Label lblID = new Label("ID: ");       
        Label lblPOsition = new Label("Position: ");      

        btnSearch = new Button("Search");
        txtID = new TextField();
        txtName = new TextField();
        txtPosition = new TextField();
        txtResult = new TextArea();

        fields.setPadding(new Insets(5));
        fields.setVgap(3);
        fields.setHgap(3);
        fields.add(lblID, 0,0,1,1);
        fields.add(lblName, 0,1,1,1);
        fields.add(lblPOsition, 0,2,1,1);

        fields.add(txtID, 1,0,3,1);
        fields.add(txtName, 1,1,3,1);
        fields.add(txtPosition, 1,2,3,1);

        input.setTop(fields);
        input.setBottom(txtResult);
        pane.setCenter(input);
        
        pane.setTop(new Label("Search"));
        buttons.setPadding(new Insets(5));
        buttons.setSpacing(5);
        buttons.getChildren().add(btnSearch);
        buttons.getChildren().add(btnClear);
        pane.setBottom(buttons);  

        btnSearch.setOnAction(e -> btnSearchClicked());
        btnClear.setOnAction(e -> btnClearClicked());

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     public ArrayList<Employee> readEmployees(){
        ObjectInputStream input = null;
        ArrayList<Employee> myList = new ArrayList<Employee>();
        try {
            input = new ObjectInputStream(new FileInputStream("employees.dat"));
            while(true){
                Employee acc = (Employee)input.readObject();
                myList.add(acc);
            }   
        } catch(EOFException eof){
        }catch (Exception e) {
            System.out.println("Error reading objects " + e);
        }finally{
            try {
                input.close();
            } catch (Exception e) {
                System.out.println("Error closing");
            }
        }
        return myList;
    }

    public void btnSearchClicked(){
        ArrayList<Employee> myArrList = readEmployees();
        String result = "";
        String name = txtName.getText();
        String id = txtID.getText();
        String position = txtPosition.getText();
        for(int i = 0; i < myArrList.size(); i ++){
            if (name.equals(myArrList.get(i).name.toString()) || id.equals(myArrList.get(i).id) || position.equals(myArrList.get(i).position.toString())){
                result = myArrList.get(i).name + "," + myArrList.get(i).id 
                               + "," + myArrList.get(i).position;
            txtResult.setText(result.toString());
            }
        }
    }

    public void btnClearClicked(){
        txtID.setText("");
        txtName.setText("");
        txtPosition.setText("");
        txtResult.setText("");
    }
    public static void main(String[] args){
        launch(args);
    }
}
