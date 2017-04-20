package financialcore.account;

import java.time.LocalDate;
import java.util.HashMap;

import databaseLayer.AbstractElement;
import financialcore.customer.Customer;
import financialcore.general.MyOwnException;
import financialcore.interfaces.AccountI;
import jdk.nashorn.internal.ir.annotations.Reference;

/**
 * Created by ganzo on 4/13/17.
 */

public class Account extends AbstractElement implements AccountI {

	private int accountNumber;
	private String currency;
	private LocalDate startDate;
	private LocalDate endDate;
	private Customer cust;
	private float interestRate;
	private String type;
	private String status = "NEW";// NEW, OPENED, CLOSED

	public Account() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Reference
	private HashMap<String, Balance> balanceHashMap = new HashMap<>();

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public HashMap<String, Balance> getBalanceHashMap() {
		return balanceHashMap;
	}

	@Override
	public void addBalanceToHashMap(Balance b) {
		balanceHashMap.put(b.getBalanceCode(), b);

		// TODO
		// dbAccess.INSTANCE.getDatastore().save(b);
	}

	@Override
	public void updateBalanceToHashMap(Balance b) {
		balanceHashMap.remove(b.getBalanceCode());
		// TODO remove Baaz
		balanceHashMap.put(b.getBalanceCode(), b);
		// TODO add Baaz
	}

	public Account(String pCustNo, int pAccountNo, String pType, String pCcy, LocalDate pStartDate,
			LocalDate pEndDate) {

		this.setAccountNumber(pAccountNo);
		this.setType(pType);
		this.setCurrency(pCcy);
		this.setStartDate(pStartDate);
		this.setEndDate(pEndDate);
		Customer cust = null;
		this.setCust(cust);
	}

	public Account(String pCustNo, int pAccountNo, String pType, String pCcy, LocalDate pStartDate, LocalDate pEndDate,
			float pInterestRate) {

		this(pCustNo, pAccountNo, pType, pCcy, pStartDate, pEndDate);

		this.setInterestRate(pInterestRate);
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	@Override
	public void createAccount() throws MyOwnException {

		// dbAccess.INSTANCE.getDatastore().save(this.account);
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAccount() throws MyOwnException {

		// dbAccess.INSTANCE.getDatastore().save(this.account);
		// TODO Auto-generated method stub

	}

	public void openAccount() throws MyOwnException {
		this.setStatus("OPENED");
		// TODO save to DB
	}

	public void closeAccount() throws MyOwnException {
		this.setStatus("CLOSED");
		// TODO save to DB
	}

}
