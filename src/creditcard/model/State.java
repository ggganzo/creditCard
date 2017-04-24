package creditcard.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import financialcore.general.MyOwnException;

public interface State {
	public void createAccount() throws MyOwnException;

	public void saveAccount() throws MyOwnException;

	public void openAccount() throws MyOwnException;

	public void closeAccount() throws MyOwnException;

	public void purchase(BigDecimal pAmount, String TranDesc) throws MyOwnException;

	public void cashWidthdraw(BigDecimal pAmount, String TranDesc) throws MyOwnException;

	public void repayment(BigDecimal pAmount, String TranDesc) throws MyOwnException;

	public void createStatement(BigDecimal pAmount, LocalDate pDueDate) throws MyOwnException;

}
