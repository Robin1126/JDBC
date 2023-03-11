import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

/*
    批量编辑 alt+shift+insert；
    三个重要代码：
        conn.setAutoCommit(false);
        conn.commit();
        conn.rollback();在异常机制中
 */
public class JDBCTest11 {
    public static void main(String[] args) {
        PreparedStatement ps = null;
        Connection conn = null;

        ResourceBundle rb = ResourceBundle.getBundle("data");

        try {
            // 1. 获取驱动
            Class.forName(rb.getString("className"));
            // 2. 建立连接
            conn = DriverManager.getConnection(rb.getString("url"),rb.getString("username"),rb.getString("password"));
            // 修改手动提交
            conn.setAutoCommit(false); //开启事务
            String sql = "update t_act set balance = ? where actno = ?";
            // 3. 获取数据库操作对象
            ps = conn.prepareStatement(sql); // 预先编译框架
            // 传值
            // 4. 执行SQL
            ps.setDouble(1,10000);
            ps.setInt(2,111);
            int count = ps.executeUpdate();
            // 重新传值
//            String s = null;
//            s.toString();

            ps.setDouble(1,10000);
            ps.setInt(2,222);
            count += ps.executeUpdate();
            System.out.println(count==2?"success":"fail");

            conn.commit(); //能走到这里说明，以上没有问题，提交事务




        } catch (ClassNotFoundException e) {
            if (conn != null) {
                try {
                    // 如果出现了异常，而且conn有了变动，则回滚事务
                    conn.rollback();
                } catch (SQLException ex) {
                    e.printStackTrace();
                }
            }
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