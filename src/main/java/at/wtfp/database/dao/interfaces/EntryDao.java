/**
 * 
 */
package at.wtfp.database.dao.interfaces;

import at.wtfp.domain.Entry;

import com.j256.ormlite.dao.Dao;

/**
 * Entry-Dao whitch has a Integer id (Entry.id)
 */
public interface EntryDao extends Dao<Entry, Integer> {
	//Additional Dao-Methodes here
}
