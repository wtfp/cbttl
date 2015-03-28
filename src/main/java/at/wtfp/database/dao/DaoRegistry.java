package at.wtfp.database.dao;

import java.sql.SQLException;

import at.wtfp.database.dao.interfaces.EntryDao;
import at.wtfp.database.dao.jdbc.EntryDaoImpl;

import com.j256.ormlite.support.ConnectionSource;

public class DaoRegistry {

	private static DaoRegistry INSTANCE = null;
	private static ConnectionSource connectionSource;
	
	private static EntryDao entryDao = null;	
	
	/**
	 * Singleton
	 */
	private DaoRegistry() {
	}

	public static DaoRegistry getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DaoRegistry();
		}
		return INSTANCE;
	}

	/**
	 * The Connection-Source (ATM: Only JDBC)
	 * @param cs
	 * @throws SQLException 
	 */
	public static void setConnectionSource(ConnectionSource cs) throws SQLException {
		connectionSource = cs;
		createDaos();
	}
	
	private static void createDaos() throws SQLException {	
		entryDao = new EntryDaoImpl(connectionSource);
	}
	
	public EntryDao getEntryDao(){
		return entryDao;
	}
	
	/**
	 * @return true if the connection source is open. Once close() has been called, this should return false.
	 */
	public boolean testConnection(){
		return connectionSource.isOpen();
	}

}
