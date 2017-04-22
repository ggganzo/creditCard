package financialcore.interfaces;

import java.util.Map;

import financialcore.account.Balance;
import financialcore.general.MyOwnException;

public interface AccountI {

	public Map<String, Balance> getBalanceHashMap();

	public void addBalanceToHashMap(Balance b);

	public void updateBalanceToHashMap(Balance b);

	public void createAccount() throws MyOwnException;

	public void updateAccount() throws MyOwnException;

}
