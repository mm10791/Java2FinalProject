import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.fxml.FXMLLoader;

public class EmployeeApp extends Application {
  public static void main(String[] args) {
	launch(args);
  }

  public void start(Stage primaryStage) {
	try {
		Parent root = FXMLLoader.load(getClass().getResource("EmployeeListView.fxml"));

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}catch(Exception e) {
		System.out.println("Error loading fxml "+ e);
	}

  }

}