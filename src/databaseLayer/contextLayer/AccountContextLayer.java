package databaseLayer.contextLayer;

import financialcore.account.*;
import databaseLayer.factory.*;
import databaseLayer.dao.IDataManager;

import java.util.List;

import creditcard.model.CreditCardAccount;

/**
 * Created by orifjon9 on 4/17/2017.
 */
public class AccountContextLayer extends IContextLayer<CreditCardAccount> {

    private static IContextLayer instance = new AccountContextLayer();
    private IDataManager<CreditCardAccount> dataManager = null;

    private AccountContextLayer(){}

    public static IContextLayer<CreditCardAccount> Accounts(){
        return instance;
    }

    @Override
    public CreditCardAccount getElement(int id) {
        return getDataManager().getElement(id);
    }

    @Override
    public List<CreditCardAccount> getElements() {
        return getDataManager().getElements();
    }

    @Override
    public IDataManager<CreditCardAccount> getDataManager() {
        if (dataManager == null) {
            dataManager = DataStoreFactory.getInstance().createManager(Account.class);
        }

        return dataManager;
    }

}
