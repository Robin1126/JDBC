import java.sql.DriverManager;
import java.sql.*;

public class JDBCTest01 {
    public static void main(String[] args) {
			Statement stmt = null;
			Connection conn = null;
         // 3. 获取数据库操作对象
         // 4. 执行sql
          //5. 处理查询结果集
          //6. 释放资源
         //	1. 注册驱动
			try{
				Driver driver = new com.mysql.jdbc.Driver();	
				//Driver driver = new oracle.jdbc.driver.OracleDriver(); Oracle的驱动
				DriverManager.registerDriver(driver);
			// 2. 获取连接
			// url是什么东西
			String url = "jdbc:mysql://127.0.0.1:3306/mydatabase";
			/*
				url：统一资源定位符（网络中某个资源的绝对路径）
				https://www.baidu.com/ 就是一个url
				url包括哪几个部分？
					协议
					IP
					PORT 端口
					资源名
				
				http:// 协议
				182.61.200.7 服务器IP地址
				80 服务器上软件的端口 到这里就已经打开了通道了
				index.html 是服务器上某个资源名

				因此这里
					jdbc:mysql:// 协议
					127.0.0.1 IP地址
					3306 mysql软件的端口 --》这里建立了联系
					mydatabase 要获取的资源是mysql中的mydatabase

				说明 localhost和127.0.0.1都是本机ip地址

				什么是通信协议，有什么用？Kommunikationsprotokoll
					通信之前就提前定好的数据传送格式
					数据包怎么传数据，格式是提前定好的

				oracle的url：
					jdbc:oracle:thin@localhost:1521:orcl
					
			*/
			String user = "root";
			String password = "woaiky0522";
			conn = DriverManager.getConnection(url,user,password); //返回一个connection
			// com.mysql.jdbc.JDBC4Connection@3cb5cdba
			// 顺便已经创建了一个数据库对象conn
			System.out.println("数据库连接对象：" + conn);

			// 3.获取数据库操作对象
			// 使用connection对象下面的createStatement():创建一个statement对象来将SQL语句发送到数据库
			stmt = conn.createStatement();

			// 4.执行SQL语句
				String sql = "insert into dept1(deptno,dname,loc) values(50,'CCP','Peking')";
				int count = stmt.executeUpdate(sql); //statement中的执行sql方法，返回一个int类型的值,专门执行DML语句
				//返回的是数据库中受到影响的记录条数
				System.out.println(count == 1 ? "保存成功！":"保存失败");
			} catch(SQLException e){
				e.printStackTrace();
			} finally {
				// 6. 释放资源
				// 为了保证资源一定释放，在finally语句块中关闭资源
				// 并且遵循从小到大的原则依次关闭
				// stmt是由connection对象创造出来的
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

