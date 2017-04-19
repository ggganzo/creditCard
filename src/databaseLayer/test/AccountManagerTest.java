package databaseLayer.test;

import financialcore.account.*;
import databaseLayer.dao.AccountManager;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Created by orifjon9 on 4/17/2017.
 */
public class AccountManagerTest {

    @Test
    public void checkSelectAccountElement(){
        AccountManager accountManager = new AccountManager();

        Account acc = accountManager.getElement(2);
        Account acc2 = new Account();

        assertEquals(acc, acc2);
    }

    @Test
    public void checkAddAccountElement(){
        AccountManager accountManager = new AccountManager();
        Account acc2 = new Account();
        acc2.setAccountNumber(987456362);
        acc2.setCurrency("USD");
        acc2.setStartDate(LocalDate.parse("2015-01-01"));
        acc2.setEndDate(LocalDate.parse("2019-11-01"));
        acc2.setInterestRate((float) 1.21);
        acc2.setType("credit");

        boolean result = accountManager.add(acc2);


        assertEquals(result, true);
    }

    @Test
    public void checkDeleteAccountElement(){
        AccountManager accountManager = new AccountManager();
        Account acc2 = new Account();
        acc2.setAccountNumber(987456377);

        boolean result = accountManager.delete(acc2);


        assertEquals(result, true);
    }

    @Test
    public void checkUpdateAccountElement(){
        AccountManager accountManager = new AccountManager();
        Account acc2 = new Account();
        acc2.setAccountNumber(987456377);
        acc2.setCurrency("EUR");
        acc2.setStartDate(LocalDate.parse("2015-01-25"));
        acc2.setEndDate(LocalDate.parse("2019-11-30"));
        acc2.setInterestRate((float) 2.21);
        acc2.setType("credit");
        //acc2.setId(3);

        boolean result = accountManager.update(acc2);


        assertEquals(result, true);
    }

}
