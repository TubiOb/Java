package BankApplicationSQLConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class BankAppConnectionLoader {

	public static Statement loadsql() {
		String username = "root", password = "KiLLingMyDemons#1";
		String url = "jdbc:mysql://localhost:3306/Banking_Application";
		
		try {
			System.out.println("Hello...1");
			// register driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Hello...2");
		
		// load driver
		Connection conn = DriverManager.getConnection(url, username, password);
		System.out.println("Hello...3");
		Statement st = conn.createStatement();
		
		return st;
		
		}
		catch (Exception e) {
			System.out.println("There was an error..." + e.getMessage());
		}
		return null;
	}
}
