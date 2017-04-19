package databaseLayer.contextLayer;

/**
 * Created by orifjon9 on 4/19/2017.
 */
public class ContextLayer {
    private static ContextLayer instance = new ContextLayer();
    private BalanceContextLayer balanceContextLayer;
    private IContextLayer accountContextLayer;

    private ContextLayer(){
        balanceContextLayer = BalanceContextLayer.Balances();
        accountContextLayer = AccountContextLayer.Accounts();
    }

    public static ContextLayer Model() {
        return instance;
    }

    public BalanceContextLayer Balances(){
        return balanceContextLayer;
    }

    public IContextLayer Accounts(){
        return accountContextLayer;
    }
}
