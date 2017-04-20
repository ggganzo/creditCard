package creditcard.ui;

import java.io.IOException;

import creditcard.factory.User;
import creditcard.service.WindowService;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;

/**
 * Created by orifjon9 on 4/20/2017.
 */
public class MainScreenController {
	@FXML
	private Menu menuLogout;
	
	@FXML
	private void logoutAction(){
		try {
			User.GetCurrent().setStaff(null);
		
			WindowService.ShowLoginScreen();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
