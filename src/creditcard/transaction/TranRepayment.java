package creditcard.transaction;

import creditcard.model.CreditCardAccount;
import financialcore.account.NotificationTran;
import financialcore.account.TransactionTemplate;
import financialcore.general.MyOwnException;
import financialcore.interfaces.NotificationI;

public class TranRepayment implements TranCommand {
	CreditCardAccount account = null;
	TransactionTemplate tranTemplate;

	public TranRepayment(TransactionTemplate pTranTemplate) {
		tranTemplate = pTranTemplate;
		account = null;// TODO
	}

	@Override
	public void execute() throws MyOwnException {
		account.repayment(tranTemplate.amount, tranTemplate.description);
		NotificationI notify = new NotificationTran();
		notify.sendNotifictation(tranTemplate);
	}

}
