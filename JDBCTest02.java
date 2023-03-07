/*
	JDBC完成delete
*/
import java.sql.*;

public class JDBCTest02 {
	public static void main(String[] args) {
		// 1. 注册驱动
		Statement stmt = null;
		Connection conn = null;
		try
		{
			// 驱动 driver
			Driver driver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(driver);
			
			// 2. 获取连接Connection中的createStatement()方法
			String url = "jdbc:mysql://localhost:3306/mydatabase";
			String user = "root";
			String password = "woaiky0522";
			conn = DriverManager.getConnection(url,user,password);
			
			// 3. 获取数据库操作对象
			stmt = conn.createStatement();
			
			// 4. 执行sql语句
			// JDBC中的SQL语句不需要用分号
			String sql = "delete from dept1 where deptno = 50";
			int count = stmt.executeUpdate(sql);
			System.out.println(count == 1 ? "删除成功！":"删除失败");
			// 5. pass

			
		} catch (SQLException e) 
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
		
		
		// 4. 执行sql语句
		// 5. 处理查询返回值
		// 6. 释放资源
	
	}
}