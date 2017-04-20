package creditcard.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

//import com.mongodb.connection.ChangeListener;

import creditcard.model.CreditCardAccount;
import creditcard.model.CreditFacade;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AccountListController implements Initializable {
	// static Helper helper = new Helper();
	List<CreditCardAccount> accountList;
	CreditFacade facade = new CreditFacade();

	@FXML
	private TableView<CreditCardAccount> accountTable;

	@FXML
	private TableColumn<CreditCardAccount, String> colAccountNo;

	@FXML
	private TableColumn<CreditCardAccount, String> colCardNo;

	@FXML
	private TableColumn<CreditCardAccount, String> colCardName;

	@FXML
	private TableColumn<CreditCardAccount, String> colTotalLimit;

	@FXML
	private Button btnNewAccount;

	@FXML
	private Button btnEditAccount;

	@FXML
	private TextField txtEditAccount;

	@FXML
	private Label editError;

	Stage stage;

	public void getAllMembers() {

		System.out.println("getAllMembers: controller: ");

		colAccountNo.setCellValueFactory(new PropertyValueFactory<CreditCardAccount, String>("accountNo"));
		colCardNo.setCellValueFactory(new PropertyValueFactory<CreditCardAccount, String>("cardNo"));
		colCardName.setCellValueFactory(new PropertyValueFactory<CreditCardAccount, String>("cardName"));

		// colTotalLimit.setCellValueFactory(new
		// PropertyValueFactory<CreditCardAccount, String>("totallimit"));

		accountTable.setItems(FXCollections.observableArrayList(accountList));
	}

	/*
	 * @FXML public void backAction() { String memberView =
	 * "../view/layout/StaffBoard.fxml"; String viewTitle = "Library System";
	 * LoginController.helper.loadNewStage( stage, lblTitle, memberView,
	 * viewTitle, false); }
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("initialize");
		getAccountList();
		// TODO Auto-generated method stub
		Platform.runLater(() -> {
			getAllMembers();
		});
		btnNewAccount.setOnAction((e) -> ActionAdd());
		btnEditAccount.setOnAction((e) -> editAccountHandler(txtEditAccount.getText()));

	}

	private void getAccountList() {
		System.out.println("getAccountList: controller: ");
		accountList = facade.getAccountList();
	}

	@FXML
	public void ActionAdd() {
		System.out.println("SDF");
		MainFormController.getMainController().changeView("createNewAccount");
	}

	private void editAccountHandler(String text) {

		// Employee employee = DataAccessFacade.findEmployeeById(employeeid);

		// if (employee == null) {
		// editError.setText("Invalid Employee Id number!");
		// return;
		// }

		CreditCardAccount account = null;// TODO;
		MainFormController.getMainController().changeViewAccount("editEmployee", account);
	}

	@FXML
	public void clickTable() {
		CreditCardAccount account = accountTable.getSelectionModel().getSelectedItem();
		txtEditAccount.setText(String.valueOf(account.getAccountNo()));

	}

}
