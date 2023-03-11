import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * PreparedStatement完成INSERT DELETE UPDATE
 * 必要的知识：in.nextLine();不能放在in.nextInt();代码段后面
 *
 * 否则in.nextLine();会读入"\n"字符，但"\n"并不会成为返回的字符
 */
public class JDBCTest09 {
    public static void main(String[] args) {
        PreparedStatement ps = null;
        Connection conn = null;

        ResourceBundle rb = ResourceBundle.getBundle("data");
        try {
            // 1. 获取驱动
            Class.forName(rb.getString("className"));
            // 2. 建立连接
            conn = DriverManager.getConnection(rb.getString("url"),rb.getString("username"),rb.getString("password"));

            // 3. 获取数据库操作对象
//            String sql = "delete from dept1 where deptno = ? ";
            String sql = "insert into dept1 (deptno, dname, loc) values(?, ?, ?)";
            ps = conn.prepareStatement(sql);
            //传入值
            Scanner s = new Scanner(System.in);

            System.out.print("deptno: ");
            int deptno = s.nextInt(); // 读取一行字符串,按下回车后，\n没有被接收如nextInt，而是跑到了下一个nextLine当中
            // 因此要设置一个nextLine()来接收这个\n
            String str = s.nextLine();

            System.out.print("dname: ");
            String dname = s.nextLine();

            System.out.print("loc:");
            String loc = s.nextLine();

            ps.setInt(1,deptno);
            ps.setString(2,dname);
            ps.setString(3,loc);

            int count = ps.executeUpdate();
            //
            System.out.println(count == 1 ?"操作成功" : "操作失败");
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
