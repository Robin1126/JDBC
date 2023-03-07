/*
	注册驱动的另一种写法，使用反射机制，执行静态代码块
*/
import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest02_AnotherWay
{
	public static void main(String[] args) {
		try
		{
			// 1. 注册驱动
			// Driver driver = new com.mysql.jdbc.Driver();
			// DriverManager.registerDriver(driver);
			// 因为参数是一个字符串，字符串可以写到xxx.properties文件中。

			// Class.forName("com.mysql.jdbc.Driver");
			// 使用bundle导入文件
			ResourceBundle resourcebundle = ResourceBundle.getBundle("driver");
			String driver = resourcebundle.getString("driverName");
			Class.forName(driver);

			// 2. 获取连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306","root","woaiky0522");
			System.out.println(conn);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		} 
	}
}