package financialcore.account;

import java.math.BigDecimal;
import java.time.LocalDate;

import databaseLayer.AbstractElement;
import databaseLayer.ElementState;
import databaseLayer.contextLayer.ContextLayer;
import financialcore.config.Sequence;

/**
 * Created by ganzo on 4/13/17.
 */
public class Balance extends AbstractElement {

	private int accountNo;

	private String balanceCode;
	private BigDecimal balance;

	public Balance() {

	}

	public int getAccountNumber() {
		return accountNo;
	}

	public void setAccountNumber(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getBalanceCode() {
		return balanceCode;
	}

	public void setBalanceCode(String balanceCode) {
		this.balanceCode = balanceCode;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Balance(int pAccountNo, String pBalanceCode, BigDecimal pBalance) {
		this.setAccountNumber(pAccountNo);
		this.setBalanceCode(pBalanceCode);
		this.setBalance(pBalance);
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void incrBalance(int pAccountNo, BigDecimal pTranAmount, String pTranCode, String pTranDesc) {

		Transaction t = new Transaction();
		t.setAccountNumber(pAccountNo);
		t.setBalanceCode(this.getBalanceCode());
		t.setAmount(pTranAmount);
		t.setType("INC");
		t.setTransactionCode(pTranCode);
		t.setDescription(pTranDesc);
		t.setTransactionNumber(Sequence.getTranNo());
		t.setPostBalance(this.getBalance().add(pTranAmount));

		t.setElementState(ElementState.Inserted);
		ContextLayer.Model().Transactions().save(t);

		this.setBalance(this.getBalance().add(pTranAmount));

		saveBalance();

	}

	public void decrBalance(int pAccountNo, BigDecimal pTranAmount, String pTranCode, String pTranDesc) {

		Transaction t = new Transaction();
		t.setAccountNumber(pAccountNo);
		t.setBalanceCode(this.getBalanceCode());
		t.setAmount(pTranAmount);
		t.setType("DEC");
		t.setTransactionCode(pTranCode);
		t.setDescription(pTranDesc);
		t.setTransactionNumber(Sequence.getTranNo());
		t.setPostBalance(this.getBalance().subtract(pTranAmount));

		t.setElementState(ElementState.Inserted);
		ContextLayer.Model().Transactions().save(t);

		this.setBalance(this.getBalance().subtract(pTranAmount));
		saveBalance();

	}

	private void saveBalance() {

		this.setElementState(ElementState.Updated);
		ContextLayer.Model().Balances().save(this);

	}
}
