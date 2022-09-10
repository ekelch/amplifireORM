package models;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import annotations.NonId;
import annotations.NonIdGetter;


public class QueryBuilder {

    protected StringBuffer sql;
    private String tableName;
    Connection connection;

    public QueryBuilder(Connection connection, String tableName) throws SQLException {
    	this.connection = connection;
        this.tableName = tableName;
        sql = new StringBuffer("");
    }


    public QueryBuilder getColumns(String columns){
        sql.append("SELECT "+columns);
        return this;
    }

    public QueryBuilder fromTable() {
        sql.append(" FROM "+ tableName);
        return this;
    }

    public QueryBuilder insertRow(Object entry)  { // Evan
    	
    	Field[] fields = entry.getClass().getDeclaredFields();
    	Method[] methods = entry.getClass().getDeclaredMethods();

    	Map<Integer, String> fieldAnnoMap = new HashMap<Integer, String>();
    	Map<Integer, String> methodAnnoMap = new HashMap<Integer, String>();
    	
    	sql.append("INSERT INTO " + tableName);
    	if (fields.length > 0) {
    		
    		sql.append(" (");
    		for (Field field:fields) {
    			if (field.isAnnotationPresent(NonId.class)) {
    				//params.add(field.getName());
    				NonId anno = field.getAnnotation(NonId.class);
    				int paramKey = anno.column();
    				String paramValue = field.getName();
    				fieldAnnoMap.put(paramKey, paramValue);
    			}	
			}
    		for (Method method:methods) {
    			if (method.isAnnotationPresent(NonIdGetter.class))
    				try{
    					Object obj = method.invoke(entry);
    					NonIdGetter anno = method.getAnnotation(NonIdGetter.class);
    					//values.add(obj.toString());
    					int paramKey = anno.column();
        				String paramValue = obj.toString();
        				methodAnnoMap.put(paramKey, paramValue);
    					
    				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
    					e.printStackTrace();
    				}
				}
    		
    		for (int i = 1; i <= fieldAnnoMap.size(); i++) {
    			sql.append(fieldAnnoMap.get(i));
    			if (i < fieldAnnoMap.size()) {
    				sql.append(", ");
    			}
    		}
    		sql.append(") VALUES (");
    		
    		for (int i = 1; i <= methodAnnoMap.size(); i++) {
    			sql.append(methodAnnoMap.get(i));
    			if (i < methodAnnoMap.size()) {
    				sql.append(", ");
    			}
    		}
    		sql.append(");");
    	}
    	return this;
    }

    public String viewSQL() {
        return sql.toString();
    }

    public void executeQuery() {
        try(Statement statement = connection.createStatement();)
        
        {
            ResultSet set = statement.executeQuery(sql.toString());
        } catch(SQLException e) {

        }

    }

}