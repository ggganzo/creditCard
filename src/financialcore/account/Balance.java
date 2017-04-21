package financialcore.account;

import java.math.BigDecimal;
import java.time.LocalDate;

import databaseLayer.AbstractElement;
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

		this.setBalance(this.getBalance().add(pTranAmount));

		Transaction t = new Transaction();
		t.setAccountNumber(pAccountNo);
		t.setBalanceCode(this.getBalanceCode());
		t.setAmount(pTranAmount);
		t.setType("INC");
		t.setTransactionCode(pTranCode);
		t.setDescription(pTranDesc);
		t.setTransactionNumber(Sequence.getTranNo());

		// dbAccess.INSTANCE.getDatastore().save(t);

	}

	public void decrBalance(int pAccountNo, BigDecimal pTranAmount, String pTranCode, String pTranDesc) {
		this.setBalance(this.getBalance().subtract(pTranAmount));

		Transaction t = new Transaction();
		t.setAccountNumber(pAccountNo);
		t.setBalanceCode(this.getBalanceCode());
		t.setAmount(pTranAmount);
		t.setType("DEC");
		t.setTransactionCode(pTranCode);
		t.setDescription(pTranDesc);
		t.setTransactionNumber(Sequence.getTranNo());

		// dbAccess.INSTANCE.getDatastore().save(t);

	}
}
