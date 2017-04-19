package databaseLayer.dao;

import financialcore.account.Balance;
import databaseLayer.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by orifjon9 on 4/18/2017.
 */
public class BalanceManager implements IDataManager<Balance> {
    @Override
    public Balance getElement(int id) {
        try (ResultSet resultSet = executeQuery("SELECT * FROM balance WHERE balanceid = " + id)) {

            while (resultSet.next()) {
                return convertToAccount(resultSet);
            }

            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Balance> getElements() {
        List<Balance> balances = new ArrayList<>();
        try (ResultSet resultSet = executeQuery("SELECT * FROM balance;")) {

            while (resultSet.next()) {
                balances.add(convertToAccount(resultSet));
            }

            return balances;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Balance> getElements(Object obj1, Object obj2) {
        int accountNumber = (int)obj1;
        String balanceCode = (String) obj2;

        String sql = "SELECT * FROM balance WHERE accountnumber = " + accountNumber;
        if(balanceCode.trim().length() > 0){
            sql+= " AND code= '" + balanceCode + "'";
        }

        List<Balance> balances = new ArrayList<>();
        try (ResultSet resultSet = executeQuery(sql)) {

            while (resultSet.next()) {
                balances.add(convertToAccount(resultSet));
            }

            return balances;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean add(Balance element) {
        try (Connection connection = DBConnection.SQLiteConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO balance(accountnumber, code,  amount) VALUES(?,?,?);")) {

            Class.forName("org.sqlite.JDBC");

            statement.setInt(1, element.getAccountNumber());
            statement.setString(2, element.getBalanceCode());
            statement.setBigDecimal(3,element.getBalance());

            return statement.executeUpdate() > 0;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean update(Balance element) {
        try (Connection connection = DBConnection.SQLiteConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE balance SET amount = ? WHERE accountnumber = ? AND code = ?;")) {

            Class.forName("org.sqlite.JDBC");

            statement.setBigDecimal(1, element.getBalance());
            statement.setInt(2, element.getAccountNumber());
            statement.setString(3, element.getBalanceCode());


            return statement.executeUpdate() > 0;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean delete(Balance element) {
        return false;
        //throw new BalanceException("You can't remove balance");
    }

    private Balance convertToAccount(ResultSet resultSet) throws SQLException{
        Balance balance = new Balance();

        //balance.setId(resultSet.getInt("balanceid"));
        balance.setAccountNumber(resultSet.getInt("accountnumber"));
        balance.setBalanceCode(resultSet.getString("code"));
        balance.setBalance(resultSet.getBigDecimal("amount"));

        return balance;
    }
}
