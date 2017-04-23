package financialcore.account;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import databaseLayer.AbstractElement;
import databaseLayer.ElementState;
import databaseLayer.contextLayer.ContextLayer;
import financialcore.customer.Customer;
import financialcore.general.MyOwnException;
import financialcore.interfaces.AccountI;

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

	private Map<String, Balance> balanceHashMap = new HashMap<String, Balance>();

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
	public Map<String, Balance> getBalanceHashMap() {
		return balanceHashMap;
	}

	@Override
	public void addBalanceToHashMap(Balance b) {
		balanceHashMap.put(b.getBalanceCode(), b);

		b.setElementState(ElementState.Updated);
		ContextLayer.Model().Balances().save(b);
	}

	@Override
	public void updateBalanceToHashMap(Balance b) {
		balanceHashMap.remove(b.getBalanceCode());

		balanceHashMap.put(b.getBalanceCode(), b);

		b.setElementState(ElementState.Updated);
		ContextLayer.Model().Balances().save(b);
	}

	public Account(int pCustNo, int pAccountNo, String pType, String pCcy, LocalDate pStartDate, LocalDate pEndDate) {

		this.setAccountNumber(pAccountNo);
		this.setType(pType);
		this.setCurrency(pCcy);
		this.setStartDate(pStartDate);
		this.setEndDate(pEndDate);
		Customer cust = (Customer) ContextLayer.Model().Persons().getElement(pCustNo);
		this.setCustomer(cust);

		List<Balance> balanceList = ContextLayer.Model().Balances().getElementsByAccount(getAccountNumber());

		if (balanceList != null) {

			balanceHashMap = balanceList.stream().collect(Collectors.toMap(Balance::getBalanceCode, item -> item));
		}
	}

	public Account(int pCustNo, int pAccountNo, String pType, String pCcy, LocalDate pStartDate, LocalDate pEndDate,
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

	public Customer getCustomer() {
		return cust;
	}

	public void setCustomer(Customer cust) {
		this.cust = cust;
	}

	@Override
	public void createAccount() throws MyOwnException {

	}

	@Override
	public void updateAccount() throws MyOwnException {

	}

	public void openAccount() throws MyOwnException {
		this.setStatus("OPENED");
	}

	public void closeAccount() throws MyOwnException {
		this.setStatus("CLOSED");
	}

}
