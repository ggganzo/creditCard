package application;

import creditcard.service.ScreenService;
import creditcard.service.WindowService;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	

	@Override
	public void start(Stage primaryStage) {
		try {
			ScreenService.Current().setMainStage(primaryStage);
			
			WindowService.ShowLoginScreen();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
