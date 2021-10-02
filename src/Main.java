import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException {

		String url = "jdbc:mysql://localhost:3306/employee";
		String user = "root";
		String password = "password";

		Connection con = DriverManager.getConnection(url, user, password);
		con.close();

	}
}
