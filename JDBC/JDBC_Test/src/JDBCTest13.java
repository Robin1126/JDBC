/*
    这个程序负责开启一个事务，专门进行查询，并且使用行级锁，锁住相关的记录
 */

import utils.DButil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTest13 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DButil.getConnection();
            conn.setAutoCommit(false); //开启事务

            String sql = "select ename,job,sal from emp1 where job = ? for update ";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"MANAGER");

            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("ename")+", "+ rs.getString("job") + ", " + rs.getDouble("sal"));
            }



            conn.commit();//事务的结束
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
            DButil.close(rs,ps,conn);
        }
    }
}
