package databaseLayer.test;

import financialcore.account.*;
import databaseLayer.dao.BalanceManager;
import databaseLayer.dao.TransactionManager;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by orifjon9 on 4/18/2017.
 */
public class TransactionManagerTest {

    @Test
    public void checkAddTransactionElement(){
        BigDecimal tranAmount = new BigDecimal(200.00);

        Transaction transaction = new Transaction();
        transaction.setAccountNumber(987456362);
        transaction.setTransactionNumber(123456);
        transaction.setBalanceCode("CASH");
        transaction.setAmount(tranAmount);
        transaction.setType("CASH");

        transaction.setTranCode("BHGD-TGFD-1234");
        transaction.setDescription("CASH from ATM");
        transaction.setTranDate(LocalDate.now());


        BalanceManager balanceManager = new BalanceManager();
        List<Balance> balances = balanceManager.getElements(transaction.getAccountNumber(), transaction.getBalanceCode());

        transaction.setPostBalance( balances.get(0).getBalance().subtract(tranAmount));

        TransactionManager manager = new TransactionManager();
        boolean result = manager.add(transaction);

        Assert.assertEquals(result, true);

    }

    @Test
    public void checkGetBalancesByAccountNumber(){

        TransactionManager manager = new TransactionManager();

        //List<Balance> balances = manager.getElements();
        List<Transaction> transactions = manager.getElements();


        Assert.assertEquals(transactions.size() > 0, true);

    }
}
