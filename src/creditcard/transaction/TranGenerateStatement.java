package creditcard.transaction;

import creditcard.model.CreditCardAccount;
import financialcore.account.NotificationTran;
import financialcore.account.TransactionTemplate;
import financialcore.general.MyOwnException;

public class TranGenerateStatement implements TranCommand {

	CreditCardAccount account = null;
	TransactionTemplate tranTemplate;

	public TranGenerateStatement(TransactionTemplate pTranTemplate) {
		tranTemplate = pTranTemplate;
		account = null;// TODO
	}

	@Override
	public void execute() throws MyOwnException {
		// account.cashWidthdraw(tranTemplate.amount, tranTemplate.description);
		account.createStatement(tranTemplate.amount);
		// NotificationI notify = new NotificationTran();
		NotificationTran.INSTANCE.sendStatement(tranTemplate);
		// notify.sendNotifictation(tranTemplate);
	}

}
