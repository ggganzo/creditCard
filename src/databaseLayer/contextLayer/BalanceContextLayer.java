package databaseLayer.contextLayer;

import financialcore.account.Balance;
import databaseLayer.factory.*;
import databaseLayer.dao.IDataManager;

import java.util.List;

/**
 * Created by orifjon9 on 4/19/2017.
 */
public class BalanceContextLayer extends IContextLayer<Balance> {

    private static BalanceContextLayer instance = new BalanceContextLayer();
    private IDataManager<Balance> dataManager = null;

    private BalanceContextLayer(){}

    public static BalanceContextLayer Balances(){
        return instance;
    }

    @Override
    public Balance getElement(int id) {
        return getDataManager().getElement(id);
    }

    @Override
    public List<Balance> getElements() {
        return getDataManager().getElements();
    }

    public List<Balance> getElementsByAccount(int accountNumber) {
        return getDataManager().getElements(accountNumber, null);
    }

    public Balance getElement(int accountNumber, String code) {
        List<Balance> balances = getDataManager().getElements(accountNumber, code);
        if(balances.size() > 0)
            return balances.get(0);

        return null;

    }

    @Override
    public IDataManager<Balance> getDataManager() {
        if (dataManager == null) {
            dataManager = DataStoreFactory.getInstance().createManager(Balance.class);
        }

        return dataManager;
    }

}
