import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import models.User;
import services.QueryBuilder;

public class QueryBuilderTest {

    @Test
    void getColumnsTestString() throws SQLException {
    	QueryBuilder qb = new QueryBuilder();
    	assertEquals("SELECT *", qb.getColumns("*").end());
    }
 
    @Test
    void getColumnsTestInt() throws SQLException {
    	QueryBuilder qb = new QueryBuilder();
    	assertEquals("SELECT 5", qb.getColumns("5").end());
    }
    
    @Test
    void deleteFromString() throws SQLException {
    	QueryBuilder qb = new QueryBuilder();
    	assertEquals("DELETE FROM table", qb.deleteFrom("table").end());
    }
    
    @Test
    void deleteFromInt() throws SQLException {
    	QueryBuilder qb = new QueryBuilder();
    	assertFalse(qb.deleteFrom("555").end().equals("DELETE FROM 333"));
    }
    
    @Test
    void updateString() throws SQLException {
    	QueryBuilder qb = new QueryBuilder();
    	assertEquals("UPDATE table", qb.update("table").end());
    }
    
    @Test
    void getNullUser() throws SQLException {
    	QueryBuilder qb = new QueryBuilder();
    	User user = new User();
    	String getQuery = qb.insert(user, "table").end();
    	
    	assertEquals("INSERT INTO table", getQuery);
    }
    
}