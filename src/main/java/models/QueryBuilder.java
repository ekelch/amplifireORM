package models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class QueryBuilder {

    protected StringBuffer sql;
    Connection connection;

    public QueryBuilder() throws SQLException {
        sql = new StringBuffer("");
    }


    public QueryBuilder getColumns(String columns){
        sql.append("SELECT " + columns);
        return this;
    }
    
    public QueryBuilder deleteFrom(String tableName){
        sql.append("DELETE FROM " + tableName);
        return this;
    }
    
    public QueryBuilder update(String tableName){
        sql.append("UPDATE " + tableName);
        return this;
    }
    
    public QueryBuilder setColumnValue(String column, String value){
    	if (value.matches("\\d+")) {
    		sql.append(" SET " + column + " = " + value);
    	}
    	else {
    		sql.append(" SET " + column + " = " + "'" + value + "'");
    	}
        return this;
    }

    public QueryBuilder fromTable(String tableName) {
        sql.append(" FROM " + tableName);
        return this;
    }
    
    public QueryBuilder whereEquals(String column, String index) {
    	
    	if (index.matches("\\d+")) {
    		sql.append(" WHERE " + column + " = " + index);
    	}
    	else {
    		sql.append(" WHERE " + column + " = " + "'" + index + "'");
    	}
        
        return this;
    }
    
public QueryBuilder andEquals(String column, String index) {
    	
    	if (index.matches("\\d+")) {
    		sql.append(" AND " + column + " = " + index);
    	}
    	else {
    		sql.append(" AND " + column + " = " + "'" + index + "'");
    	}
        
        return this;
    }

    public String insert(Object entry, String tableName)  { // Evan
 
    	Map<Integer, String> fieldAnnoMap = GetAnnoMap.getNonIdFields(entry);
    	Map<Integer, String> methodAnnoMap = GetAnnoMap.getNonIdGetters(entry);
    	
    	sql.append("INSERT INTO " + tableName);
    	if (fieldAnnoMap.size() > 0) {
    		
    		sql.append(" (");
    		
    		List<Integer> keyList = new ArrayList<Integer>();
    		fieldAnnoMap.forEach((key, value) -> {keyList.add(key);});
    		
    		for (int key:keyList)
    			sql.append(fieldAnnoMap.get(key) + ", ");
    
    		sql.reverse();
    		sql.delete(0,2);
    		sql.reverse();
    		sql.append(") VALUES (");
    		
    		for (int key:keyList) {
    			if (fieldAnnoMap.get(key).getClass().getTypeName().equals("java.lang.String"))
    				sql.append("'");
    			sql.append(methodAnnoMap.get(key));
    			if (fieldAnnoMap.get(key).getClass().getTypeName().equals("java.lang.String"))
    				sql.append("'");
    			sql.append(", ");
    		}
    		sql.reverse();
    		sql.delete(0,2);
    		sql.reverse();
    		sql.append(");");
    	}
    	return sql.toString();
    }

    public String end() {
        return sql.toString();
    }
 
    
    
}