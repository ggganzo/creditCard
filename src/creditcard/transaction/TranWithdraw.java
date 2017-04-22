package creditcard.transaction;

import creditcard.model.CreditCardAccount;
import financialcore.account.NotificationTran;
import financialcore.account.TransactionTemplate;
import financialcore.general.MyOwnException;

public class TranWithdraw implements TranCommand {

	CreditCardAccount account = null;
	TransactionTemplate tranTemplate;

	public TranWithdraw(TransactionTemplate pTranTemplate, CreditCardAccount pAccount) {
		tranTemplate = pTranTemplate;
		account = pAccount;
	}

	@Override
	public boolean execute() throws MyOwnException {
		account.cashWidthdraw(tranTemplate.amount, tranTemplate.description);
		NotificationTran.INSTANCE.sendNotifictation(tranTemplate);
		return true;
	}

}
