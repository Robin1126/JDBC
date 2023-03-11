import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * JDBC事务机制 transaction
 *  1. JDBC中的事务是自动提交的，什么是自动提交
 *      只要执行任意一条DML语句，则自动提交一次
 *      肯定是不符合我们的业务需求的，因为日常都是多个DML语句同时成功或者同时失败
 *  2. 以下程序先来验证JDBC的事务是否自动提交
 *      测试结果：JDBC只要执行一次sql就提交一次
 */
public class JDBCTest10 {
    public static void main(String[] args) {
        PreparedStatement ps = null;
        Connection conn = null;

        ResourceBundle rb = ResourceBundle.getBundle("data");
        try {
            // 1. 获取驱动
            Class.forName(rb.getString("className"));
            // 2. 建立连接
            conn = DriverManager.getConnection(rb.getString("url"),rb.getString("username"),rb.getString("password"));
            String sql = "update dept1 set dname = ? where deptno = ?";
            // 3. 获取数据库操作对象
            ps = conn.prepareStatement(sql); // 预先编译框架
            // 传值
            // 4. 执行SQL
            ps.setString(1,"研发部");
            ps.setInt(2,30);
            int count = ps.executeUpdate();
            System.out.println(count);
            // 重新传值
            ps.setString(1,"魔法部");
            ps.setInt(2,20);
            count = ps.executeUpdate();
            System.out.println(count);





        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
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
