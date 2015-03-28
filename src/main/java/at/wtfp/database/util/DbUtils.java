package at.wtfp.database.util;

import java.sql.SQLException;

import at.wtfp.domain.Entry;

import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DbUtils {

	private static DbUtils INSTANCE = null;
	
	private static ConnectionSource connectionSource;

	private static Boolean createTablesIfNotExist = null;
	/**
	 * Singleton
	 */
	private DbUtils() {
	}

	public static DbUtils getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DbUtils();
		}
		return INSTANCE;
	}

	/**
	 * The Connection-Source (ATM: Only JDBC)
	 * @param cs
	 * @param createTablesIfNotExist 
	 */
	public static void setConnectionSource(ConnectionSource cs, Boolean createTables) {
		connectionSource = cs;
		
		createTablesIfNotExist = createTables;
	}

	public int createDatabase() throws SQLException {
		int commandsUsed = 0;
		
		if(createTablesIfNotExist)
			commandsUsed = createTablesIfNotExist();
		else
			commandsUsed = createTablesForce();
		
		return commandsUsed;
	}
	
	private int createTablesForce() throws SQLException {
		int x = TableUtils.createTable(connectionSource, Entry.class);
		return x;
	}

	private int createTablesIfNotExist() throws SQLException{
		int x = TableUtils.createTableIfNotExists(connectionSource, Entry.class);
		return x;
	}

}
