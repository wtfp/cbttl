package at.wtfp.database.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import at.wtfp.database.dao.DaoRegistry;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class ConnectionManager {

	private final static String propertiesFileName = "database.properties";
	private static Properties properties = new Properties();

	private static String dbms = null;

	private static String host = null;
	private static String port = null;
	private static String database = null;
	private static String user = null;
	private static String pass = null;

	static {
		try {
			init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void init() throws SQLException, IOException {
		loadProperties();

		String connectionString = null;
		try {
			connectionString = getConnectionString(dbms);
		} catch (Exception e) {
			// TODO dbms not supported yet
			e.printStackTrace();
		}

		// Doesn't has to be Jdbc
		ConnectionSource connectionSource = new JdbcConnectionSource(
				connectionString, user, pass);

		DaoRegistry.setConnectionSource(connectionSource);

		System.out.println("connectionSource set on DaoRegistry");
	}

	private static void loadProperties() throws IOException {
		InputStream inputStream = ConnectionManager.class.getClassLoader()
				.getResourceAsStream(propertiesFileName);

		if (inputStream != null) {
			properties.load(inputStream);

			dbms = getDbDBMS();
			host = getDbHost();
			port = getDbPort();
			database = getDbName();
			user = getDbUsername();
			pass = getDbPassword();
		} else {
			throw new FileNotFoundException("Property file '"
					+ propertiesFileName + "' not found on classpath");
		}
	}

	private static String getDbDBMS() {
		return properties.getProperty("db.dbms");
	}

	private static String getDbHost() {
		return properties.getProperty("db.host");
	}

	private static String getDbPort() {
		return properties.getProperty("db.port");
	}

	private static String getDbName() {
		return properties.getProperty("db.name");
	}

	private static String getDbUsername() {
		return properties.getProperty("db.username");
	}

	private static String getDbPassword() {
		return properties.getProperty("db.password");
	}

	// private String getDbPoolMax(){
	// return properties.getProperty("db.pool.max");
	// }

	private static String getConnectionString(String dbms) throws Exception {
		String connectionString = null;

		switch (dbms) {
		case "postgres":
			connectionString = getPostgresConnectionString(host, port, database);
			break;
		case "mysql":
			connectionString = getMySQLConnectionString(host, port, database);
			break;
		default:
			throw new Exception("DBMS not supported (yet)");
		}
		return connectionString;
	}

	private static String getPostgresConnectionString(String host, String port, String database) {
		StringBuilder b = new StringBuilder();
		b.append("jdbc:postgresql://").append(host).append(":").append(port).append("/").append(database);
		return b.toString();
	}

	private static String getMySQLConnectionString(String host, String port, String database) {
		StringBuilder b = new StringBuilder();
		b.append("jdbc:mysql://").append(host).append(":").append(port).append("/").append(database);
		return b.toString();
	}

	/**
	 * 
	 * @return The Dao-Registry
	 */
	public static DaoRegistry getDaoRegistry() {
		return DaoRegistry.getInstance();
	}

}
