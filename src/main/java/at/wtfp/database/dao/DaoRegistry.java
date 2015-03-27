package at.wtfp.database.dao;

import java.sql.SQLException;

import at.wtfp.database.dao.interfaces.EntryDao;
import at.wtfp.database.dao.jdbc.EntryDaoImpl;
import at.wtfp.domain.Entry;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DaoRegistry {

	private static DaoRegistry INSTANCE = null;
	
	private static EntryDao entryDao = null;

	private static ConnectionSource connectionSource;
	
	
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

	public void createDatabase() throws SQLException{
		Dao<Entry, Integer> entryDao = DaoManager.createDao(connectionSource, Entry.class);
		TableUtils.createTable(connectionSource, Entry.class);
	}
	
	/**
	 * @return true if the connection source is open. Once close() has been called, this should return false.
	 */
	public boolean testConnection(){
		return connectionSource.isOpen();
	}

}
