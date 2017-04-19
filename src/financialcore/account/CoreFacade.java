package financialcore.account;

import financialcore.customer.Customer;

import java.util.List;

/**
 * Created by ganzo on 4/14/17.
 */
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

	public List<Balance> getBalanceList(int pAccountNo) {
		return null;
	}

	public List<Transaction> getTranList(int pAccountNo) {
		return null;
	}

	public List<Transaction> getTranListByBal(int pAccountNo, String pBalanceCode) {
		return null;

	}
}
