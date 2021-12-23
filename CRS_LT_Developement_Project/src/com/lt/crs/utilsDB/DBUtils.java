package com.lt.crs.utilsDB;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 
 * @author sai kumar,Rohan,Diwakar,Abdul,Siva,Nikil
 *
 */
public class DBUtils {

	/**
	 * this method is to read the configuration file
	 *
	 */
	public static Properties loadPropertiesFile() throws Exception {

		Properties prop = new Properties();
		InputStream in = new FileInputStream("config.properties");
		prop.load(in);
		in.close();
		return prop;
	}
	/**
	 * this method is to made connection with database abd return database connection
	 * @return Connection
	 */
	public static Connection getConnection() {

		Connection con = null;

		try {

			Properties prop = loadPropertiesFile();

			String driverClass = prop.getProperty("MYSQLJDBC.driver");
			String url = prop.getProperty("MYSQLJDBC.url");
			String username = prop.getProperty("MYSQLJDBC.username");
			String password = prop.getProperty("MYSQLJDBC.password");

			Class.forName(driverClass);

			con = DriverManager.getConnection(url, username, password);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}