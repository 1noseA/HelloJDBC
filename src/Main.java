import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws SQLException {

		Employee emp = findEmployee(7369);
		System.out.println(emp);

	}

	private static Employee findEmployee(int empno) throws SQLException{

		String url = "jdbc:mysql://localhost:3306/employee";
		String user = "root";
		String password = "password";

		// DBの接続情報
		Connection con = DriverManager.getConnection(url, user, password);

		// prepareStatementメソッドでコンパイルし
		// コンパイル済みのStatementをPreparedStatementで保持
		PreparedStatement ps = con.prepareStatement("select * from employee where empno = ?");
		ps.setInt(1, empno);
		// executeQueryで実行
		// 実行結果を表で受け取る
		ResultSet rs = ps.executeQuery();

		// nextメソッドで一行ずつ取り出す
		// while(rs.next()) {
		try {
			if (rs.next()) {
				Employee emp = new Employee();
				emp.setEmpno(rs.getInt("empno"));
				emp.setEname(rs.getString("ename"));
				emp.setJob(rs.getString("job"));
				emp.setHiredate(rs.getDate("hiredate"));
				emp.setSalary(rs.getInt("salary"));
				emp.setDeptno(rs.getInt("deptno"));
				return emp;
			}
		} finally {
			con.close();
		}

		return null;

	}
}
