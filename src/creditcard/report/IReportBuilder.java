package creditcard.report;

import financialcore.account.Transaction;

public abstract class IReportBuilder {
	private TransactionReport transactionReport = new TransactionReport();
		
	public abstract void buildTransaction(Transaction transaction);
	public abstract void buildCustomer();
	public abstract void buildAccount();
	public abstract void finish();
	
	public TransactionReport getTransactionReport(){
		return transactionReport;
	}
}
