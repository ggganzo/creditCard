package creditcard.model;

import java.math.BigDecimal;

import financialcore.general.MyOwnException;

public interface State {
	public void saveAccount() throws MyOwnException;

	public void openAccount() throws MyOwnException;

	public void closeAccount() throws MyOwnException;

	public void purchase(BigDecimal pAmount, String TranDesc) throws MyOwnException;

	public void cashWidthdraw(BigDecimal pAmount, String TranDesc) throws MyOwnException;

	public void repayment(BigDecimal pAmount, String TranDesc) throws MyOwnException;

}
