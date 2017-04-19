package creditcard.model;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

import financialcore.account.Balance;
import financialcore.general.MyOwnException;

public class StateOpen implements State {

	CreditCardAccount account = null;

	public StateOpen(CreditCardAccount pAccount) {
		account = pAccount;
	}

	@Override
	public void saveAccount() {
		// TODO Auto-generated method stub

	}

	@Override
	public void openAccount() throws MyOwnException {
		throw new MyOwnException("Status must be New");

	}

	@Override
	public void closeAccount() throws MyOwnException {

		BigDecimal outStandingBal = new BigDecimal(0);

		Iterator<Map.Entry<String, Balance>> iterator = account.getBalanceHashMap().entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Balance> entry = iterator.next();
			System.out.printf("Key : %s and Value: %s %n", entry.getKey(), entry.getValue());
			outStandingBal = outStandingBal.add(((Balance) entry.getValue()).getBalance());

		}

		if (outStandingBal.compareTo(new BigDecimal(0)) > 0) {
			throw new MyOwnException("There is outstanding balance");
		}
	}

	@Override
	public void purchase(BigDecimal pAmount, String TranDesc) throws MyOwnException {

		account.checkBalance();

		Balance balAvailable = account.getBalanceHashMap().get(BalanceCode.AVAILABLE.toString());
		Balance balPurchase = account.getBalanceHashMap().get(BalanceCode.PURCHASE.toString());

		balAvailable.decrBalance(account.getAccountNo(), pAmount, "PURCHASE", TranDesc);
		balPurchase.incrBalance(account.getAccountNo(), pAmount, "PURCHASE", TranDesc);

	}

	@Override
	public void cashWidthdraw(BigDecimal pAmount, String TranDesc) throws MyOwnException {
		account.checkBalance();
		Balance balAvailable = account.getBalanceHashMap().get(BalanceCode.AVAILABLE.toString());
		Balance balCash = account.getBalanceHashMap().get(BalanceCode.CASH.toString());

		balAvailable.decrBalance(account.getAccountNo(), pAmount, "WITHDRAW", TranDesc);
		balCash.incrBalance(account.getAccountNo(), pAmount, "WITHDRAW", TranDesc);

	}

	@Override
	public void repayment(BigDecimal pAmount, String TranDesc) throws MyOwnException {
		account.checkBalance();

		Balance balCash = account.getBalanceHashMap().get(BalanceCode.CASH.toString());
		Balance balPurchase = account.getBalanceHashMap().get(BalanceCode.PURCHASE.toString());

		Balance balAvailable = account.getBalanceHashMap().get(BalanceCode.AVAILABLE.toString());

		balAvailable.decrBalance(account.getAccountNo(), pAmount, "REPAYMENT", TranDesc);

		if (balCash.getBalance().compareTo(pAmount) > 0) {

			balCash.decrBalance(account.getAccountNo(), pAmount, "REPAYMENT", TranDesc);

		} else {

			balCash.decrBalance(account.getAccountNo(), balCash.getBalance(), "REPAYMENT", TranDesc);
			balPurchase.decrBalance(account.getAccountNo(), pAmount.subtract(balCash.getBalance()), "REPAYMENT",
					TranDesc);

		}

	}

}
