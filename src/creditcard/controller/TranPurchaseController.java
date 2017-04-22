package creditcard.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import creditcard.model.CreditCardAccount;
import creditcard.transaction.TranCommand;
import creditcard.transaction.TranPurchase;
import databaseLayer.contextLayer.ContextLayer;
import financialcore.account.Account;
import financialcore.account.Balance;
import financialcore.account.TransactionTemplate;
import financialcore.general.MyOwnException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TranPurchaseController implements Initializable {
	private CreditCardAccount account;

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
	private TableColumn colBalanceCode;

	@FXML
	private TableColumn colBalance;

	@FXML
	public void saveAction() throws MyOwnException {

		if (txtAccountNo.getText().length() == 0
				|| new BigDecimal(txtAmount.getText()).compareTo(new BigDecimal(0)) <= 0
				|| txtDesc.getText().length() == 0

		) {
			//errorMessage.setText("Field is Empty!");
			return;
		}

		TransactionTemplate _tran = new TransactionTemplate();
		_tran.accountNo = Integer.valueOf(txtAccountNo.getText());
		_tran.amount = new BigDecimal(txtAmount.getText());
		_tran.description = txtDesc.getText();

		command = new TranPurchase(_tran, account);
		command.execute();

	}

	@FXML
	private void checkAccount(){
		try{
			int accountNumber = Integer.parseInt(txtAccountNo.getText());
			
			account = ContextLayer.Model().Accounts().getElement(accountNumber);
			
			if(account == null){
				JOptionPane.showMessageDialog(null, "Incorrect account number. Please, try again");
			}
			
			getAllBalance();
		}
		catch(Exception ex){
			JOptionPane.showMessageDialog(null, "Account number should be number");
			ex.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		/*System.out.println("initialize");

		Platform.runLater(() -> {
			//getAllBalance();
		});*/
	}

	private void getAllBalance() {

		System.out.println("getAllBalance: controller: ");
		 
		btnMakeTransaction.setDisable(false);
		
		balanceList = ContextLayer.Model().Balances().getElementsByAccount(this.account.getAccountNumber());

		colBalanceCode.setCellValueFactory(new PropertyValueFactory<Balance, String>("balanceCode"));
		colBalance.setCellValueFactory(new PropertyValueFactory<Balance, BigDecimal>("balance"));

		balanceTable.setItems(FXCollections.observableArrayList(balanceList));
		
		for(Balance balance: balanceList){
			account.addBalanceToHashMap(balance);
		}
		
	}

	private void initDataAndControls() {
		txtAccountNo.setDisable(true);
		btnCheckAccount.setDisable(true);
		getAllBalance();
	}

	public void setAccount(CreditCardAccount pAccount) {
		System.out.println("setAccount");

		this.account = pAccount;

		txtAccountNo.setText(String.valueOf(account.getAccountNumber()));
		txtAmount.setText(String.valueOf(new BigDecimal(0)));

		initDataAndControls();

	}
}
