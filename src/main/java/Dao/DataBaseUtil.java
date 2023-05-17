package Dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataBaseUtil {
	
	Connection connection=null;


	public  Connection getConnector() {
		
		try {
			
			Context initContext = new InitialContext();
			DataSource ds=(DataSource) initContext.lookup("java:comp/env/jdbc/employee");
			connection=ds.getConnection();
			return connection;
		}catch (Exception e) {
			System.out.println("data base error");
			System.out.println(e);
		}
		
		return connection;
	}
	
	public void dbClose() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
