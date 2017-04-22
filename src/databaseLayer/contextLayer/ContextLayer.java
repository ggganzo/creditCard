package databaseLayer.contextLayer;

import creditcard.model.CreditCardAccount;
import databaseLayer.dao.PersonManager;
import financialcore.account.Account;
import financialcore.customer.Person;

/**
 * Created by orifjon9 on 4/19/2017.
 */
public class ContextLayer {
    private static ContextLayer instance = new ContextLayer();
    
    private BalanceContextLayer balanceContextLayer;
    private IContextLayer<CreditCardAccount> accountContextLayer;
    private PersonContextLayer personContextLayer;
    private TransactionContextLayer transactionContextLayer;

    private ContextLayer(){
        balanceContextLayer = BalanceContextLayer.Balances();
        accountContextLayer = AccountContextLayer.Accounts();
        personContextLayer = PersonContextLayer.Persons();
        transactionContextLayer = TransactionContextLayer.Transactions();
    }

    public static ContextLayer Model() {
        return instance;
    }

    public BalanceContextLayer Balances(){
        return balanceContextLayer;
    }

    public IContextLayer<CreditCardAccount> Accounts(){
        return accountContextLayer;
    }
    
    public PersonContextLayer Persons(){
        return personContextLayer;
    }
    
    public TransactionContextLayer Transactions(){
        return transactionContextLayer;
    }
}
