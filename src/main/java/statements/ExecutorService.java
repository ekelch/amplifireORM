package statements;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import annotations.NonIdGetter;

public class ExecutorService {

	Connection connection;
	
	public ExecutorService(Connection connection) {
		super();
		this.connection = connection;
	}

	public Object insert(Object entry, String sql) {
		Object out = null;
		int pk;
		Method[] methods = entry.getClass().getDeclaredMethods();
		List<Method> getters = new ArrayList<Method>();
		for (Method method:methods) {
			if (method.isAnnotationPresent(NonIdGetter.class)) {
				getters.add(method);
			}
		}
		
		
		try {
			Constructor<? extends Object> construct = entry.getClass().getConstructor(entry.getClass());
			out = construct.newInstance(null);
		} catch (NoSuchMethodException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {	
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			int affectedRows = statement.executeUpdate();
			
			if (affectedRows > 0) {
				try (ResultSet set = statement.getGeneratedKeys()){
					if (set.next()) {
						pk = set.getInt(1);
					}
				} catch (SQLException e) {
					e.getMessage();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}

		
		
		return user;
	}
	
	
	
	
}
