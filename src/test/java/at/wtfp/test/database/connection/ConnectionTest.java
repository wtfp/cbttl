package at.wtfp.test.database.connection;

import static org.junit.Assert.assertNotSame;

import java.sql.SQLException;
import java.util.List;

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
	
			int commandsUsed = ConnectionManager.getDbUtils().createDatabase();
			System.out.println("Commands used for DB creation: " + commandsUsed);
			
			failId = ConnectionManager.getDaoRegistry().getEntryDao().create(new Entry("HugoBanane"));
			ConnectionManager.getDaoRegistry().getEntryDao().create(new Entry("HugoBanane"));
			ConnectionManager.getDaoRegistry().getEntryDao().create(new Entry("HugoBanane"));
			
			List<Entry> entrysByDescription = ConnectionManager.getDaoRegistry().getEntryDao().getEntrysByDescription("HugoBanane");
			for(Entry e : entrysByDescription)
				System.out.println(e);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertNotSame(-1, failId);
	}

}
