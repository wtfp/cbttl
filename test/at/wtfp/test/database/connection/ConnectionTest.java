package at.wtfp.test.database.connection;


import java.sql.SQLException;

import at.wtfp.database.connection.ConnectionManager;

public class ConnectionTest {

	public static void main(String args[]){
		new ConnectionTest();
	}
	
	public ConnectionTest() {
		ConnectionManager connectionManager = ConnectionManager.getInstance();
		try {
			connectionManager.createDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
