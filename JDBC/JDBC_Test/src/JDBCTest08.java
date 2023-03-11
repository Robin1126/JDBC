import java.sql.*;
import java.util.ResourceBundle;
import java.util.Scanner;

/*
    用户在控制台输入desc就是降序排列，asc就是升序排列
 */
public class JDBCTest08 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        ResourceBundle resourceBundle = ResourceBundle.getBundle("data");
        try {
            // 建立变量
            Scanner s = new Scanner(System.in);
            System.out.print("请输入desc或者asc：");
            String order = s.nextLine();
            // 1. 注册驱动
            Class.forName(resourceBundle.getString("className"));
            // 2. 建立连接
            conn = DriverManager.getConnection(resourceBundle.getString("url"), resourceBundle.getString("username"), resourceBundle.getString("password"));
            // 3. 获取数据库执行对象
            stmt = conn.createStatement();
            String SQL = "select ename, sal from emp order by sal " + order ;
            // 4. 执行SQL
            rs = stmt.executeQuery(SQL);
            // 5. 处理查询的结果
            while (rs.next()) {
                System.out.println(rs.getString("ename")+", "+rs.getString("sal"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
