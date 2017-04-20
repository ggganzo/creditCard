package creditcard.ui;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PersonOverviewWindow extends Application {
	private static PersonOverviewWindow instance;
	
	public static PersonOverviewWindow getInstance(){
		if(instance == null){
			instance = new PersonOverviewWindow();
		}
		
		return instance;
	}
	
	@Override
    public void start(Stage primaryStage) throws Exception{
		
		Parent root = FXMLLoader.load(getClass().getResource("PersonOverview.fxml"));
        primaryStage.setTitle("Customer overview");
        Scene scene = new Scene(root, 800, 600);
        
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
	
	public static void main(String[] args) {	
        launch(args);
    }
}