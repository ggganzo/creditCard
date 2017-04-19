package databaseLayer.dao;

import financialcore.account.*;
import databaseLayer.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by orifjon9 on 4/17/2017.
 */
public interface IDataManager<T> {
    T getElement(int id);
    List<T> getElements();
    List<T> getElements(Object obj1,Object obj2);
    boolean add(T element);
    boolean update(T element);
    boolean delete(T element);

    default ResultSet executeQuery(String query) throws Exception {
        Connection connection = DBConnection.SQLiteConnection();
        Statement statement = connection.createStatement();

        Class.forName("org.sqlite.JDBC");


        return statement.executeQuery(query);
    }

    default int executeUpdate(String query){
        try(Connection connection = DBConnection.SQLiteConnection();
        Statement statement = connection.createStatement()) {

            Class.forName("org.sqlite.JDBC");

            return statement.executeUpdate(query);
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
