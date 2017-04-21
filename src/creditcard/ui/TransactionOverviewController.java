package creditcard.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import creditcard.report.ConstructorTransactionReport;
import creditcard.report.TransactionReportBuilder;
import databaseLayer.contextLayer.ContextLayer;
import financialcore.account.Transaction;
import financialcore.customer.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TransactionOverviewController {

	@FXML
	private void initialize() {
		// tbTransactions.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		//txtReport.
	}

	@FXML
	private TextField txtAccountNumber;
	@FXML
	private TextArea txtReport;

	@FXML
	private TableView tbTransactions;
	@FXML
	private TableColumn clmId;
	@FXML
	private TableColumn clmNumber;
	@FXML
	private TableColumn clmAccountNumber;
	@FXML
	private TableColumn clmBalanceCode;
	@FXML
	private TableColumn clmAmount;
	@FXML
	private TableColumn clmType;
	@FXML
	private TableColumn clmPostBalance;
	@FXML
	private TableColumn clmCode;
	@FXML
	private TableColumn clmDescription;
	@FXML
	private TableColumn clmDate;

	@FXML
	private void searchTransaction() {
		List<Transaction> transactions = ContextLayer.Model().Transactions().getElements();

		clmNumber.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("transactionNumber"));
		clmAccountNumber.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("accountNumber"));
		clmBalanceCode.setCellValueFactory(new PropertyValueFactory<Transaction, String>("balanceCode"));
		clmAmount.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("amount"));
		clmType.setCellValueFactory(new PropertyValueFactory<Transaction, String>("type"));
		clmPostBalance.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("postBalance"));
		clmCode.setCellValueFactory(new PropertyValueFactory<Transaction, String>("TransactionCode"));
		clmDescription.setCellValueFactory(new PropertyValueFactory<Transaction, String>("description"));
		clmDate.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("transactionDate"));

		ObservableList<Transaction> data = FXCollections.observableArrayList();
		for (Transaction transaction : transactions) {
			data.add(transaction);
		}

		tbTransactions.setItems(data);
	}

	private Transaction getSelectedItem() {
		try {
			return (Transaction) tbTransactions.getSelectionModel().getSelectedItem();
		} catch (Exception e) {
			return null;
		}
	}

	@FXML
	private void printReport() {
		Transaction transaction = getSelectedItem();
		if (transaction == null) {
			JOptionPane.showMessageDialog(null, "You should select one of transaction for renerate report");
			return;
		}
		
		ConstructorTransactionReport cons = new ConstructorTransactionReport(new TransactionReportBuilder());
		cons.setTransaction(transaction);
		
		cons.process();
		
		txtReport.setText(cons.getTransactionReport().getReport());
		
	}
}
