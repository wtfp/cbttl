package at.wtfp.database.dao.jdbc;

import java.sql.SQLException;
import java.util.List;

import at.wtfp.database.dao.interfaces.EntryDao;
import at.wtfp.domain.Entry;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.QueryBuilder;
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

	@Override
	public List<Entry> getEntrysByDescription(String description) throws SQLException {
		QueryBuilder<Entry, Integer> queryBuilder = queryBuilder();
		
		queryBuilder.where().eq(Entry.DESCRIPTION_FIELD_NAME, description);
		
		List<Entry> foundList = queryBuilder.query();
		return foundList;
	}
}
