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
	
	private final String propertiesFileName = "database.properties";
	private Properties properties = new Properties();
	
	private static String dbms = null;
	
	private static String host = null;
	private static String port = null;
	private static String database = null;
	private static String user = null;
	private static String pass = null;
	
	private static ConnectionManager INSTANCE = null;
	
	/**
	 * Singleton
	 * @throws SQLException
	 * @throws IOException If database.properties were not found
	 */
	private ConnectionManager() throws SQLException, IOException {
		loadProperties();

		String connectionString = null;
		try {
			connectionString = getConnectionString(dbms);
		} catch (Exception e) {
			// TODO dbms not supported yet
			e.printStackTrace();
		}		
		
		//Doesn't has to be Jdbc
		ConnectionSource connectionSource = new JdbcConnectionSource(connectionString, user, pass);
		
		DaoRegistry.setConnectionSource(connectionSource);
	}

	public static ConnectionManager getInstance() {
		if(INSTANCE == null){
			try {
				INSTANCE = new ConnectionManager();
			} catch (SQLException e) {
				//TODO: Exception weiter behandeln
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				//TODO: Exception weiter behandeln
				e.printStackTrace();
			}
		}		
		return INSTANCE;
	}
	
	private void loadProperties() throws IOException {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
		
		if(inputStream != null) {
			properties.load(inputStream);			
			
			dbms = getDbDBMS();
			host = getDbHost();
			port = getDbPort();
			database = getDbName();
			user = getDbUsername();
			pass = getDbPassword();
		} else {
			throw new FileNotFoundException("Property file '" + propertiesFileName + "' not found on classpath");
		}	
	}
	
	private String getDbDBMS(){
		return properties.getProperty("db.dbms");
	}	
	private String getDbHost(){
		return properties.getProperty("db.host");
	}
	private String getDbPort(){
		return properties.getProperty("db.port");
	}
	private String getDbName(){
		return properties.getProperty("db.name");
	}
	private String getDbUsername(){
		return properties.getProperty("db.username");
	}
	private String getDbPassword(){
		return properties.getProperty("db.password");
	}	
	private String getDbPoolMax(){
		return properties.getProperty("db.pool.max");
	}
	
	private String getConnectionString(String dbms) throws Exception {
		String connectionString = null;
		
		switch(dbms){
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
	
	private static String getPostgresConnectionString(String host, String port, String database){
		StringBuilder b = new StringBuilder();
		b.append("jdbc:postgresql://").append(host).append(":").append(port).append("/").append(database);
		return b.toString();
	}
	
	private static String getMySQLConnectionString(String host, String port, String database){
		StringBuilder b = new StringBuilder();
		b.append("jdbc:mysql://").append(host).append(":").append(port).append("/").append(database);
		return b.toString();
	}

	/**
	 * 
	 * @return The Dao-Registry
	 */
	public static DaoRegistry getDaoRegistry(){
		return DaoRegistry.getInstance();		
	}

}
