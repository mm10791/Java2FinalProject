// public class SearchController {
//     SearchModel model;
//     SearchView view;

//     public SearchController(){
//         model = new SearchModel();
//         view = new SearchView(model);
//         view.getButtonSearch().setOnAction(e -> searchMethod());
//         //view.getButtonClear().setOnAction(e -> clearMethod());

//     }
//     public void searchMethod(){
//         view.setResult(model.searchResult(view.getEmpID(), view.getEmpName(), view.getEmpPosition()));
//     }
//     // public void clearMethod(){
//     //     view.clearAll(model.clearID(""),model.clearName(""), model.clearPosition(""), model.clearResult(""));
//     // }
//     public SearchModel getModel(){
//         return model;
//     }
//     public SearchView getView(){
//         return view;
//     }
// }

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SearchController {
    SearchModel model = new SearchModel();

    @FXML
    private Label lblID;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPosition;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextArea txtResult;

    @FXML
    void clearAll(ActionEvent event) {
        txtID.setText("");
        txtName.setText("");
        txtPosition.setText("");
        txtResult.setText("");
    }

    @FXML
    void searchResult(ActionEvent event) {
        setResult(model.searchResult(getID(), getName(), getPosition()));
    }
    public String getID(){
        return txtID.getText();
    }
    public String getName(){
        return txtName.getText();
    }
    public String getPosition(){
        return txtPosition.getText();
    }
    public void setResult(String value){
        txtResult.setText(value);
    }
    // public void clear(){
    //     txtID.setText("");
    //     txtName.setText("name");
    //     txtPosition.setText("");
    //     txtResult.setText("");
    // }

}

