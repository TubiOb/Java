package BankApplicationSQLConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectionLoader {
	private static Connection conn;
	public static Statement loadsql() {
		String uname = "root", password = "KiLLingMyDemons#1";
		String url = "jdbc:mysql://localhost:3306/Banking_application";
		try {
			System.out.println("Hello...1");
			Class.forName("com.mysql.cj.jdbc.Driver");
			//			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Hello...2");

			 conn = DriverManager.getConnection(url, uname, password);
			System.out.println("Hello...3");

			//			String query = "insert into master_data values(4,'haleemah',30);";
			Statement st = conn.createStatement();
			//			 st.execute(query);
			//			 System.out.println("Hello...4");
			return st;
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return null;
	}


}