package creditcard.model;

import java.math.BigDecimal;

import financialcore.general.MyOwnException;

public class StateNew implements State {
	CreditCardAccount account = null;

	public StateNew(CreditCardAccount pAccount) {
		account = pAccount;
	}

	@Override
	public void saveAccount() throws MyOwnException {
		account.createAccount();

		account.createBalances();
	}

	@Override
	public void openAccount() throws MyOwnException {
		System.out.println("Succeed");

	}

	@Override
	public void closeAccount() throws MyOwnException {
		System.out.println("Succeed");

	}

	@Override
	public void purchase(BigDecimal pAmount, String TranDesc) throws MyOwnException {
		throw new MyOwnException("Account must be opened");

	}

	@Override
	public void cashWidthdraw(BigDecimal pAmount, String TranDesc) throws MyOwnException {
		throw new MyOwnException("Account must be opened");

	}

	@Override
	public void repayment(BigDecimal pAmount, String TranDesc) throws MyOwnException {
		throw new MyOwnException("Account must be opened");

	}

}
