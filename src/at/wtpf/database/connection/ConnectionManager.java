package at.wtpf.database.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import at.wtpf.domain.Entry;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class ConnectionManager {
	
	private static ConnectionManager INSTANCE = null;	
	
	private final String propertiesFileName = "database.properties";
	private Properties properties = new Properties();
	
	private JdbcConnectionSource connectionSource = null;
	
	private ConnectionManager() throws IOException, SQLException {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
		
		if(inputStream != null) {
			properties.load(inputStream);
		} else {
			throw new FileNotFoundException("Property file '" + propertiesFileName + "' not found on classpath");
		}		
		
		String url = getPostgresConnectionString(propertiesFileName, propertiesFileName, propertiesFileName);		
		connectionSource = new JdbcConnectionSource(url);
	}
		
	public static ConnectionManager getInstance() {
		if(INSTANCE == null){
			try {
				INSTANCE = new ConnectionManager();
			} catch (IOException e) {	
				//TODO: Exception weiter behandeln
				e.printStackTrace();
				return null;
			} catch (SQLException e) {
				//TODO: Exception weiter behandeln
				e.printStackTrace();
				return null;
			}
		}		
		return INSTANCE;
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
	private String getDbHost(){
		return properties.getProperty("db.host");
	}
	private String getDbPort(){
		return properties.getProperty("db.port");
	}
	private String getDbPoolMax(){
		return properties.getProperty("db.pool.max");
	}
	
	private String getPostgresConnectionString(String host, String port, String database){
		StringBuilder b = new StringBuilder();
		b.append("jdbc:postgresql://").append(host).append(":").append(port).append("/").append(database);
		return b.toString();
	}
	
	private String getMySQLConnectionString(String host, String port, String database){
		StringBuilder b = new StringBuilder();
		b.append("jdbc:postgresql://").append(host).append(":").append(port).append("/").append(database);
		return b.toString();
	}
	
	public void createDatabase() throws SQLException{
		DaoManager.createDao(connectionSource, Entry.class);
		TableUtils.createTable(connectionSource, Entry.class);
	}

}
