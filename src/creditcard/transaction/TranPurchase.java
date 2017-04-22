package creditcard.transaction;

import creditcard.model.CreditCardAccount;
import financialcore.account.NotificationTran;
import financialcore.account.TransactionTemplate;
import financialcore.general.MyOwnException;

public class TranPurchase implements TranCommand {

	CreditCardAccount account = null;
	TransactionTemplate tranTemplate;

	public TranPurchase(TransactionTemplate pTranTemplate, CreditCardAccount account) {
		tranTemplate = pTranTemplate;
		this.account = account;
	}

	@Override
	public void execute() throws MyOwnException {
		this.account.purchase(tranTemplate.amount, tranTemplate.description);
		NotificationTran.INSTANCE.sendNotifictation(tranTemplate);
		//NotificationI notify = new NotificationTran();
		//notify.sendNotifictation(tranTemplate);

	}

}
