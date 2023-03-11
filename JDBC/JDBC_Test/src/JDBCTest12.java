import utils.DButil; //当如当前module里的包

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
    两个任务
        1. 测试DButil
        2. 模糊查询的写法
 */
public class JDBCTest12 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // 获取连接
            conn = DButil.getConnection();
            // 获取预编译的数据库操作对象
            // 先写SQL语句, 查询员工字母第二个是a的员工名字
            String sql = "select ename from emp where ename like ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1,"_A%");
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("ename"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.close(rs,ps,conn);
        }
    }
}
