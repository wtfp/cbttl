package at.wtpf.test.database.connection;

import java.sql.SQLException;

import at.wtpf.database.connection.ConnectionManager;

public class ConnectionTest {

	public ConnectionTest() {
		ConnectionManager connectionManager = ConnectionManager.getInstance();
		try {
			connectionManager.createDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
