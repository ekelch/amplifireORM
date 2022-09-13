package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() throws SQLException {
		
		try {
	        Class.forName("org.postgresql.Driver");
	    } catch (ClassNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
		
		return DriverManager.getConnection(
                "jdbc:postgresql://project1-amplifire.cudk92rje4ex.us-west-1.rds.amazonaws.com:5432/postgres",
                "postgres",
                "amplifire");
	}
	
}
