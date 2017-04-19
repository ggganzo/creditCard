package financialcore.account;

import java.math.BigDecimal;
/*
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
*/
import financialcore.config.Sequence;
//import financialcore.db.dbAccess;

/**
 * Created by ganzo on 4/13/17.
 */
//@Entity(value = "balance")
public class Balance {

	//@Id
	//private ObjectId id;

	private int accountNo;

	private String balanceCode;
	private BigDecimal balance;
	
	public Balance(){
		
	}
/*
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
*/
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
		t.setTranCode(pTranCode);
		t.setDescription(pTranDesc);
		t.setTransactionNumber(Sequence.getTranNo());

		//dbAccess.INSTANCE.getDatastore().save(t);

	}

	public void decrBalance(int pAccountNo, BigDecimal pTranAmount, String pTranCode, String pTranDesc) {
		this.setBalance(this.getBalance().subtract(pTranAmount));

		Transaction t = new Transaction();
		t.setAccountNumber(pAccountNo);
		t.setBalanceCode(this.getBalanceCode());
		t.setAmount(pTranAmount);
		t.setType("DEC");
		t.setTranCode(pTranCode);
		t.setDescription(pTranDesc);
		t.setTransactionNumber(Sequence.getTranNo());

		//dbAccess.INSTANCE.getDatastore().save(t);

	}
}
