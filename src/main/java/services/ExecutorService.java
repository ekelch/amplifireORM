package services;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import models.GetAnnoMap;
import models.getLists;

public class ExecutorService {

	Connection connection;
	
	public ExecutorService(Connection connection) {
		super();
		this.connection = connection;
	}

	public Object insert(Object entry, String sql) {
		Object output = entry;
		int pk = -1;
		List<Method> idSetters = getLists.getIdSetters(entry);

		try {
			PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			int affectedRows = statement.executeUpdate();
			if (affectedRows > 0) {
				try (ResultSet set = statement.getGeneratedKeys()){
					if (set.next()) {
						pk = set.getInt(1);
					}
				} catch (SQLException | IllegalArgumentException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if (pk != -1)
			for (Method setter:idSetters) {
				setter.invoke(output, pk);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		
		
		return output;
	}
	
	public Object getRow(Class<?> clazz, String sql) {
		
		Object instance = Instantiater.construct(clazz);
		
		Map<Integer, Method> setterAnnoMap = GetAnnoMap.getAllSetterMethods(instance);
		Map<Integer, String> allFields = GetAnnoMap.getFieldTypes(instance);
		
		List<Integer> keyList = new ArrayList<Integer>();
		setterAnnoMap.forEach((key, value) -> {keyList.add(key);});
		Collections.sort(keyList);
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet set = statement.executeQuery();
			while (set.next()) {
				for (int key:keyList) {
					Method setter = setterAnnoMap.get(key);
					if (allFields.get(key).equals("long"))
						setter.invoke(instance, set.getLong(key));
					if (allFields.get(key).equals("class java.lang.String"))
						setter.invoke(instance, set.getString(key));
					if (allFields.get(key).equals("int"))
						setter.invoke(instance, set.getInt(key));
				}
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	public List<Object> getColumn(Class<?> clazz, String sql){
		
		String[] arr = sql.split(" "); 	// get column name
		String column = arr[1];			// assuming it is always immediately after SELECT
		Object obj = Instantiater.construct(clazz);
		
		Map<Integer, String> fieldTypes = GetAnnoMap.getFieldTypes(obj);
		List<Integer> keyList = new ArrayList<Integer>();
		fieldTypes.forEach((key, value) -> {keyList.add(key);});
		
		List<Object> columnList = new ArrayList<Object>();
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet set = statement.executeQuery();
			
			while (set.next())
				columnList.add(set.getString(column));
//			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return columnList;
	}
	
	public boolean update(String sql) {
		boolean updated = false;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			int affectedRows = statement.executeUpdate();
			if (affectedRows > 0)
				updated = true;
			
		} catch(Exception e) {
			//e.printStackTrace();
		}
		return updated;
	}
	
	public boolean delete(String sql) {
		boolean deleted = false;
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			int affectedRows = statement.executeUpdate();
			if (affectedRows > 0)
				deleted = true;
			
		} catch(Exception e) {
			//e.printStackTrace();
		}
		return deleted;
	}
	
}
