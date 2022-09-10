package models;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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

    public QueryBuilder insertRow(Object entry) { // Evan
    	
    	sql.append("INSERT INTO " + tableName);
    	Field[] fields = entry.getClass().getDeclaredFields();
    	Method[] methods = entry.getClass().getDeclaredMethods();
    	
    	if (fields.length > 0) {
    		sql.append(" (");
    		
    		for (int i = 0; i < fields.length; i++) {
        		sql.append(fields[i].getName());
        		if (i < fields.length - 1) {
        			sql.append(", ");
        		}
        	}
    		sql.append(") values (");
    		
    		for (int i = 0; i < fields.length; i++) {
        		sql.append(fields[i].getName());
        		if (i < fields.length - 1) {
        			sql.append(", ");
        		}
        	}
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