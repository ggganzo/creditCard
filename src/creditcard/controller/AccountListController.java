package creditcard.controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

//import com.mongodb.connection.ChangeListener;

import creditcard.model.CreditCardAccount;
import creditcard.model.CreditFacade;
import creditcard.service.MainWindowService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AccountListController implements Initializable {
	// static Helper helper = new Helper();
	List<CreditCardAccount> accountList;
	CreditFacade facade = new CreditFacade();
	CreditCardAccount account = null;

	@FXML
	private TableView<CreditCardAccount> accountTable;

	@FXML
	private TableColumn<CreditCardAccount, String> colAccountNo;

	@FXML
	private TableColumn<CreditCardAccount, String> colCardNo;

	@FXML
	private TableColumn<CreditCardAccount, String> colCardName;

	@FXML
	private TableColumn<CreditCardAccount, String> colStatus;

	@FXML
	private Button btnNewAccount;

	@FXML
	private Button btnEditAccount;

	@FXML
	private TextField txtEditAccount;

	@FXML
	private Label editError;

	@FXML
	private Button btnTranList;

	Stage stage;

	public void getAllMembers() {

		System.out.println("getAllMembers: controller: ");
		getAccountList();
		colAccountNo.setCellValueFactory(new PropertyValueFactory<CreditCardAccount, String>("accountNo"));
		colCardNo.setCellValueFactory(new PropertyValueFactory<CreditCardAccount, String>("cardNo"));
		colCardName.setCellValueFactory(new PropertyValueFactory<CreditCardAccount, String>("cardName"));
		colStatus.setCellValueFactory(new PropertyValueFactory<CreditCardAccount, String>("status"));

		accountTable.setItems(FXCollections.observableArrayList(accountList));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("initialize");
		// getAccountList();
		// TODO Auto-generated method stub
		Platform.runLater(() -> {
			getAllMembers();
		});
		// btnNewAccount.setOnAction((e) -> ActionAdd());
		// btnEditAccount.setOnAction((e) ->
		// clickAccountEdit(txtEditAccount.getText()));

	}

	private void getAccountList() {
		try {
			System.out.println("getAccountList: controller: ");
			accountList = facade.getAccountList();
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	@FXML
	public void clickAccountEdit() {
		try {
			account = facade.getAccountDetail(String.valueOf(account.getAccountNo()));

			// MainFormController.getMainController().changeViewAccount("editEmployee",
			// account);

			FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/creditcard/view/CreateAccount.fxml"));
			Parent root1 = (Parent) fxmlLoader1.load();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(root1));

			AccountCreateController controller = fxmlLoader1.<AccountCreateController>getController();
			controller.setAccount(account);

			stage1.show();

		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	@FXML
	public void clickTable() {
		try {
			System.out.println("clickTable");
			account = accountTable.getSelectionModel().getSelectedItem();
			txtEditAccount.setText(String.valueOf(account.getAccountNo()));
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	@FXML
	public void clickTranList() {

		try {
			MainWindowService.Current().getMainScreenController().openTransactionView();
			/*
			FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/creditcard/view/TranList.fxml"));
			Parent root1 = (Parent) fxmlLoader1.load();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(root1));

			TranListController controller = fxmlLoader1.<TranListController>getController();
			controller.initData(account);

			stage1.show();*/

		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	@FXML
	public void clickPurchase() {

		try {
			
			MainWindowService.Current().getMainScreenController().openTransactionPurchase(account);
			
			/*FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/creditcard/view/TranPurchase.fxml"));
			Parent root1 = (Parent) fxmlLoader1.load();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(root1));

			TranPurchaseController controller = fxmlLoader1.<TranPurchaseController>getController();
			controller.setAccount(account);

			stage1.show();
*/
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	@FXML
	public void clickWithdraw() {

		try {
			MainWindowService.Current().getMainScreenController().openTransactionWithDraw(account);
/*
			FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/creditcard/view/TranWithdraw.fxml"));
			Parent root1 = (Parent) fxmlLoader1.load();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(root1));

			TranWithdrawController controller = fxmlLoader1.<TranWithdrawController>getController();
			controller.setAccount(account);

			stage1.show();*/

		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	@FXML
	public void clickRepayment() {

		try {
			MainWindowService.Current().getMainScreenController().openTransactionRepayment(account);
			/*
			FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/creditcard/view/TranRepayment.fxml"));
			Parent root1 = (Parent) fxmlLoader1.load();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(root1));

			TranPurchaseController controller = fxmlLoader1.<TranPurchaseController>getController();
			controller.setAccount(account);

			stage1.show();*/
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	@FXML
	public void clickCreateStatement() {

		try {
			/*
			FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("/creditcard/view/TranStatement.fxml"));
			Parent root1 = (Parent) fxmlLoader1.load();
			Stage stage1 = new Stage();
			stage1.setScene(new Scene(root1));

			TranStatementController controller = fxmlLoader1.<TranStatementController>getController();
			controller.setAccount(account);

			stage1.show();*/
			MainWindowService.Current().getMainScreenController().openTransactionStatement(account);
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	@FXML
	public void clickOpen() {

		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			// alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure to open account");
			// alert.setContentText("Are you ok with this?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				facade.openAccount(String.valueOf(account.getAccountNo()));
			}
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	@FXML
	public void clickClose() {

		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			// alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure to close account");
			// alert.setContentText("Are you ok with this?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				facade.closeAccount(String.valueOf(account.getAccountNo()));
			}
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}
}
