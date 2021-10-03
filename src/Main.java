import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException {

		String url = "jdbc:mysql://localhost:3306/employee";
		String user = "root";
		String password = "password";

		Connection con = DriverManager.getConnection(url, user, password);

		PreparedStatement ps = con.prepareStatement("select * from employee");
		ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			System.out.println(rs.getString("ename"));
		}

		con.close();

	}
}
