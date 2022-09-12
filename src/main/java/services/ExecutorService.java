package services;

import java.lang.reflect.Constructor;
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
		Map<Integer, Method> nonIdSetterMethods = GetAnnoMap.getNonIdSetterMethods(entry);
		Map<Integer, Method> nonIdGetterMethods = GetAnnoMap.getNonIdGetterMethods(entry);

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
			
			List<Integer> keyList = new ArrayList<Integer>();
    		nonIdSetterMethods.forEach((key, value) -> {keyList.add(key);});
    		Collections.sort(keyList);
    		
    		for (int key:keyList) {
    			Method setter = nonIdSetterMethods.get(key);
    			String value = nonIdGetterMethods.get(key).invoke(entry).toString();
    			setter.invoke(output, value);
    		}
		
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		
		
		return output;
	}
	
	public Object get(Class<?> clazz, String sql) {
		Object output = null;
		
		try {
		Constructor<?> construct = clazz.getConstructor();
		output = construct.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<Integer, Method> setterAnnoMap = GetAnnoMap.getAllSetterMethods(output);
		Map<Integer, String> allFields = GetAnnoMap.getFieldTypes(output);
		System.out.println(allFields);
		
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
						setter.invoke(output, set.getLong(key));
					if (allFields.get(key).equals("class java.lang.String"))
						setter.invoke(output, set.getString(key));
					if (allFields.get(key).equals("int"))
						setter.invoke(output, set.getInt(key));
				}
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return output;
	}
	
	
}
