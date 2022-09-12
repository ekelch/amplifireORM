package models;

import java.sql.Connection;
import java.sql.SQLException;

import services.ExecutorService;

public class Driver {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
		ExecutorService exec = new ExecutorService(connection);
		User user = new User(1, "stringy", "tsts", "goodtsst");
		QueryBuilder queryBuilder = new QueryBuilder();
		
		//Object newUser = exec.insert(user, queryBuilder.insertRow(user));
		//System.out.println(newUser.getClass());
		
		String query = queryBuilder.getColumns("*").fromTable("routes").whereEquals("name", "Quinsana Plus").end();
		
		System.out.println(query);
		System.out.println(exec.get(Route.class, query));
		
	}

}
