package creditcard.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.RootPaneContainer;

import application.Main;
import creditcard.factory.User;
import creditcard.service.WindowService;
import databaseLayer.contextLayer.ContextLayer;
import databaseLayer.exception.LoginException;
import financialcore.customer.Staff;
import financialcore.general.MyOwnException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	@FXML
	private Button btnLogin;
	@FXML
	private TextField txtUserName;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Label lblError;

	// public User static loggedUser = null;

	private boolean validateUserLogin() throws LoginException {
		String userName  = txtUserName.getText();
		String password = txtPassword.getText();
		
		Staff staff = ContextLayer.Model().Persons().getStaff(userName, password);
		if(staff!= null){
			User.GetCurrent().setStaff(staff);
			return true;
		}
		else{
			throw new LoginException("Incorrect login or password. Please, try again");
		}
	}

	public void loginHandler(ActionEvent event) {
		try {
				if(validateUserLogin())
				{
					WindowService.ShowMainScreen();
				}
		}catch (LoginException ex) {
			lblError.setText(ex.getMessage());
		}
		catch (IOException io) {
			io.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
