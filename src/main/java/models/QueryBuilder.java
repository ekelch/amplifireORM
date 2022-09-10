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
    	
    	sql.append("INSERT INTO " + tableName);
    	Field[] fields = entry.getClass().getDeclaredFields();
    	List<String> params = new ArrayList<String>();
    	Method[] methods = entry.getClass().getDeclaredMethods();
    	List<String> values = new ArrayList<String>();
    	int i = 0;
    
    	if (fields.length > 0) {
    		
    		sql.append(" (");
    		for (Field field:fields) {
    			if (field.isAnnotationPresent(NonId.class))
    				params.add(field.getName());
				}
    		for (String param:params) {
    			sql.append(param);
    			if (i < params.size() - 1) {
    				sql.append(", ");
    		}
    			i++;
    		}
        	
    		sql.append(") values (");
    		i = 0;
    		for (Method method:methods) {
    			if (method.isAnnotationPresent(NonIdGetter.class))
    				try{
    					Object obj = method.invoke(entry);
    					values.add(obj.toString());
    				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
    					e.printStackTrace();
    				}
				}
    		for (String value:values) {
    			sql.append(value);
    			if (i < values.size() - 1) {
    				sql.append(", ");
    		}
    			i++;
    		}
    		sql.append(");");
    	}
    	return this;
    	
    	// do not return userId field with the use of annotations
    	// do not invoke getter for userId
    	
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