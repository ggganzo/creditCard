package databaseLayer.test;

import financialcore.account.Balance;
import databaseLayer.dao.BalanceManager;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by orifjon9 on 4/18/2017.
 */
public class BalanceManagerTest {

    @Test
    public void checkAddBalanceElement(){
        Balance balance = new Balance();
        balance.setAccountNumber(987456362);
        balance.setBalanceCode("AVAILABLE");
        balance.setBalance(new BigDecimal(900));

        BalanceManager manager = new BalanceManager();
        boolean result = manager.add(balance);

        Assert.assertEquals(result, true);

    }

    @Test
    public void checkUpdateBalanceElement(){

        Balance balance = new Balance();

        balance.setAccountNumber(987456362);
        balance.setBalanceCode("LIMITCASH");
        balance.setBalance(new BigDecimal(100));

        BalanceManager manager = new BalanceManager();
        boolean result = manager.add(balance);

        Assert.assertEquals(result, true);

    }

    @Test
    public void checkGetBalancesByAccountNumber(){

        BalanceManager manager = new BalanceManager();

        //List<Balance> balances = manager.getElements();
        List<Balance> balances = manager.getElements(987456362, null);


        Assert.assertEquals(balances.size(), 4);

    }
}
