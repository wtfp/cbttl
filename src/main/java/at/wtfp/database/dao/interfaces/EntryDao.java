/**
 * 
 */
package at.wtfp.database.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import at.wtfp.domain.Entry;

import com.j256.ormlite.dao.Dao;

/**
 * Entry-Dao whitch has a Integer id (Entry.id)
 */
public interface EntryDao extends Dao<Entry, Integer> {
	
	public List<Entry> getEntrysByDescription(String description) throws SQLException;
	
	//Additional Dao-Methodes here
}
