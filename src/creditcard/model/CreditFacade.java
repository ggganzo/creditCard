package creditcard.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import databaseLayer.contextLayer.ContextLayer;
import financialcore.account.Balance;
import financialcore.account.Transaction;
import financialcore.customer.Customer;
import financialcore.general.MyOwnException;

public class CreditFacade {

	public List<CreditCardAccount> getAccountList() {
		List<CreditCardAccount> _account = ContextLayer.Model().Accounts().getElements();

		return _account;
	}

	public CreditCardAccount getAccountDetail(String pAccountNo) {
		CreditCardAccount _account = getCCAccountDetail(pAccountNo);

		return _account;
	}

	public List<Customer> getCustomerList() {
		List<Customer> cust = ContextLayer.Model().Persons().getCustomers();

		return cust;
	}

	public List<Transaction> getTransactionList(String pAccountNo) {
		List<Transaction> tran = ContextLayer.Model().Transactions().getElementsByAccount(Integer.parseInt(pAccountNo));

		return tran;
	}

	public void createAccount(int pCustNo, String pCardNumber, String pCardName, String pAccountNo, String pCcy,
			LocalDate pStartDate, LocalDate pEndDate, float pInterestRate, BigDecimal pTotalLimit,
			BigDecimal pCashLImit) throws MyOwnException {

		CreditCardAccount ccAccount = new CreditCardAccount(pCustNo, pCardNumber, pCardName,
				Integer.parseInt(pAccountNo), pCcy, pStartDate, pEndDate, pInterestRate);

		ccAccount.createAccount();

		Balance balLimit = new Balance(ccAccount.getAccountNo(), BalanceCode.LIMIT.toString(), pTotalLimit);
		Balance balLimitCash = new Balance(ccAccount.getAccountNo(), BalanceCode.LIMITCASH.toString(), pCashLImit);
		Balance minPaymentAmount = new Balance(ccAccount.getAccountNo(), BalanceCode.MINPAYAMOUNT.toString(),
				new BigDecimal(0));

		ccAccount.addBalanceToHashMap(balLimit);
		ccAccount.addBalanceToHashMap(balLimitCash);
		ccAccount.addBalanceToHashMap(minPaymentAmount);

		// TODO send notification
	}

	public void updateAccount(String pAccountNo, LocalDate pStartDate, LocalDate pEndDate, BigDecimal pTotalLimit,
			BigDecimal pCashLImit) throws MyOwnException {
		System.out.println("updateAccount");
		CreditCardAccount ccAccount = getCCAccountDetail(pAccountNo);
		ccAccount.setStartDate(pStartDate);
		ccAccount.setEndDate(pEndDate);

		Balance balLimit = ccAccount.getBalanceHashMap().get(BalanceCode.LIMIT.toString());
		Balance balLimitCash = ccAccount.getBalanceHashMap().get(BalanceCode.LIMITCASH.toString());

		if (balLimit.getBalance().compareTo(pTotalLimit) != 0) {
			System.out.println("LIMIT DIFFERENT");
			balLimit.setBalance(pTotalLimit);
			ccAccount.updateBalanceToHashMap(balLimit);
		}
		if (balLimitCash.getBalance().compareTo(pCashLImit) != 0) {
			System.out.println("LIMITCASH DIFFEREN");
			balLimitCash.setBalance(pCashLImit);
			ccAccount.updateBalanceToHashMap(balLimitCash);
		}

		ccAccount.saveAccount();
	}

	public void openAccount(String pAccountNo) throws MyOwnException {
		CreditCardAccount pCcAccount = getCCAccountDetail(pAccountNo);
		pCcAccount.openAccount();
	}

	public void closeAccount(String pAccountNo) throws MyOwnException {
		CreditCardAccount pCcAccount = getCCAccountDetail(pAccountNo);
		pCcAccount.closeAccount();
	}

	private void sendNotification() {

	}

	private CreditCardAccount getCCAccountDetail(String pAccountNo) {

		CreditCardAccount account = ContextLayer.Model().Accounts().getElement(Integer.parseInt(pAccountNo));
		return account;
	}
}
