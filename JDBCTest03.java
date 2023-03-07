/*
	使用JDBC完成update操作
*/
import java.sql.*;

public class JDBCTest03
{
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		try
		{
			// 1. 注册驱动
			Driver driver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(driver);

			// 2. 获取连接
			String url = "jdbc:mysql://127.0.0.1:3306/mydatabase";
			String username = "root";
			String password = "woaiky0522";
			conn = DriverManager.getConnection(url, username, password);
			
			// 3. 创建数据库操作对象
			stmt = conn.createStatement();

			// 4. 执行SQL语句
			String sql = "update dept1 set dname = '销售部', loc = '韶关' where deptno = 30";
			int count = stmt.executeUpdate(sql);
			System.out.println(count == 1 ? "update成功":"update失败");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} finally {
			try
			{
				if(stmt != null) {
					stmt.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			try
			{
				if(conn != null) {
					conn.close();
				}
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

	}
}