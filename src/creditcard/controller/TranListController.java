package creditcard.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

//import com.mongodb.connection.ChangeListener;

import creditcard.model.CreditCardAccount;
import creditcard.model.CreditFacade;
import financialcore.account.Transaction;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TranListController implements Initializable {
	// static Helper helper = new Helper();
	List<Transaction> accountList;
	CreditFacade facade = new CreditFacade();
	private CreditCardAccount cAccount;

	@FXML
	private TableView<Transaction> transactionTable;

	@FXML
	private TableColumn<Transaction, String> colAccountNo;

	@FXML
	private TableColumn<Transaction, String> colTranNo;

	@FXML
	private TableColumn<Transaction, String> colBalanceCode;

	@FXML
	private TableColumn<Transaction, String> colAmount;

	@FXML
	private TableColumn<Transaction, String> colDescription;

	Stage stage;

	public void getAllMembers() {

		try {
			getAccountList();
			System.out.println("getAllMembers: controller: ");

			colAccountNo.setCellValueFactory(new PropertyValueFactory<Transaction, String>("accountNumber"));
			colTranNo.setCellValueFactory(new PropertyValueFactory<Transaction, String>("transactionId"));
			colBalanceCode.setCellValueFactory(new PropertyValueFactory<Transaction, String>("balanceCode"));
			colAmount.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
			colDescription.setCellValueFactory(new PropertyValueFactory<Transaction, String>("description"));

			transactionTable.setItems(FXCollections.observableArrayList(accountList));
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("initialize: TranlistController: ");

		// TODO Auto-generated method stub
		Platform.runLater(() -> {
			System.out.println("runLater: TranlistController: ");
			getAllMembers();
		});

	}

	private void getAccountList() {
		try {
			System.out.println("getAccountList: TranlistController: ");
			accountList = facade.getTransactionList(String.valueOf(cAccount.getAccountNo()));
			System.out.println("accountList.size: TranlistController: " + accountList.size());
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	public void initData(CreditCardAccount pAccount) {
		try {
			System.out.println("initData: TranlistController: ");
			this.cAccount = facade.getAccountDetail(String.valueOf(pAccount.getAccountNo()));
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}

	}
}
