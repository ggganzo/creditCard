package creditcard.transaction;

import creditcard.model.CreditCardAccount;
import financialcore.account.NotificationTran;
import financialcore.account.TransactionTemplate;
import financialcore.general.MyOwnException;

public class TranGenerateStatement implements TranCommand {

	CreditCardAccount account = null;
	TransactionTemplate tranTemplate;

	public TranGenerateStatement(TransactionTemplate pTranTemplate, CreditCardAccount pAccount) {
		tranTemplate = pTranTemplate;
		this.account = pAccount;
	}

	@Override
	public boolean execute() throws MyOwnException {

		account.createStatement(tranTemplate.amount);
		NotificationTran.INSTANCE.sendStatement(tranTemplate);
		return true;
	}

}
