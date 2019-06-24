package com.quinnox.dbauto.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateConnection {
	private static final long serialVersionUID = 1L;
	static final String db_url = "url to your db";
	static final String userName = "username";
	static final String password = "password";
	static Connection connection;

	public static Connection createcon() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(db_url, userName, password);
		} catch (ClassNotFoundException | SQLException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
		
		return connection;
	}

}
