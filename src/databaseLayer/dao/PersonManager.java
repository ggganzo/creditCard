package databaseLayer.dao;


import financialcore.customer.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import databaseLayer.DBConnection;


/**
 * Created by orifjon9 on 4/17/2017.
 */
public class PersonManager implements IDataManager<Person> {

    @Override
    public Person getElement(int id) {
    	try (ResultSet resultSet = executeQuery("SELECT * FROM person WHERE personid = " + id)) {

            while (resultSet.next()) {
                return convertToPerson(resultSet);
            }

            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Person> getElements() {
    	List<Person> persons = new ArrayList<>();
        try (ResultSet resultSet = executeQuery("SELECT * FROM person order by personid desc")) {

            while (resultSet.next()) {
            	persons.add(convertToPerson(resultSet));
            }

            return persons;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Person> getElements(Object obj1, Object obj2) {
    	List<Person> persons = new ArrayList<>();
    	String sql = "SELECT * FROM person ";
    	String filter = " WHERE typeid = 0 ";
    	
    	if(obj1.toString().length() > 0){
    		if(filter.length() > 0){
    			filter+=" AND ";
    		}
    		
    		filter+=" lastname like '%" + obj1.toString() + "%'";
    	}
    	
    	if(obj2.toString().length() > 0){
    		if(filter.length() > 0){
    			filter+=" AND ";
    		}
    		
    		filter+=" firstname like '%" + obj2.toString() + "%'";
    	}
    	
    	
    	
    	try (ResultSet resultSet = executeQuery(sql + filter)) {

            while (resultSet.next()) {
            	persons.add(convertToPerson(resultSet));
            }

            return persons;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public Person getElement(Object obj1, Object obj2) {
    	String sql = "SELECT * FROM person WHERE login = '" + obj1.toString() + "' AND password = '" + obj2.toString() + "' AND typeid = 1;";
    	
    	try (ResultSet resultSet = executeQuery(sql)) {

            while (resultSet.next()) {
            	return convertToPerson(resultSet);
            }

            return null;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public boolean add(Person element) {
    	try (Connection connection = DBConnection.SQLiteConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO person(lastname, firstname, age, login, password, typeid) VALUES(?,?,?,?,?,?);")) {

               Class.forName("org.sqlite.JDBC");

               setParameters(statement, element);
               statement.setInt(6, element.getType().getTypeId());
               
               return statement.executeUpdate() > 0;

           } catch (Exception ex) {
               throw new RuntimeException(ex);
           }
    }

    @Override
    public boolean update(Person element) {
    	try (Connection connection = DBConnection.SQLiteConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE person SET lastname = ?, firstname = ?, age = ?, login = ?, password = ? WHERE personid = ?;")) {

               Class.forName("org.sqlite.JDBC");

               setParameters(statement, element);
               statement.setLong(6, element.getPersonId());
               
               return statement.executeUpdate() > 0;

           } catch (Exception ex) {
               throw new RuntimeException(ex);
           }
    }

    @Override
    public boolean delete(Person element) {
        return false;
    }
    
    private void setParameters(PreparedStatement statement, Person person) throws SQLException {
        statement.setString(1, person.getLastName());
        statement.setString(2, person.getFirstName());
        statement.setInt(3, person.getAge());
        statement.setString(4, person.getUserName());
        statement.setString(5, person.getPassword());
        
    }

    private Person convertToPerson(ResultSet resultSet) throws SQLException{
    	Person person = PersonType.Customer.getTypeId() == (resultSet.getInt("typeid")) ? new Customer() : new Staff();  
    	
    	person.setPersonId(resultSet.getInt("personid"));
    	person.setLastName(resultSet.getString("lastname"));
    	person.setFirstName(resultSet.getString("firstname"));
    	person.setAge(resultSet.getInt("age"));
    	person.setUserName(resultSet.getString("login"));
    	person.setPassword(resultSet.getString("password"));
        
        return person;
    }
}
