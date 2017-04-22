package creditcard.transaction;

import creditcard.model.CreditCardAccount;
import financialcore.account.NotificationTran;
import financialcore.account.TransactionTemplate;
import financialcore.general.MyOwnException;

public class TranRepayment implements TranCommand {
	CreditCardAccount account = null;
	TransactionTemplate tranTemplate;

	public TranRepayment(TransactionTemplate pTranTemplate, CreditCardAccount pAccount) {
		tranTemplate = pTranTemplate;
		account = pAccount;
	}

	@Override
	public boolean execute() throws MyOwnException {
		account.repayment(tranTemplate.amount, tranTemplate.description);
		NotificationTran.INSTANCE.sendNotifictation(tranTemplate);
		return true;
	}

}
