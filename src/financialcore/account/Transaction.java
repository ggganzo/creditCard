package financialcore.account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import databaseLayer.AbstractElement;

public class Transaction extends AbstractElement {

	private int tranNo;
	private int accountNo;
	private String balanceCode;
	private BigDecimal amount;
	private String type;
	private BigDecimal postBalance;
	private String tranCode;
	private String description;
	private LocalDate tranDate;

	public Transaction() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		System.out.println(dtf.format(localDate)); // 2016/11/16
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTransactionNumber() {
		return tranNo;
	}

	public void setTransactionNumber(int tranNo) {
		this.tranNo = tranNo;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getPostBalance() {
		return postBalance;
	}

	public void setPostBalance(BigDecimal postBalance) {
		this.postBalance = postBalance;
	}

	public LocalDate getTranDate() {
		return tranDate;
	}

	public void setTranDate(LocalDate tranDate) {
		this.tranDate = tranDate;
	}

}
