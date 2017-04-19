package databaseLayer.dao;

import financialcore.account.Transaction;
import databaseLayer.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by orifjon9 on 4/17/2017.
 */
public class TransactionManager implements IDataManager<Transaction> {

    @Override
    public Transaction getElement(int id) {
        try (ResultSet resultSet = executeQuery("SELECT * FROM c_transaction WHERE transactionnumber = " + id)) {

            while (resultSet.next()) {
                return convertToAccount(resultSet);
            }

            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Transaction> getElements() {
        List<Transaction> transactions = new ArrayList<>();
        try (ResultSet resultSet = executeQuery("SELECT * FROM c_transaction")) {

            while (resultSet.next()) {
                transactions.add(convertToAccount(resultSet));
            }

            return transactions;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Transaction> getElements(Object obj1, Object obj2) {
        return null;
    }

    @Override
    public boolean add(Transaction element) {
        try (Connection connection = DBConnection.SQLiteConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO \"c_transaction\"(transactionnumber, accountnumber,  balancecode, amount, type, postbalance, transactioncode, description, transactiondate) VALUES(?,?,?,?,?,?,?,?,?);")) {

            Class.forName("org.sqlite.JDBC");

            statement.setInt(1, element.getTransactionNumber());
            statement.setInt(2, element.getAccountNumber());
            statement.setString(3, element.getBalanceCode());
            statement.setBigDecimal(4, element.getAmount());
            statement.setString(5, element.getType());
            statement.setBigDecimal(6, element.getPostBalance());
            statement.setString(7, element.getTranCode());
            statement.setString(8, element.getDescription());
            statement.setString(9, element.getTranDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            return statement.executeUpdate() > 0;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean update(Transaction element) {
        return false;
    }

    @Override
    public boolean delete(Transaction element) {
        return false;
    }

    private Transaction convertToAccount(ResultSet resultSet) throws SQLException {
        Transaction transaction = new Transaction();

        transaction.setTransactionNumber(resultSet.getInt("transactionnumber"));
        transaction.setAccountNumber(resultSet.getInt("accountnumber"));
        transaction.setBalanceCode(resultSet.getString("balancecode"));
        transaction.setAmount(resultSet.getBigDecimal("amount"));
        transaction.setType(resultSet.getString("type"));
        transaction.setPostBalance(resultSet.getBigDecimal("postbalance"));
        transaction.setTranCode(resultSet.getString("transactioncode"));
        transaction.setDescription(resultSet.getString("description"));
        transaction.setTranDate(LocalDate.parse(resultSet.getString("transactiondate")));

        return transaction;
    }
}
