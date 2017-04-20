package creditcard.service;

import creditcard.ui.MainScreenController;

public class MainWindowService {
	private static MainWindowService instance = new MainWindowService();
	private MainScreenController mainScreenController;
	
	private MainWindowService(){}
	
	public static MainWindowService Current(){
		return instance;
	}

	public void setMainScreenController(MainScreenController mainScreenController){
		this.mainScreenController = mainScreenController;
	}
	
	public MainScreenController getMainScreenController(){
		return this.mainScreenController;
	}
	
}
