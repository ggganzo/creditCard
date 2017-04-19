package financialcore.interfaces;

import java.util.HashMap;

import financialcore.account.Balance;

public interface AccountI {

	public HashMap<String, Balance> getBalanceHashMap();

	public void addBalanceToHashMap(Balance b);

	public void saveAccount();

}
