package financialcore.account;

import java.time.LocalDate;
import java.util.HashMap;

//import org.bson.types.ObjectId;
//import org.mongodb.morphia.annotations.Entity;
//import org.mongodb.morphia.annotations.Id;

import financialcore.customer.Customer;
import financialcore.db.dbAccess;
import financialcore.general.MyOwnException;
import financialcore.interfaces.AccountI;
import jdk.nashorn.internal.ir.annotations.Reference;

/**
 * Created by ganzo on 4/13/17.
 */
// @Entity(value = "account")
public class Account implements AccountI {

	// @Id
	// private ObjectId id;
	private int accountNumber;
	private String currency;
	private LocalDate startDate;
	private LocalDate endDate;
	// @Reference
	private Customer cust;
	private float interestRate;
	private String type;
	private String status = "NEW";// NEW, OPENED, CLOSED

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

	public HashMap<String, Balance> getBalanceHashMap() {
		return balanceHashMap;
	}

	public void addBalanceToHashMap(Balance b) {
		balanceHashMap.put(b.getBalanceCode(), b);

		// TODO
		// dbAccess.INSTANCE.getDatastore().save(b);
	}

	public Account() {

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

	public void setAccountNumber(int accountNo) {
		this.accountNumber = accountNo;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String ccy) {
		this.currency = ccy;
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

	public void setBalanceHashMap(HashMap<String, Balance> balanceHashMap) {
		this.balanceHashMap = balanceHashMap;
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
