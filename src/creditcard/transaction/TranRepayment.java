package creditcard.transaction;

import java.math.BigDecimal;

import creditcard.model.CreditCardAccount;
import financialcore.general.MyOwnException;

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
	}

}
