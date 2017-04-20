package databaseLayer.contextLayer;

import financialcore.account.*;
import databaseLayer.factory.*;
import databaseLayer.dao.IDataManager;

import java.util.List;

/**
 * Created by orifjon9 on 4/17/2017.
 */
public class AccountContextLayer extends IContextLayer<Account> {

    private static IContextLayer instance = new AccountContextLayer();
    private IDataManager<Account> dataManager = null;

    private AccountContextLayer(){}

    public static IContextLayer<Account> Accounts(){
        return instance;
    }

    @Override
    public Account getElement(int id) {
        return getDataManager().getElement(id);
    }

    @Override
    public List<Account> getElements() {
        return getDataManager().getElements();
    }

    @Override
    public IDataManager<Account> getDataManager() {
        if (dataManager == null) {
            dataManager = DataStoreFactory.getInstance().createManager(Account.class);
        }

        return dataManager;
    }

}
