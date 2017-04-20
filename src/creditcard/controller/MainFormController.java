package creditcard.controller;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import creditcard.model.CreditCardAccount;
import creditcard.service.ScreenService;
import financialcore.customer.Staff;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainFormController implements Initializable {
	private static MainFormController mainController;
	private static Staff currentUser;

	@FXML
	private Label lblWelcome;
	@FXML
	private Button btnLogOut;
	@FXML
	private Pane contentPane;
	@FXML
	private VBox menuContainer;

	public static void setCurrentUser(Staff user) {
		currentUser = user;
	}

	public static Staff getCurrentUser() {
		return currentUser;
	}

	public static MainFormController getMainController() {
		return mainController;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mainController = this;
		// DataAccessFacade.initData();

		SetView("User");
		// currentUser.isLibrarian());
	}

	public void SetView(String user_name) {
		lblWelcome.setText("Welcome " + user_name);

		Button test2 = new Button("Account list");
		test2.setId("accountList");
		test2.getStyleClass().add("my-menu-button");
		test2.setOnAction(e -> changeView("accountList"));

		Button test3 = new Button("Create account");
		test3.setId("createNewAccount");
		test3.getStyleClass().add("my-menu-button");
		test3.setOnAction(e -> changeView("createNewAccount"));

		menuContainer.getChildren().addAll(test2, test3);

	}

	public void logout(ActionEvent event) {
		currentUser = null;
		mainController = null;

		Stage stage = ScreenService.Current().getMainStage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/creditcard/view/Login.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(root, 400, 400);
		stage.setScene(scene);
		stage.show();
	}

	public void changeView(String buttonLabel) {
		System.out.println("SDFasf");
		Pane intent;
		try {
			switch (buttonLabel) {

			case "welcome":
				intent = FXMLLoader.load(getClass().getResource("/creditcard/view/Welcome.fxml"));
				contentPane.getChildren().setAll(intent);
				break;
			case "accountList":
				intent = FXMLLoader.load(getClass().getResource("/creditcard/view/AccountList.fxml"));
				contentPane.getChildren().setAll(intent);
				break;
			case "createNewAccount":
				intent = FXMLLoader.load(getClass().getResource("/creditcard/view/CreateAccount.fxml"));
				contentPane.getChildren().setAll(intent);
				break;
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void changeViewAccount(String buttonLabel, CreditCardAccount account) {
		Pane intent;
		try {
			switch (buttonLabel) {
			case "editEmployee":
				FXMLLoader fxloader = new FXMLLoader(getClass().getResource("/creditcard/view/CreateAccount.fxml"));
				intent = fxloader.load();
				AccountCreateController cont = fxloader.getController();
				cont.setAccount(account);

				contentPane.getChildren().setAll(intent);
				break;
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
