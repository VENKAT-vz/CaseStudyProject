package casestudy.venkatesh.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionFactory {
	public static Connection getConnection() {
		Connection myConnection = null;
	    String url = "jdbc:mysql://localhost:3306/casestudy_virtualartgallery?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
		String username = "root";
		String password = "#8Rcgvjgvu962";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			myConnection = DriverManager.getConnection(url, username, password );
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return myConnection;
 
	}

}
