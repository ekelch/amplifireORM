import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import models.User;
import services.ConnectionFactory;
import services.ExecutorService;

public class ExecutorServiceTest {

	@Test
	void getRowTest() throws SQLException {
		Connection connection = ConnectionFactory.getConnection();
		ExecutorService exec = new ExecutorService(connection);
		User user = new User(1, "user1", "pass1", "email1");
		
		List<Object> objList = new ArrayList<Object>();
		objList.add(user);
		
		String sql = "SELECT * FROM users WHERE username = 'user1'";
		List<Object> execList = exec.getColumn(User.class, sql);
		assertEquals(execList, objList);
	}
	
}
