package databaseLayer.dao;
import databaseLayer.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import creditcard.model.CreditCardAccount;

/**
 * Created by orifjon9 on 4/17/2017.
 */

public class AccountManager implements IDataManager<CreditCardAccount>{

    @Override
    public CreditCardAccount getElement(int id) {
        try (ResultSet resultSet = executeQuery("SELECT * FROM account WHERE number = " + id)) {

            while (resultSet.next()) {
                return convertToAccount(resultSet);
            }

            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<CreditCardAccount> getElements() {
        List<CreditCardAccount> accounts = new ArrayList<>();
        try (ResultSet resultSet = executeQuery("SELECT * FROM account")) {

            while (resultSet.next()) {
                accounts.add(convertToAccount(resultSet));
            }

            return accounts;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<CreditCardAccount> getElements(Object obj1, Object obj2) {
        return null;
    }

    @Override
    public boolean add(CreditCardAccount element) {

        try (Connection connection = DBConnection.SQLiteConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO account(currency,  startdate, enddate, interestrate, type, number, personid, cardnumber, cardname, status) VALUES(?,?,?,?,?,?,?,?,?,?);")) {

            Class.forName("org.sqlite.JDBC");

            setParameters(statement, element);

            return statement.executeUpdate() > 0;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean update(CreditCardAccount element) {

        try (Connection connection = DBConnection.SQLiteConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE account SET  currency = ?,  startdate = ?, enddate = ?, interestrate = ?, type = ?, personid = ?, cardnumber = ?, cardname = ?, status = ? WHERE number = ?;")) {

            Class.forName("org.sqlite.JDBC");

            setParameters(statement, element);
            
            return statement.executeUpdate() > 0;

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean delete(CreditCardAccount element) {
        return executeUpdate("DELETE FROM account WHERE number = " + element.getAccountNumber()) > 0;
    }

    private void setParameters(PreparedStatement statement, CreditCardAccount account) throws SQLException {
        statement.setString(1, account.getCurrency());
        statement.setString(2, account.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        statement.setString(3, account.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        statement.setFloat(4, account.getInterestRate());
        statement.setString(5, account.getType());
        statement.setString(6, account.getCardNumber());
        statement.setString(7, account.getCardName());
        statement.setString(8, account.getStatus());
        statement.setInt(9, account.getAccountNumber());
    }

    private CreditCardAccount convertToAccount(ResultSet resultSet) throws SQLException{
    	CreditCardAccount account = new CreditCardAccount(
    			resultSet.getInt("personid"),
    			resultSet.getString("cardnumber"), 
    			resultSet.getString("cardname"), 
    			resultSet.getInt("number"),
    			resultSet.getString("currency"),
    			LocalDate.parse(resultSet.getString("startdate")),
    			LocalDate.parse(resultSet.getString("enddate")),
    			resultSet.getFloat("interestrate"));
    	
        account.setType(resultSet.getString("type"));
        account.setStatus(resultSet.getString("status"));
        
        return account;
    }
}
