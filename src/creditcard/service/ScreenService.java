package creditcard.service;

import javafx.stage.Stage;

public class ScreenService {
	private static ScreenService instance = new ScreenService();
	private Stage mainStage;
	
	private ScreenService(){}
	
	public static ScreenService Current(){
		return instance;
	}
	
	public Stage getMainStage(){
		return mainStage;
	}
	
	public void setMainStage(Stage aMainStage){
		mainStage = aMainStage;
	}
	
}
