package databaseLayer.contextLayer;

import databaseLayer.dao.PersonManager;
import financialcore.account.Account;
import financialcore.customer.Person;

/**
 * Created by orifjon9 on 4/19/2017.
 */
public class ContextLayer {
    private static ContextLayer instance = new ContextLayer();
    
    private BalanceContextLayer balanceContextLayer;
    private IContextLayer<Account> accountContextLayer;
    private PersonContextLayer personContextLayer;

    private ContextLayer(){
        balanceContextLayer = BalanceContextLayer.Balances();
        accountContextLayer = AccountContextLayer.Accounts();
        personContextLayer = PersonContextLayer.Persons();
    }

    public static ContextLayer Model() {
        return instance;
    }

    public BalanceContextLayer Balances(){
        return balanceContextLayer;
    }

    public IContextLayer<Account> Accounts(){
        return accountContextLayer;
    }
    
    public PersonContextLayer Persons(){
        return personContextLayer;
    }
}
