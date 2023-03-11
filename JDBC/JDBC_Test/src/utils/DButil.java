package utils;

import java.sql.*;
import java.util.ResourceBundle;

/*
    封装工具类包，减少代码的重复写入
 */
public class DButil {
    static {
        // 只要加载这个类的时候，就注册驱动~ 只要注册一次就可以了！！
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    //工具类的构造方法都是私有的，防止new对象，只能通过类调用
    private DButil() {}

    /**
     * 获取连接对象Connection
     * @return Connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        ResourceBundle rb = ResourceBundle.getBundle("data");
        return DriverManager.getConnection(rb.getString("url"),rb.getString("username"),rb.getString("password"));
    }

    /**
     * 关闭资源方法
     * @param rs ResultSet
     * @param ps PreparedStatement
     * @param conn Connection
     */
    public static void close(ResultSet rs, Statement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
