package creditcard.transaction;

import financialcore.general.MyOwnException;

public interface TranCommand {
	public boolean execute() throws MyOwnException;
}
