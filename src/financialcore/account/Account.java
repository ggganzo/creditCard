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
	private int accountNo;
	private String ccy;
	private LocalDate startDate;
	private LocalDate endDate;
	// @Reference
	private Customer cust;
	private long interestRate;
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

	public long getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(long interestRate) {
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
		//dbAccess.INSTANCE.getDatastore().save(b);
	}

	public Account(String pCustNo, int pAccountNo, String pType, String pCcy, LocalDate pStartDate,
			LocalDate pEndDate) {

		this.setAccountNo(pAccountNo);
		this.setType(pType);
		this.setCcy(pCcy);
		this.setStartDate(pStartDate);
		this.setEndDate(pEndDate);
		Customer cust = null;
		this.setCust(cust);
	}

	public Account(String pCustNo, int pAccountNo, String pType, String pCcy, LocalDate pStartDate, LocalDate pEndDate,
			long pInterestRate) {

		this(pCustNo, pAccountNo, pType, pCcy, pStartDate, pEndDate);

		this.setInterestRate(pInterestRate);
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getCcy() {
		return ccy;
	}

	public void setCcy(String ccy) {
		this.ccy = ccy;
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
