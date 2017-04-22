package financialcore.account;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import databaseLayer.AbstractElement;

public class Transaction extends AbstractElement {

	private int transactionId;
	private int accountNumber;
	private String balanceCode;
	private BigDecimal amount;
	private String type;
	private BigDecimal postBalance = new BigDecimal(0);
	private String transanctionCode;
	private String description;
	private LocalDate transactionDate = LocalDate.now();

	public String getTransactionCode() {
		return transanctionCode;
	}

	public Transaction() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		System.out.println(dtf.format(localDate)); // 2016/11/16
	}

	public void setTransactionCode(String tranCode) {
		this.transanctionCode = tranCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTransactionNumber() {
		return transactionId;
	}

	public void setTransactionNumber(int tranNo) {
		this.transactionId = tranNo;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNo) {
		this.accountNumber = accountNo;
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

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate tranDate) {
		this.transactionDate = tranDate;
	}

}
