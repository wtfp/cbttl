package at.wtfp.database.dao;

import java.sql.SQLException;

import at.wtfp.database.dao.interfaces.EntryDao;
import at.wtfp.database.dao.jdbc.EntryDaoImpl;
import at.wtfp.domain.Entry;

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
//		Dao<Entry, Integer> entryDao = DaoManager.createDao(connectionSource, Entry.class);
		
		entryDao = new EntryDaoImpl(connectionSource);
		
		//Remove later
		if(!entryDao.isTableExists())
			TableUtils.createTable(connectionSource, Entry.class);
	}
	
	public EntryDao getEntryDao(){
		return entryDao;
	}

	//TODO: wo anders
//	public void createDatabase() throws SQLException{
//		TableUtils.createTable(connectionSource, Entry.class);
//	}
	
	/**
	 * @return true if the connection source is open. Once close() has been called, this should return false.
	 */
	public boolean testConnection(){
		return connectionSource.isOpen();
	}

}
