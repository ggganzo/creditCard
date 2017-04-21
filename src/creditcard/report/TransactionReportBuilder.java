package creditcard.report;

import databaseLayer.contextLayer.ContextLayer;
import financialcore.account.Account;
import financialcore.account.Transaction;
import financialcore.customer.Customer;

public class TransactionReportBuilder extends IReportBuilder {

	@Override
	public void buildCustomer() {
		long customerId = getTransactionReport().getAccount().getCustomer().getPersonId();
		Customer customer = (Customer) ContextLayer.Model().Persons().getElement((int) customerId);

		if (customer != null) {
			getTransactionReport().setCustomer(customer);
		}

	}

	@Override
	public void buildTransaction(Transaction transaction) {
		getTransactionReport().setTransaction(transaction);

	}

	@Override
	public void buildAccount() {
		long accountNumber = getTransactionReport().getTransaction().getAccountNumber();
		Account account = ContextLayer.Model().Accounts().getElement((int) accountNumber);

		if (account != null) {
			getTransactionReport().setAccount(account);
		}
	}

	@Override
	public void finish() {
		Transaction transaction = getTransactionReport().getTransaction();
		Account account = getTransactionReport().getAccount();
		Customer customer = getTransactionReport().getCustomer();

		if (transaction != null && account != null && customer != null) {

			String report = "Transaction number: " + transaction.getTransactionNumber();
			report += "\nAccount number: " + transaction.getAccountNumber() + " | Type: " + account.getType();
			report += "\nTransaction code: " + transaction.getTransactionCode();
			report += "\nTransaction date: " + transaction.getTransactionDate();

			report += "\nDescription: " + transaction.getDescription();
			report += "\n================================================================";
			report+="\n " + customer.getLastName() + " " + customer.getFirstName();
			report += "\n================================================================";

			report += "\n\nBalance code: " + transaction.getBalanceCode();
			report += " | Amount: " + transaction.getAmount();
			report += " | Type: " + transaction.getType();
			report += " | Post balance: " + transaction.getPostBalance();
			getTransactionReport().setReport(report);
		}
	}

}
