package at.wtfp.database.dao.jdbc;

import java.sql.SQLException;

import at.wtfp.database.dao.interfaces.EntryDao;
import at.wtfp.domain.Entry;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.sun.webkit.ThemeClient;

/**
 * JDBC-Implementation of {@link ThemeClient} 
 *
 */
public class EntryDaoImpl extends BaseDaoImpl<Entry, Integer> implements EntryDao{

	public EntryDaoImpl(ConnectionSource connectionSource) throws SQLException{
		super(connectionSource, Entry.class);
	}
}
