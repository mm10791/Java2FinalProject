import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class MainSearch extends Application{
    public void start(Stage primaryStage){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("SearchSBView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Search");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("Error loading scene fxml");
        }
        
    }
 
    public static void main(String[] args){
        launch(args);
    }
}
