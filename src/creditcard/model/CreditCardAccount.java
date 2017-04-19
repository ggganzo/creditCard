package creditcard.model;

import financialcore.account.Account;
import financialcore.account.Balance;
import financialcore.general.MyOwnException;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by ganzo on 4/13/17.
 */

public class CreditCardAccount extends Account {

	private String cardNumber;
	private String cardName;
	private int accountNo;

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
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public CreditCardAccount(String pCustNo, String pCardNumber, String pCardName, int pAccountNo, String pCcy,
			LocalDate pStartDate, LocalDate pEndDate, long pInterestRate) {

		super(pCustNo, pAccountNo, "CreditCard", pCcy, pStartDate, pEndDate, pInterestRate);

		cardNumber = pCardNumber;
		cardName = pCardName;
		accountNo = pAccountNo;

	}

	public void saveAccount() {
		super.saveAccount();

		if (super.getStatus().compareTo("NEW") == 0) {
			createBalances();
		} else if (super.getStatus().compareTo("OPENED") == 0) {

		}

		// TODO save to DB
	}

	public void openAccount() throws MyOwnException {
		super.saveAccount();

		if (super.getStatus().compareTo("NEW") == 0) {
			super.openAccount();
		} else
			throw new MyOwnException("Status must be New");

	}

	public void closeAccount() throws MyOwnException {
		super.saveAccount();

		if (super.getStatus().compareTo("CLOSED") != 0) {
			super.openAccount();
		} else
			throw new MyOwnException("Account already closed");

	}

	private void createBalances() {
		Balance balCash = new Balance(this.getAccountNo(), BalanceCode.CASH.toString(), new BigDecimal(0));
		Balance balPurchase = new Balance(this.getAccountNo(), BalanceCode.PURCHASE.toString(), new BigDecimal(0));
		Balance balLimit = new Balance(this.getAccountNo(), BalanceCode.LIMIT.toString(), new BigDecimal(0));
		Balance balLimitCash = new Balance(this.getAccountNo(), BalanceCode.LIMITCASH.toString(), new BigDecimal(0));

		super.addBalanceToHashMap(balCash);
		super.addBalanceToHashMap(balPurchase);
		super.addBalanceToHashMap(balLimit);
		super.addBalanceToHashMap(balLimitCash);
	}

	private void checkBalance() {
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

		if (super.getStatus().compareTo("OPENED") != 0)
			throw new MyOwnException("Account status must be Opened");

		checkBalance();

		Balance balAvailable = super.getBalanceHashMap().get(BalanceCode.AVAILABLE.toString());
		Balance balPurchase = super.getBalanceHashMap().get(BalanceCode.PURCHASE.toString());

		balAvailable.decrBalance(super.getAccountNo(), pAmount, "PURCHASE", TranDesc);
		balPurchase.incrBalance(super.getAccountNo(), pAmount, "PURCHASE", TranDesc);
	}
	// TODO send notification }

	public void cashWidthdraw(BigDecimal pAmount, String TranDesc) throws MyOwnException {
		if (super.getStatus().compareTo("OPENED") != 0)
			throw new MyOwnException("Account status must be Opened");
		checkBalance();

		Balance balAvailable = super.getBalanceHashMap().get(BalanceCode.AVAILABLE.toString());
		Balance balCash = super.getBalanceHashMap().get(BalanceCode.CASH.toString());

		balAvailable.decrBalance(super.getAccountNo(), pAmount, "WITHDRAW", TranDesc);
		balCash.incrBalance(super.getAccountNo(), pAmount, "WITHDRAW", TranDesc); // TODO
																					// send
																					// notification
	}

	public void repayment(BigDecimal pAmount, String TranDesc) {
		checkBalance();

		Balance balCash = super.getBalanceHashMap().get(BalanceCode.CASH.toString());
		Balance balPurchase = super.getBalanceHashMap().get(BalanceCode.PURCHASE.toString());

		Balance balAvailable = super.getBalanceHashMap().get(BalanceCode.AVAILABLE.toString());

		balAvailable.decrBalance(super.getAccountNo(), pAmount, "REPAYMENT", TranDesc);

		if (balCash.getBalance().compareTo(pAmount) > 0) {

			balCash.decrBalance(super.getAccountNo(), pAmount, "REPAYMENT", TranDesc);

		} else {

			balCash.decrBalance(super.getAccountNo(), balCash.getBalance(), "REPAYMENT", TranDesc);
			balPurchase.decrBalance(super.getAccountNo(), pAmount.subtract(balCash.getBalance()), "REPAYMENT",
					TranDesc);

		} // TODO send notification
	}

	public void createStatement(BigDecimal pAmount) {

		// TODO Create Statemtn // TODO Send notification
	}

}
