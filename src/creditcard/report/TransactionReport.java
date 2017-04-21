package creditcard.report;

import financialcore.account.Account;
import financialcore.account.Transaction;
import financialcore.customer.Customer;

public class TransactionReport {
	private Customer customer;
	private Account account;
	private Transaction transaction;
	private String report;
	
	public TransactionReport() {}
	
	public Customer getCustomer(){
		return this.customer;
	}
	
	public void setCustomer(Customer customer){
		this.customer = customer;
	}
	
	public Account getAccount(){
		return this.account;
	}
	
	public void setAccount(Account account){
		this.account = account;
	}
	
	public Transaction getTransaction(){
		return this.transaction;
	}
	
	public void setTransaction(Transaction transaction){
		this.transaction = transaction;
	}
	
	public String getReport(){
		return this.report;
	}
	public void setReport(String report){
		this.report = report;
	}
}
