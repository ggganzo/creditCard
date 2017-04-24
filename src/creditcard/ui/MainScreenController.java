package creditcard.ui;

import java.io.IOException;

import creditcard.controller.TranPurchaseController;
import creditcard.controller.TranRepaymentController;
import creditcard.controller.TranStatementController;
import creditcard.controller.TranWithdrawController;
import creditcard.factory.User;
import creditcard.model.CreditCardAccount;
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
	
	
	@FXML
	private void openTransactionPurchase() {
		try {
			Parent intent = FXMLLoader.load(getClass().getResource("/creditcard/view/TranPurchase.fxml"));
			paneMain.getChildren().setAll(intent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openTransactionPurchase(CreditCardAccount account) {
		try {
			FXMLLoader fxloader = new FXMLLoader(getClass().getResource("/creditcard/view/TranPurchase.fxml"));
			Parent intent = fxloader.load();

			TranPurchaseController cont = fxloader.getController();
			cont.setAccount(account);

			paneMain.getChildren().setAll(intent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void openTransactionRepayment() {
		try {
			Parent intent = FXMLLoader.load(getClass().getResource("/creditcard/view/TranRepayment.fxml"));
			paneMain.getChildren().setAll(intent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openTransactionRepayment(CreditCardAccount account) {
		try {
			FXMLLoader fxloader = new FXMLLoader(getClass().getResource("/creditcard/view/TranRepayment.fxml"));
			Parent intent = fxloader.load();

			TranRepaymentController cont = fxloader.getController();
			cont.setAccount(account);

			paneMain.getChildren().setAll(intent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void openTransactionWithDraw() {
		try {
			Parent intent = FXMLLoader.load(getClass().getResource("/creditcard/view/TranWithdraw.fxml"));
			paneMain.getChildren().setAll(intent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openTransactionWithDraw(CreditCardAccount account) {
		try {
			FXMLLoader fxloader = new FXMLLoader(getClass().getResource("/creditcard/view/TranWithdraw.fxml"));
			Parent intent = fxloader.load();

			TranWithdrawController cont = fxloader.getController();
			cont.setAccount(account);

			paneMain.getChildren().setAll(intent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@FXML
	private void openTransactionStatement() {
		try {
			Parent intent = FXMLLoader.load(getClass().getResource("/creditcard/view/TranStatement.fxml"));
			paneMain.getChildren().setAll(intent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void openTransactionStatement(CreditCardAccount account) {
		try {
			FXMLLoader fxloader = new FXMLLoader(getClass().getResource("/creditcard/view/TranStatement.fxml"));
			Parent intent = fxloader.load();

			TranStatementController cont = fxloader.getController();
			cont.setAccount(account);

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
