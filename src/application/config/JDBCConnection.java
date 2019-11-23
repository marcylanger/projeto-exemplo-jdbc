package application.config;

import java.sql.*;

public class JDBCConnection {
	
	public static Connection conn; 
	public static void JDBCConnect() {
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection
					("jdbc:postgresql://localhost:5432/base_vendas", 
							"postgres", "root");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
