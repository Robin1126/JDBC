/*
	处理查询结果集	
*/
import java.util.ResourceBundle;
import java.sql.*;

public class JDBCTest04 {
	public static void main (String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			// 1. 注册驱动
			// 导入配置文件
			ResourceBundle rb = ResourceBundle.getBundle("driver");
			Class.forName(rb.getString("driverName"));
			// 2. 获取连接
			// 输入url，username，password
			conn = DriverManager.getConnection(rb.getString("url"),rb.getString("username"),rb.getString("password"));
			// 3. 获取数据库操作对象
			stmt = conn.createStatement();
			// 4. 执行sql语句
			String sql = "select empno, ename, sal from emp";
			// executeUpdatee = insert/delete/update
			// executeQuery = select
			rs = stmt.executeQuery(sql); // 专门执行DQL语句
			// 5. 处理查询结果集 ResultSet
			// 查完以后rs代表的是表中的数据，一行一行的
			// 最初光标指向第一行的上一行，next()如果有，就返回true
			// next()往下走一行
			System.out.println(rs.next());
			while (rs.next())
			{
				// 取数据？ String rs.getString(int i )方法的特点: 不管你存的是什么类型，都取出来字符串，i表示第几列
				// JDBC下标从1开始
				// 如何知道有多少列？
				System.out.println(rs.getInt("empno") + "," + rs.getString("ename") + "," + rs.getDouble("sal"));
				// 这个getString里面的列名是查询语句最后的表头，如果empno as a，最后getString就是用a
				// 一共4种情况
				// 1.getString() 2.getDouble()等指定数据类型 3.getString(第几列) 4.getString(列名)
			}
	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}finally {
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
					if (stmt != null)
			{
				try
				{
					stmt.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
					if (conn != null)
			{
				try
				{
					conn.close();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}



		}
		
		
		

	}
}

