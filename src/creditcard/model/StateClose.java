package creditcard.model;

import java.math.BigDecimal;

import financialcore.general.MyOwnException;

public class StateClose implements State {

	CreditCardAccount account = null;

	public StateClose(CreditCardAccount pAccount) {
		account = pAccount;
	}

	@Override
	public void saveAccount() throws MyOwnException {
		// TODO Auto-generated method stub

	}

	@Override
	public void openAccount() throws MyOwnException {
		throw new MyOwnException("Status must be New");

	}

	@Override
	public void closeAccount() throws MyOwnException {
		throw new MyOwnException("Account already closed");

	}

	@Override
	public void purchase(BigDecimal pAmount, String TranDesc) throws MyOwnException {
		throw new MyOwnException("Account already closed");
	}

	@Override
	public void cashWidthdraw(BigDecimal pAmount, String TranDesc) throws MyOwnException {
		throw new MyOwnException("Account already closed");

	}

	@Override
	public void repayment(BigDecimal pAmount, String TranDesc) throws MyOwnException {
		throw new MyOwnException("Account already closed");

	}

}
