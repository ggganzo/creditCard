package creditcard.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import financialcore.account.Balance;
import financialcore.account.Transaction;
import financialcore.customer.Customer;
import financialcore.customer.Person;
import financialcore.customer.Staff;
import financialcore.general.MyOwnException;

public class CreditFacade {

	public List<CreditCardAccount> getAccountList() {
		List<CreditCardAccount> _account = null;

		return _account;
	}

	public CreditCardAccount getAccountDetail(String pAccountNo) {
		CreditCardAccount _account = getCCAccountDetail(pAccountNo);

		return _account;
	}

	public List<Customer> getCustomerList() {
		List<Customer> cust = null;

		return cust;
	}

	public Customer getCustomerDetail(String pCustNo) {
		Customer cust = null;

		return cust;
	}

	public List<Staff> getStaffList() {
		List<Staff> staff = null;

		return staff;
	}

	public Staff getStaffDetail(String pStaffNo) {
		Staff staff = null;

		return staff;
	}

	public List<Transaction> getTransactionList(String pAccountNo) {
		List<Transaction> tran = null;

		return tran;
	}

	public void createStaff(String pCustNo, String pFirstName, String pLastName, int pAge, String pUserName,
			String pPassword) {
		Customer cust = new Customer(pCustNo, pFirstName, pLastName, pAge, pUserName, pPassword);
		cust.createPerson();
	}

	public void updateStaff(String pCustNo, String pFirstName, String pLastName, int pAge, String pUserName,
			String pPassword) {
		Person cust = null; // TODO
		cust.setAge(pAge);
		cust.setFirstName(pFirstName);
		cust.setLastName(pLastName);
		cust.setPassword(pPassword);
		cust.setUserName(pUserName);
		cust.updatePerson();

	}

	public void createAccount(String pCustNo, String pCardNumber, String pCardName, int pAccountNo, String pCcy,
			LocalDate pStartDate, LocalDate pEndDate, float pInterestRate, BigDecimal pTotalLimit,
			BigDecimal pCashLImit) throws MyOwnException {

		CreditCardAccount ccAccount = new CreditCardAccount(pCustNo, pCardNumber, pCardName, pAccountNo, pCcy,
				pStartDate, pEndDate, pInterestRate);

		ccAccount.saveAccount();

		Balance balLimit = new Balance(ccAccount.getAccountNo(), BalanceCode.LIMIT.toString(), pTotalLimit);
		Balance balLimitCash = new Balance(ccAccount.getAccountNo(), BalanceCode.LIMITCASH.toString(), pCashLImit);
		Balance minPaymentAmount = new Balance(ccAccount.getAccountNo(), BalanceCode.MINPAYAMOUNT.toString(),
				pTotalLimit.multiply(new BigDecimal(0.2)));

		ccAccount.addBalanceToHashMap(balLimit);
		ccAccount.addBalanceToHashMap(balLimitCash);
		ccAccount.addBalanceToHashMap(minPaymentAmount);

		// TODO send notification
	}

	public void openAccount(String pAccountNo) throws MyOwnException {
		CreditCardAccount pCcAccount = getCCAccountDetail(pAccountNo);
		pCcAccount.openAccount();
	}

	public void closeAccount(String pAccountNo) throws MyOwnException {
		CreditCardAccount pCcAccount = getCCAccountDetail(pAccountNo);
		pCcAccount.closeAccount();
	}

	public void purchase(String pAccountNo, BigDecimal pAmount, String TranDesc) throws MyOwnException {

		CreditCardAccount pCcAccount = getCCAccountDetail(pAccountNo);
		pCcAccount.purchase(pAmount, TranDesc);

		// TODO send notification
	}

	public void cashWidthdraw(String pAccountNo, BigDecimal pAmount, String TranDesc) throws MyOwnException {

		CreditCardAccount pCcAccount = getCCAccountDetail(pAccountNo);
		pCcAccount.cashWidthdraw(pAmount, TranDesc);
		// TODO send notification
	}

	public void repayment(String pAccountNo, BigDecimal pAmount, String TranDesc) throws MyOwnException {

		CreditCardAccount pCcAccount = getCCAccountDetail(pAccountNo);
		pCcAccount.repayment(pAmount, TranDesc);

		// TODO send notification
	}

	public void generateStatement(String pAccountNo, BigDecimal pAmount) {
		CreditCardAccount pCcAccount = getCCAccountDetail(pAccountNo);
		// TODO Create Statemtn
		// TODO Send notification
	}

	private CreditCardAccount getCCAccountDetail(String pAccountNo) {
		// TODO
		CreditCardAccount account = null;
		return account;
	}
}
