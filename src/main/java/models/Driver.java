package models;

import java.sql.Connection;
import java.sql.SQLException;

import statements.ExecutorService;

public class Driver {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
		ExecutorService exec = new ExecutorService(connection);
		User user = new User(1, "insert", "test", "good");
		QueryBuilder query = new QueryBuilder("users");
		
		String insertQuery = query.insertRow(user).viewSQL();
		
		Object newUser = exec.insert(user, insertQuery);
		
		System.out.println(newUser);
	}

}
