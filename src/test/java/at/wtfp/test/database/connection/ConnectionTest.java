package at.wtfp.test.database.connection;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import at.wtfp.database.connection.ConnectionManager;

public class ConnectionTest {

	private static ConnectionManager connectionManager = null;
	
	@BeforeClass
	public static void runBeforeClass(){
		connectionManager = ConnectionManager.getInstance();
	}
	
	@Test
	public void testConnection() {
		boolean testConnection = ConnectionManager.getDaoRegistry().testConnection();
		assertTrue(testConnection);
	}

}
