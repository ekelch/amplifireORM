package models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import services.ConnectionFactory;
import services.ExecutorService;
import services.QueryBuilder;

public class Driver {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = ConnectionFactory.getConnection();
		ExecutorService exec = new ExecutorService(connection);
		QueryBuilder queryBuilder = new QueryBuilder();
		
//		Route insertRoute = new Route("Not a Real Route5", 1, "13b", 1000);
//		String insertQuery = queryBuilder.insert(insertRoute, "routes").end();
//		System.out.println(insertQuery);
		
    	String getQuery = queryBuilder.getColumns("*").fromTable("users").whereEquals("username", "user1").end();
    	System.out.println(getQuery);
    	List<Object> execList = exec.getRow(User.class, getQuery);
    	System.out.println(execList);
		
//		Object newRoute = exec.insert(insertRoute, insertQuery);
//		//System.out.println(newUser.getClass());
//		
//		String getQuery = queryBuilder.getColumns("*").fromTable("routes").whereEquals("location_id", "3").end();
//		String deleteQuery = queryBuilder.deleteFrom("routes").whereEquals("name", "Not a Real Route2").end();
//		System.out.println(deleteQuery);
//		String updateQuery = queryBuilder.update("routes").setColumnValue("name", "Quinsana Plus").whereEquals("name", "Quinsana").end();
//		System.out.println(updateQuery);
//		boolean updated = exec.update(updateQuery);
//		System.out.println(updated);
//		String query = queryBuilder.getColumns("*").fromTable("users").whereEquals("username", "ekelch").end();
//		
//		List<Object> getRequestOutput = exec.getRow(Route.class, getQuery);
		
//		getRequestOutput.forEach((boop) -> {System.out.println(boop);});
//		boolean deleted = exec.delete(deleteQuery);
//		Map<Integer, String> fieldNames = GetAnnoMap.getAllFieldNames(insertRoute);
//		System.out.println(insertRoute);
//		System.out.println(fieldNames);
//		System.out.println(getQuery);
		
//		Location newLocation = new Location();
//		newLocation.setLocation_id(0);
//		newLocation.setLocation_name("Phantasia");
//		String insertLocation = queryBuilder.insert(newLocation, "locations").end();
//		Object loco = exec.insert(newLocation, insertLocation);
//		System.out.println(loco);
		
		connection.close();
	}
 
}
