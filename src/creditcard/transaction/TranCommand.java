package creditcard.transaction;

import financialcore.general.MyOwnException;

public interface TranCommand {
	public void execute() throws MyOwnException;
}
