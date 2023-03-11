import utils.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
    这个程序负责修改
 */
public class JDBCTest13_1 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        // DML是不需要ResultSet的
        try {
            conn = DButil.getConnection();
            conn.setAutoCommit(false);

            String sql = "update emp1 set sal = sal * 1.1 where job = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"MANAGER");
            int count = ps.executeUpdate(); // 返回更新的记录条数
            System.out.println(count);

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    e.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DButil.close(null,ps,conn);
        }
    }
}
