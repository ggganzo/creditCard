package databaseLayer;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by orifjon9 on 4/17/2017.
 */
public class DBConnection {
    private static String CONNECTION_STRING = "";
    private static Connection instance;
    private DBConnection(){}

    public static Connection SQLiteConnection() throws SQLException {
        //if(instance == null) {
    	if(CONNECTION_STRING.length() == 0){
    		CONNECTION_STRING = "jdbc:sqlite:" + new File("").getAbsolutePath() + "\\db\\CreditCard.sqlite" ;
    	}    
    	return DriverManager.getConnection(CONNECTION_STRING);
       /* }

        return instance;*/
    }
    
}
