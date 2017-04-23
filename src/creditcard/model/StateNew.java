package creditcard.model;

import java.math.BigDecimal;

import databaseLayer.ElementState;
import databaseLayer.contextLayer.ContextLayer;
import financialcore.general.MyOwnException;

public class StateNew implements State {
	CreditCardAccount account = null;

	public StateNew(CreditCardAccount pAccount) {
		account = pAccount;
	}

	@Override
	public void saveAccount() throws MyOwnException {
		// account.createAccount();

		account.setElementState(ElementState.Inserted);
		ContextLayer.Model().Accounts().save(account);

		account.createBalances();
	}

	@Override
	public void openAccount() throws MyOwnException {
		System.out.println("Succeed");

		account.setStatus("OPENED");

		account.setElementState(ElementState.Updated);
		ContextLayer.Model().Accounts().save(account);

	}

	@Override
	public void closeAccount() throws MyOwnException {
		System.out.println("Succeed");
		account.setStatus("CLOSED");

		account.setElementState(ElementState.Updated);
		ContextLayer.Model().Accounts().save(account);
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
