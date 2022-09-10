package models;

import java.sql.Connection;
import java.sql.SQLException;

public class Driver {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
		User user = new User(1, "ekelch", "pass", "email");
		QueryBuilder query = new QueryBuilder(connection, "users");
		
		String result = query.getColumns("*").fromTable().viewSQL();
		
		//String result = query.insertRow(user).viewSQL();
		
		System.out.println(result);
		
	}

}
