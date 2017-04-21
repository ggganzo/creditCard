package creditcard.ui;

import java.io.IOException;

import creditcard.factory.User;
import creditcard.service.MainWindowService;
import creditcard.service.WindowService;
import financialcore.customer.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.layout.Pane;

/**
 * Created by orifjon9 on 4/20/2017.
 */
public class MainScreenController {

	@FXML
	public void initialize() {
		MainWindowService.Current().setMainScreenController(this);
	}

	@FXML
	private Menu menuLogout;

	@FXML
	private Pane paneMain;

	@FXML
	private void logoutAction() {
		try {
			User.GetCurrent().setStaff(null);

			WindowService.ShowLoginScreen();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@FXML
	public void openCustomerView() {
		try {
			FXMLLoader parent = new FXMLLoader(getClass().getResource("/creditcard/ui/PersonOverview.fxml"));
			Parent intent = parent.load();
			PersonOverviewController controller = parent.getController();
			paneMain.getChildren().setAll(intent);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void openCustomerCreate() {
		try {
			Parent intent = FXMLLoader.load(getClass().getResource("/creditcard/ui/PersonForm.fxml"));
			paneMain.getChildren().setAll(intent);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openCustomerEdit(Customer customer) {
		try {
			FXMLLoader fxloader = new FXMLLoader(getClass().getResource("/creditcard/ui/PersonForm.fxml"));
			Parent intent = fxloader.load();

			PersonFormController cont = fxloader.getController();
			cont.setCustomer(customer);

			paneMain.getChildren().setAll(intent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void openAccountView() {
		try {
			Parent intent = FXMLLoader.load(getClass().getResource("/creditcard/view/AccountList.fxml"));
			paneMain.getChildren().setAll(intent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void openAccountCreate() {
		try {
			Parent intent = FXMLLoader.load(getClass().getResource("/creditcard/view/CreateAccount.fxml"));
			paneMain.getChildren().setAll(intent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	public void openTransactionView() {
		try {
			Parent intent = FXMLLoader.load(getClass().getResource("/creditcard/ui/TransactionOverview.fxml"));
			paneMain.getChildren().setAll(intent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Pane getMainPane() {
		return paneMain;
	}
}
