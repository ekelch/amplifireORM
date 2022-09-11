package models;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import statements.ExecutorService;

public class Driver {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
		ExecutorService exec = new ExecutorService(connection);
		User user = new User(1, "ekelch", "pass", "email");
		QueryBuilder query = new QueryBuilder("users");
		
		//String result = query.getColumns("*").fromTable().viewSQL();
		
		//String result = query.insertRow(user).viewSQL();
		
//		Map<Integer, Method> result = GetAnnoMap.getIdSetters(user);
//		try {
//			result.get(1).invoke(user, 15);
//		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println(user);
	}

}
