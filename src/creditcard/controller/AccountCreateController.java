package creditcard.controller;

import java.net.URL;
import java.util.ResourceBundle;

import creditcard.model.CreditCardAccount;
import financialcore.general.MyOwnException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AccountCreateController implements Initializable {
	private CreditCardAccount account;
	private boolean booleanEdit = false;

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

	Stage stage;

	@FXML
	public void saveAction() throws MyOwnException {

		if (txtAccountNo.getText().length() == 0 || txtCardNo.getText().length() == 0
				|| txtCardName.getText().length() == 0) {
			errorMessage.setText("Field is Empty!");
			return;
		}

		account.setCardName(txtCardName.getText());

		//account.saveAccount();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	public void setAccount(CreditCardAccount pAccount) {
		booleanEdit = true;
		this.account = pAccount;

		txtAccountNo.setText(String.valueOf(account.getAccountNo()));
		txtCardName.setText(account.getCardName());

		txtAccountNo.setDisable(true);
	}
}
