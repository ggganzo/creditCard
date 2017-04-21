package creditcard.service;

import java.io.IOException;

import creditcard.factory.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowService {
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 600;
	
	
	public static void ShowLoginScreen() throws IOException{
		Parent root = FXMLLoader.load(WindowService.class.getClass().getResource("/creditcard/view/Login.fxml"));

		Scene scene = new Scene(root, 450, 300);
		setShowForm(scene, ScreenService.Current().getMainStage(), "Login to Creditcard system");
		
	}
	
	public static void ShowMainScreen() throws IOException{
		Parent mainScreen = FXMLLoader.load(WindowService.class.getClass().getResource("/creditcard/ui/MainScreen.fxml"));

		setScene(mainScreen, ScreenService.Current().getMainStage(), "Creditcard system 2.0");
	}
	
	private static void setScene(Parent parent, Stage stage, String title){
		Scene scene = new Scene(parent, SCREEN_WIDTH, SCREEN_HEIGHT);
		setShowForm(scene, stage, title);
	}
	
	private static void setShowForm(Scene scene, Stage stage, String title){
		String userFullName = User.GetCurrent().GetUserFullName();
		if(userFullName.length() > 0){
			title = "Hello " + userFullName + "! | "+ title;
		}
		
		stage.setTitle(title);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
