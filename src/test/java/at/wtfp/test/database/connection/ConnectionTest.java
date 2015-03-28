package at.wtfp.test.database.connection;

import static org.junit.Assert.assertNotSame;

import java.sql.SQLException;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import at.wtfp.database.connection.ConnectionManager;
import at.wtfp.domain.Entry;

public class ConnectionTest {

	@BeforeClass
	public static void runBeforeClass(){
	}
	
	@Test
	public void testCreate() {
		int failId = -1;
		try {
//			DaoRegistry daoRegistry = ConnectionManager.getDaoRegistry();
//			EntryDao entryDao = daoRegistry.getEntryDao();
//			
//			failId = entryDao.create(new Entry("TestEntry - " + new Date()));
			
			failId = ConnectionManager.getDaoRegistry().getEntryDao().create(new Entry("TestEntry - " + new Date()));
			
			System.out.println("HALLELUJA! Created Entry with id: [" + failId + "]");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertNotSame(-1, failId);
	}

}
