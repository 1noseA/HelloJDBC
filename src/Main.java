import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws SQLException {

		// empno = 7369のデータを取得
//		Employee emp = findEmployee(7369);
//		System.out.println(emp);

		// deptno = 10の部署に所属する全員を取得
		// Take1
//		Department dpt = findDeptno(10);
//		System.out.println(dpt);
		// Take2
//		Employee emp = findDeptno(10);
//		System.out.println(emp);
		// Take3
//		List<Employee> list = findDeptno(10);
//		for (Employee emp : list) {
//			System.out.println(emp);
//		}
		List<Employee> list = new ArrayList<>();
		list = findDeptno(10);
		for (Employee emp : list) {
			System.out.println(emp);
		}

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

	private static List<Employee> findDeptno(int deptno) throws SQLException {

		String url = "jdbc:mysql://localhost:3306/employee";
		String user = "root";
		String password = "password";

		Connection con = DriverManager.getConnection(url, user, password);

		// これじゃダメじゃん
		// PreparedStatement ps = con.prepareStatement("select * from department where deptno = ?");
		PreparedStatement ps = con.prepareStatement(
				"select empno, ename, job, hiredate, salary, e.deptno from employee e join department d on e.deptno = d.deptno where e.deptno = ?");
		ps.setInt(1, deptno);
		ResultSet rs = ps.executeQuery();

		List<Employee> list = new ArrayList<>();

		try {
			while (rs.next()) {
//				Department dpt = new Department();
//				dpt.setDeptno(rs.getInt("deptno"));
//				dpt.setDname(rs.getString("dname"));
//				dpt.setLocation(rs.getString("location"));
//				return dpt;
				// List<Employee> list = new ArrayList<>();
				// listに値が入っていない
				// for (Employee emp : list) {
					Employee emp = new Employee();
					emp.setEmpno(rs.getInt("empno"));
					emp.setEname(rs.getString("ename"));
					emp.setJob(rs.getString("job"));
					emp.setHiredate(rs.getDate("hiredate"));
					emp.setSalary(rs.getInt("salary"));
					emp.setDeptno(rs.getInt("deptno"));
					list.add(emp);
				// }
				// return list;
			}
		} finally {
			con.close();
		}

		return list;

		// Take7 returnの場所 return nullって必要？
		// return null;

	}
}
