package databaseLayer.contextLayer;

import java.util.List;

import databaseLayer.dao.IDataManager;
import databaseLayer.factory.DataStoreFactory;
import financialcore.account.Account;
import financialcore.account.Transaction;

public class TransactionContextLayer extends IContextLayer<Transaction> {

	private static TransactionContextLayer instance = new TransactionContextLayer();
    private IDataManager<Transaction> dataManager = null;

    private TransactionContextLayer(){}

    public static TransactionContextLayer Transactions(){
        return instance;
    }
    
	@Override
	public Transaction getElement(int id) {
		return getDataManager().getElement(id);
	}

	@Override
	public List<Transaction> getElements() {
		return getDataManager().getElements();
	}

	public List<Transaction> getElementsByAccount(int accountNumber) {
		return getDataManager().getElements(accountNumber, null);
	}
	
	@Override
	public IDataManager<Transaction> getDataManager() {
		 if (dataManager == null) {
	            dataManager = DataStoreFactory.getInstance().createManager(Transaction.class);
	        }

	        return dataManager;
	}

}
