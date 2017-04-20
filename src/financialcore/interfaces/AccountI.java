package financialcore.interfaces;

import java.util.HashMap;

import financialcore.account.Balance;
import financialcore.general.MyOwnException;

public interface AccountI {

	public HashMap<String, Balance> getBalanceHashMap();

	public void addBalanceToHashMap(Balance b);
	
	public void updateBalanceToHashMap(Balance b);

	public void createAccount() throws MyOwnException;

	public void updateAccount() throws MyOwnException;

}
