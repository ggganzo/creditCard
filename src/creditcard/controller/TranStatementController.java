package creditcard.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import creditcard.model.CreditCardAccount;
import creditcard.transaction.TranCommand;
import creditcard.transaction.TranGenerateStatement;
import creditcard.transaction.TranWithdraw;
import financialcore.account.Balance;
import financialcore.account.TransactionTemplate;
import financialcore.general.MyOwnException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TranStatementController implements Initializable {
	private CreditCardAccount account;

	TranCommand command = null;

	List<Balance> balanceList;

	@FXML
	private Label errorMessage;

	@FXML
	private TextField txtAccountNo;

	@FXML
	private TextField txtMinPayment;

	@FXML
	private DatePicker dpDueDate;

	Stage stage;

	@FXML
	private TableView<Balance> balanceTable;

	@FXML
	private TableColumn<Balance, String> colBalanceCode;

	@FXML
	private TableColumn<Balance, BigDecimal> colBalance;

	@FXML
	public void saveAction() throws MyOwnException {

		if (txtAccountNo.getText().length() == 0
				|| new BigDecimal(txtMinPayment.getText()).compareTo(new BigDecimal(0)) <= 0
				|| dpDueDate.getValue() == null

		) {
			errorMessage.setText("Field is Empty!");
			return;
		}

		Alert alert = new Alert(AlertType.CONFIRMATION);
		// alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Are you sure to create statement");
		// alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			TransactionTemplate _tran = new TransactionTemplate();
			_tran.accountNo = Integer.valueOf(txtAccountNo.getText());
			_tran.amount = new BigDecimal(txtMinPayment.getText());
			_tran.dueDate = dpDueDate.getValue();

			command = new TranGenerateStatement(_tran);
			command.execute();
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		System.out.println("initialize");

		Platform.runLater(() -> {
			getAllBalance();
		});

		txtMinPayment.setText(String.valueOf(new BigDecimal(0)));
		LocalDate localDate = LocalDate.now();
		dpDueDate.setValue(localDate);
	}

	private void getAllBalance() {

		System.out.println("getAllBalance: controller: ");
		balanceList = new ArrayList<Balance>(account.getBalanceHashMap().values());

		colBalanceCode.setCellValueFactory(new PropertyValueFactory<Balance, String>("balanceCode"));
		colBalance.setCellValueFactory(new PropertyValueFactory<Balance, BigDecimal>("balance"));

		balanceTable.setItems(FXCollections.observableArrayList(balanceList));

	}

	private void initDataAndControls() {

		txtAccountNo.setDisable(true);

	}

	public void setAccount(CreditCardAccount pAccount) {
		System.out.println("setAccount");

		this.account = pAccount;

		txtAccountNo.setText(String.valueOf(account.getAccountNo()));

		initDataAndControls();

	}
}
