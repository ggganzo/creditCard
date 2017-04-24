package creditcard.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import creditcard.model.BalanceCode;
import creditcard.model.CreditCardAccount;
import creditcard.model.CreditFacade;
import financialcore.account.Balance;
import financialcore.general.MyOwnException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AccountCreateController implements Initializable {
	private CreditCardAccount account;
	private boolean booleanEdit = false;
	CreditFacade facade = new CreditFacade();

	List<Balance> balanceList;

	@FXML
	private Label lblTitle;

	@FXML
	private Label errorMessage;

	@FXML
	private TextField txtAccountNo;

	@FXML
	private TextField txtCardNo;

	@FXML
	private TextField txtCardName;

	@FXML
	private TextField txtTotalLimit;

	@FXML
	private TextField txtCashLimit;

	@FXML
	private DatePicker dpStart;

	@FXML
	private DatePicker dpEnd;

	@FXML
	private TextField txtCustNo;

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
			if (txtAccountNo.getText().length() == 0 || txtCardNo.getText().length() == 0
					|| txtCardName.getText().length() == 0

			) {
				errorMessage.setText("Field is Empty!");
				return;
			}

			String _accountNo = txtAccountNo.getText();
			String _cardNo = txtCardNo.getText();
			String _cardName = txtCardName.getText();
			BigDecimal _totalLimit = new BigDecimal(txtTotalLimit.getText());
			BigDecimal _cashLimit = new BigDecimal(txtCashLimit.getText());
			LocalDate _startDate = dpStart.getValue();
			LocalDate _endDate = dpEnd.getValue();
			int _custNo = Integer.parseInt(txtCustNo.getText());
			float _interestRate = 20;
			Alert alert = new Alert(AlertType.CONFIRMATION);
			// alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Are you sure to make a transaction");
			// alert.setContentText("Are you ok with this?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				System.out.println("booleanEdit: " + booleanEdit);
				if (booleanEdit) {
					facade.updateAccount(_accountNo, _startDate, _endDate, _totalLimit, _cashLimit);

				} else {

					facade.createAccount(_custNo, _cardNo, _cardName, _accountNo, "USD", _startDate, _endDate,
							_interestRate, _totalLimit, _cashLimit);
				}

				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setHeaderText("Succeeded");
				alert1.show();

				getAllBalance();
			}

		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println("initialize");

		Platform.runLater(() -> {
			try {
				getAllBalance();
			} catch (MyOwnException ex) {
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setHeaderText(ex.toString());
				alert1.show();

				ex.printStackTrace();
			}
		});
	}

	private void getAllBalance() throws MyOwnException {
		try {

			System.out.println("getAllBalance: controller: ");

			if (booleanEdit) {
				balanceList = new ArrayList<Balance>(account.getBalanceHashMap().values());

				colBalance.setCellValueFactory(new PropertyValueFactory<Balance, BigDecimal>("balance"));
				colBalanceCode.setCellValueFactory(new PropertyValueFactory<Balance, String>("balanceCode"));
				balanceTable.refresh();
				balanceTable.setItems(FXCollections.observableArrayList(balanceList));
				balanceTable.refresh();
			}
		} catch (Exception ex) {
			Alert alert1 = new Alert(AlertType.INFORMATION);
			alert1.setHeaderText(ex.toString());
			alert1.show();

			ex.printStackTrace();
		}
	}

	private void initDataAndControls() {

		if (booleanEdit) {
			txtAccountNo.setDisable(true);
			txtCardNo.setDisable(true);
			txtCardName.setDisable(true);
			txtCustNo.setDisable(true);
		}
	}

	public void setAccount(CreditCardAccount pAccount) {
		System.out.println("setAccount");

		booleanEdit = true;
		this.account = facade.getAccountDetail(String.valueOf(pAccount.getAccountNo()));

		txtAccountNo.setText(String.valueOf(account.getAccountNo()));
		txtCardNo.setText(account.getCardNumber().toString());
		txtCardName.setText(account.getCardName());
		txtTotalLimit
				.setText(String.valueOf(account.getBalanceHashMap().get(BalanceCode.LIMIT.toString()).getBalance()));
		txtCashLimit.setText(
				String.valueOf(account.getBalanceHashMap().get(BalanceCode.LIMITCASH.toString()).getBalance()));
		dpStart.setValue(account.getStartDate());
		dpEnd.setValue(account.getEndDate());
		txtCustNo.setText(String.valueOf(account.getCustomer().getPersonId()));

		initDataAndControls();

	}
}
