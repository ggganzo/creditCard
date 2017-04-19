package financialcore.facade;

import java.util.List;

import financialcore.account.Account;
import financialcore.account.Balance;
import financialcore.account.Transaction;
import financialcore.customer.Customer;

public class CoreFacade {

	public Account detailAccount(String pAccountNo) {
		return null;
	}

	public Customer detailCustomer(String pCustNo) {
		return null;
	}

	public List<Account> getAccountList() {
		return null;
	}

	public List<Customer> getCustomerList() {
		return null;
	}

	public List<Balance> getBalanceList(int pAccountNo) {
		return null;
	}

	public List<Transaction> getTranList(int pAccountNo) {
		return null;
	}

	public List<Transaction> getTranListByBalanceCode(int pAccountNo, String pBalanceCode) {
		return null;
	}
}
