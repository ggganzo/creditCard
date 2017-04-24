package creditcard.model;

import financialcore.account.Account;
import financialcore.account.Balance;
import financialcore.general.MyOwnException;

import java.math.BigDecimal;
import java.time.LocalDate;

import databaseLayer.contextLayer.ContextLayer;

/**
 * Created by ganzo on 4/13/17.
 */

public class CreditCardAccount extends Account {

	private String cardNumber;
	private String cardName;
	private int accountNumber;

	private State stateNew;
	private State stateOpened;
	private State stateClosed;

	private State stateCurrent = stateNew;

	public State getStateCurrent() {
		return stateCurrent;
	}

	public void setStateCurrent(State stateCurrent) {
		this.stateCurrent = stateCurrent;
	}

	public State getStateNew() {
		return stateNew;
	}

	public void setStateNew(State stateNew) {
		this.stateNew = stateNew;
	}

	public State getStateOpened() {
		return stateOpened;
	}

	public void setStateOpened(State stateOpened) {
		this.stateOpened = stateOpened;
	}

	public State getStateClosed() {
		return stateClosed;
	}

	public void setStateClosed(State stateClosed) {
		this.stateClosed = stateClosed;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public int getAccountNo() {
		return accountNumber;
	}

	public void setAccountNo(int accountNo) {
		this.accountNumber = accountNo;
	}

	@Override
	public void setStatus(String status) {
		super.setStatus(status);
		System.out.println("setStatus: " + super.getStatus());
		if (super.getStatus().compareTo("NEW") == 0) {
			stateCurrent = stateNew;
		} else if (super.getStatus().compareTo("OPENED") == 0) {
			stateCurrent = stateOpened;
		} else if (super.getStatus().compareTo("CLOSED") == 0) {
			stateCurrent = stateClosed;
		}
	}

	public CreditCardAccount(int pCustNo, String pCardNumber, String pCardName, int pAccountNo, String pCcy,
			LocalDate pStartDate, LocalDate pEndDate, float pInterestRate) {

		super(pCustNo, pAccountNo, "CreditCard", pCcy, pStartDate, pEndDate, pInterestRate);

		cardNumber = pCardNumber;
		cardName = pCardName;
		accountNumber = pAccountNo;

		stateNew = new StateNew(this);
		stateOpened = new StateOpen(this);
		stateClosed = new StateClose(this);
		stateCurrent = stateNew;
	}

	public void createAccount() throws MyOwnException {
		// super.saveAccount();
		System.out.println("createAccount");
		System.out.println("createAccount: " + stateCurrent.getClass());

		stateCurrent.saveAccount();
	}

	public void saveAccount() throws MyOwnException {
		// super.saveAccount();
		System.out.println("saveAccount");
		System.out.println("stateCurrent: " + stateCurrent.getClass());

		stateCurrent.saveAccount();
	}

	public void openAccount() throws MyOwnException {
		// super.saveAccount();
		stateCurrent.openAccount();

		super.setStatus("OPENED");
		super.updateAccount();

		stateCurrent = stateOpened;

	}

	public void closeAccount() throws MyOwnException {

		stateCurrent.closeAccount();

		super.setStatus("CLOSED");
		super.updateAccount();

		stateCurrent = stateClosed;
	}

	public void createBalances() {
		Balance balCash = new Balance(this.getAccountNo(), BalanceCode.CASH.toString(), new BigDecimal(0));
		Balance balPurchase = new Balance(this.getAccountNo(), BalanceCode.PURCHASE.toString(), new BigDecimal(0));
		// Balance balLimit = new Balance(this.getAccountNo(),
		// BalanceCode.LIMIT.toString(), new BigDecimal(0));
		// Balance balLimitCash = new Balance(this.getAccountNo(),
		// BalanceCode.LIMITCASH.toString(), new BigDecimal(0));
		// Balance minPaymentAmount = new Balance(this.getAccountNo(),
		// BalanceCode.MINPAYAMOUNT.toString(),
		// new BigDecimal(0));

		super.addBalanceToHashMap(balCash);
		super.addBalanceToHashMap(balPurchase);
		// super.addBalanceToHashMap(balLimit);
		// super.addBalanceToHashMap(balLimitCash);
		// super.addBalanceToHashMap(minPaymentAmount);
	}

	public void checkBalance() throws MyOwnException {
		Balance balCash = super.getBalanceHashMap().get(BalanceCode.CASH.toString());
		Balance balPurchase = super.getBalanceHashMap().get(BalanceCode.PURCHASE.toString());
		Balance balLimit = super.getBalanceHashMap().get(BalanceCode.LIMIT.toString());
		Balance balLimitCash = super.getBalanceHashMap().get(BalanceCode.LIMITCASH.toString());

		try {
			if (balCash == null || balPurchase == null || balLimit == null || balLimitCash == null) {
				throw new MyOwnException("Not found balance");
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	public void purchase(BigDecimal pAmount, String TranDesc) throws MyOwnException {

		stateCurrent.purchase(pAmount, TranDesc);
	}

	public void cashWidthdraw(BigDecimal pAmount, String TranDesc) throws MyOwnException {

		stateCurrent.cashWidthdraw(pAmount, TranDesc);
	}

	public void repayment(BigDecimal pAmount, String TranDesc) throws MyOwnException {

		stateCurrent.repayment(pAmount, TranDesc);
	}

	public void createStatement(BigDecimal pAmount, LocalDate pDueDate) throws MyOwnException {

		stateCurrent.createStatement(pAmount, pDueDate);
	}

}
