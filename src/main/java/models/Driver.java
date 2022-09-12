package models;

import java.sql.Connection;
import java.sql.SQLException;

import services.ExecutorService;

public class Driver {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
		ExecutorService exec = new ExecutorService(connection);
		QueryBuilder queryBuilder = new QueryBuilder();
		
//		Route insertRoute = new Route("Not a Real Route5", 1, "13b", 1000);
//		String insertQuery = queryBuilder.insert(insertRoute, "routes");
//		Object newRoute = exec.insert(insertRoute, insertQuery);
//		//System.out.println(newUser.getClass());
//		
		String getQuery = queryBuilder.getColumns("difficulty").fromTable("routes").end();
//		
//		String query = queryBuilder.getColumns("*").fromTable("users").whereEquals("username", "ekelch").end();
//		
		System.out.println("get request: " + exec.getColumn(Route.class, getQuery));
		
//		Map<Integer, String> fieldNames = GetAnnoMap.getAllFieldNames(insertRoute);
//		System.out.println(insertRoute);
//		System.out.println(fieldNames);
		
	}

}
