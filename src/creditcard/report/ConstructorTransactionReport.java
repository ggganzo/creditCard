package creditcard.report;

import financialcore.account.Transaction;

public class ConstructorTransactionReport {
	private TransactionReportBuilder transactionReportBuilder;
	private Transaction transaction;

	public ConstructorTransactionReport(TransactionReportBuilder transactionReportBuilder) {
		this.transactionReportBuilder = transactionReportBuilder;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public TransactionReport getTransactionReport() {
		return transactionReportBuilder.getTransactionReport();
	}

	public void process() {
		transactionReportBuilder.buildTransaction(transaction);
		transactionReportBuilder.buildAccount();
		transactionReportBuilder.buildCustomer();
		transactionReportBuilder.finish();
	}
}
