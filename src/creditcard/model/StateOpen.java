package creditcard.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Map;

import databaseLayer.ElementState;
import databaseLayer.contextLayer.ContextLayer;
import financialcore.account.Balance;
import financialcore.general.MyOwnException;

public class StateOpen implements State {

	CreditCardAccount account = null;

	public StateOpen(CreditCardAccount pAccount) {
		account = pAccount;
	}

	@Override
	public void createAccount() throws MyOwnException {

		throw new MyOwnException("Already created");
	}

	@Override
	public void saveAccount() throws MyOwnException {

		// account.updateAccount();
		account.setElementState(ElementState.Updated);
		ContextLayer.Model().Accounts().save(account);
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

		account.setStatus("CLOSED");
		account.setElementState(ElementState.Updated);
		ContextLayer.Model().Accounts().save(account);
	}

	@Override
	public void purchase(BigDecimal pAmount, String TranDesc) throws MyOwnException {

		account.checkBalance();

		Balance balTotalLImit = account.getBalanceHashMap().get(BalanceCode.LIMIT.toString());
		Balance balPurchase = account.getBalanceHashMap().get(BalanceCode.PURCHASE.toString());
		Balance balCash = account.getBalanceHashMap().get(BalanceCode.CASH.toString());

		if (balTotalLImit.getBalance().compareTo(pAmount.add(balPurchase.getBalance()).add(balCash.getBalance())) < 0) {
			throw new MyOwnException(" Not sufficient balance");
		}

		// balAvailable.decrBalance(account.getAccountNo(), pAmount, "PURCHASE",
		// TranDesc);
		balPurchase.incrBalance(account.getAccountNo(), pAmount, "PURCHASE", TranDesc);

	}

	@Override
	public void cashWidthdraw(BigDecimal pAmount, String TranDesc) throws MyOwnException {
		account.checkBalance();
		Balance balTotalLImit = account.getBalanceHashMap().get(BalanceCode.LIMIT.toString());
		Balance balPurchase = account.getBalanceHashMap().get(BalanceCode.PURCHASE.toString());
		Balance balCash = account.getBalanceHashMap().get(BalanceCode.CASH.toString());
		Balance balCashLimit = account.getBalanceHashMap().get(BalanceCode.LIMITCASH.toString());

		if (balTotalLImit.getBalance().compareTo(pAmount.add(balPurchase.getBalance()).add(balCash.getBalance())) < 0) {
			throw new MyOwnException(" Available balance is higher than balance");
		}

		if (balCashLimit.getBalance().compareTo(pAmount.add(balCash.getBalance())) < 0) {
			throw new MyOwnException(" Cash limit is negative");
		}

		// balAvailable.decrBalance(account.getAccountNo(), pAmount, "WITHDRAW",
		// TranDesc);
		balCash.incrBalance(account.getAccountNo(), pAmount, "WITHDRAW", TranDesc);

	}

	@Override
	public void repayment(BigDecimal pAmount, String TranDesc) throws MyOwnException {
		account.checkBalance();

		Balance balCash = account.getBalanceHashMap().get(BalanceCode.CASH.toString());
		Balance balPurchase = account.getBalanceHashMap().get(BalanceCode.PURCHASE.toString());

		if (balCash.getBalance().compareTo(pAmount) > 0) {

			balCash.decrBalance(account.getAccountNo(), pAmount, "REPAYMENT", TranDesc);

		} else {

			BigDecimal cashBal = balCash.getBalance();
			balCash.decrBalance(account.getAccountNo(), cashBal, "REPAYMENT", TranDesc);
			balPurchase.decrBalance(account.getAccountNo(), pAmount.subtract(cashBal), "REPAYMENT", TranDesc);

		}

		Balance balMinPay = account.getBalanceHashMap().get(BalanceCode.MINPAYAMOUNT.toString());
		if (balMinPay.getBalance().compareTo(new BigDecimal(0)) > 0) {
			if (balMinPay.getBalance().compareTo(pAmount) >= 0) {
				balMinPay.decrBalance(account.getAccountNo(), pAmount, "REPAYMENT", TranDesc);
			} else {
				balMinPay.decrBalance(account.getAccountNo(), balMinPay.getBalance(), "REPAYMENT", TranDesc);
			}
		}

	}

	@Override
	public void createStatement(BigDecimal pAmount, LocalDate pDueDate) throws MyOwnException {
		System.out.println("createStatement");

		Balance balMinPay = account.getBalanceHashMap().get(BalanceCode.MINPAYAMOUNT.toString());

		balMinPay.setBalance(pAmount);
		account.updateBalanceToHashMap(balMinPay);

	}

}
