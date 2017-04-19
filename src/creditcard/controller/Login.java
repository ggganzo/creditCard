package creditcard.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.RootPaneContainer;

import application.Main;
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

public class Login implements Initializable {

	@FXML
	private Button btnLogin;
	@FXML
	private TextField txtUserId;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Label lblError;

	// public User static loggedUser = null;

	private void validateUserLogin() throws MyOwnException {
		int user_id = 0;
		try {
			user_id = new Integer(txtUserId.getText());
		} catch (Exception e) {
			throw new MyOwnException();
		}
		String password = passwordField.getText();

	}

	public void loginHandler(ActionEvent event) {

		// validateUserLogin();

		// MainController.setCurrentUser(user);
		try {
			Stage stage1 = Main.getPrimaryStage();
			Parent mainView = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));

			Scene scene = new Scene(mainView, 1000, 700);
			stage1.setScene(scene);
			stage1.show();
		} catch (IOException io) {
			io.printStackTrace();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
