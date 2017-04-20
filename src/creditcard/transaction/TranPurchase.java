package creditcard.transaction;

import creditcard.model.CreditCardAccount;
import financialcore.general.MyOwnException;

public class TranPurchase implements TranCommand {

	CreditCardAccount account = null;
	TransactionTemplate tranTemplate;

	public TranPurchase(TransactionTemplate pTranTemplate) {
		tranTemplate = pTranTemplate;
		account = null;// TODO
	}

	@Override
	public void execute() throws MyOwnException {
		account.purchase(tranTemplate.amount, tranTemplate.description);
	}

}
