package creditcard.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

//import com.mongodb.connection.ChangeListener;

import creditcard.model.CreditCardAccount;
import creditcard.model.CreditFacade;
import financialcore.account.Transaction;
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

		System.out.println("getAllMembers: controller: ");

		colAccountNo.setCellValueFactory(new PropertyValueFactory<Transaction, String>("accountNo"));
		colTranNo.setCellValueFactory(new PropertyValueFactory<Transaction, String>("tranNo"));
		colBalanceCode.setCellValueFactory(new PropertyValueFactory<Transaction, String>("balanceCode"));
		colAmount.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
		colDescription.setCellValueFactory(new PropertyValueFactory<Transaction, String>("description"));

		transactionTable.setItems(FXCollections.observableArrayList(accountList));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("initialize: TranlistController: ");
		getAccountList();
		// TODO Auto-generated method stub
		Platform.runLater(() -> {
			System.out.println("runLater: TranlistController: ");
			getAllMembers();
		});

	}

	private void getAccountList() {
		System.out.println("getAccountList: TranlistController: ");
		accountList = facade.getTransactionList(cAccount.getAccountNo());
	}

	public void initData(CreditCardAccount cAccount) {
		System.out.println("initData: TranlistController: ");
		this.cAccount = cAccount;
	}
}
