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

		// DBの接続情報
		Connection con = DriverManager.getConnection(url, user, password);

		// prepareStatementメソッドでコンパイルし
		// コンパイル済みのStatementをPreparedStatementで保持
		PreparedStatement ps = con.prepareStatement("select * from employee");
		// executeQueryで実行
		// 実行結果を表で受け取る
		ResultSet rs = ps.executeQuery();

		// nextメソッドで一行ずつ取り出す
		while(rs.next()) {
			// enameをString型で受け取る
			// System.out.println(rs.getString("ename"));
			// System.out.println(rs.getInt("empno"));
			System.out.println(rs.getDate("hiredate"));
		}

		con.close();

	}
}
