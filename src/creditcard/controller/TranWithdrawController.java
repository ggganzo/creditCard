package creditcard.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import creditcard.model.CreditCardAccount;
import creditcard.model.CreditFacade;
import creditcard.service.MainWindowService;
import creditcard.transaction.TranCommand;
import creditcard.transaction.TranWithdraw;
import financialcore.account.Balance;
import financialcore.account.TransactionTemplate;
import financialcore.general.MyOwnException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class TranWithdrawController implements Initializable {
	private CreditCardAccount account;
	CreditFacade facade = new CreditFacade();

	TranCommand command = null;

	List<Balance> balanceList;

	@FXML
	private Label errorMessage;

	@FXML
	private TextField txtAccountNo;

	@FXML
	private TextField txtAmount;

	@FXML
	private TextField txtDesc;

	@FXML
	private Button btnCheckAccount;
	@FXML
	private Button btnMakeTransaction;

	Stage stage;

	@FXML
	private TableView<Balance> balanceTable;

	@FXML
	private TableColumn<Balance, String> colBalanceCode;

	@FXML
	private TableColumn<Balance, BigDecimal> colBalance;

	@FXML
	public void saveAction() throws MyOwnException {
		try {
			if (txtAccountNo.getText().length() == 0
					|| new BigDecimal(txtAmount.getText()).compareTo(new BigDecimal(0)) <= 0
					|| txtDesc.getText().length() == 0

			) {
				errorMessage.setText("Field is Empty!");
				return;
			}
			Alert alert = new Alert(AlertType.CONFIRMATION);
			// alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure to make a transaction");
			// alert.setContentText("Are you ok with this?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				TransactionTemplate _tran = new TransactionTemplate();
				_tran.accountNo = Integer.valueOf(txtAccountNo.getText());
				_tran.amount = new BigDecimal(txtAmount.getText());
				_tran.description = txtDesc.getText();

				command = new TranWithdraw(_tran, account);
				if (command.execute()) {
					Alert alert1 = new Alert(AlertType.INFORMATION);
					alert1.setHeaderText("Successfully");
					alert1.show();
					MainWindowService.Current().getMainScreenController().openTransactionView();
				}
			}
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	@FXML
	private void checkAccount() {
		try {
			System.out.println("checkAccount: controller: ");
			account = facade.getAccountDetail(txtAccountNo.getText());

			if (account == null) {
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setHeaderText("Incorrect account number. Please, try again");
				alert1.show();

			}

			getAllBalance();
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("initialize");
		/*
		 * Platform.runLater(() -> { getAllBalance(); });
		 */
	}

	private void getAllBalance() {
		try {
			System.out.println("getAllBalance: controller: ");
			btnMakeTransaction.setDisable(false);
			balanceList = new ArrayList<Balance>(account.getBalanceHashMap().values());

			colBalanceCode.setCellValueFactory(new PropertyValueFactory<Balance, String>("balanceCode"));
			colBalance.setCellValueFactory(new PropertyValueFactory<Balance, BigDecimal>("balance"));

			balanceTable.setItems(FXCollections.observableArrayList(balanceList));
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	public void setAccount(CreditCardAccount pAccount) {
		System.out.println("setAccount");
		try {
			this.account = pAccount;

			txtAccountNo.setText(String.valueOf(account.getAccountNo()));
			txtAmount.setText(String.valueOf(new BigDecimal(0)));

			txtAccountNo.setDisable(true);
			btnCheckAccount.setDisable(true);

			getAllBalance();
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}
}
