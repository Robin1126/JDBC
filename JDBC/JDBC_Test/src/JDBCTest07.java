import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
/*
    解决SQL注入的问题
    1. 使用PreparedStatement
        只要用户提供的信息不参与SQL语句的编译过程就行了
        PreparedStatement接口继承了java.sql.Statement
        PreparedStatement的原理是，预先对SQL语句的框架进行编译，然后再给SQL语句传值。
    2. 在获取数据库操作对象之前先定义好SQL语句，？占位符的使用
        然后使用命令conn.prepareStatement(SQL)对SQL语句进行框架的预编译
    3. 在执行SQL语句直接写.executeQuery()即可，不需要传入SQL参数了
        注意不能二次传入SQL语句
    4. 对比一下Statement和PreparedStatement
        - Statement存在注入问题
        - PreparedStatement的效率较高
            原因：如果MySQl当中，每一次执行的语句如果完全一样，MySQL是不会编译的，直接执行
            如果我们使用Statement，那么由于每次传入的SQL语句都不相同，每次都要编译
            但是如果我们使用PreparedStatement，那么sql语句永远都不会变，以后只要传入值就可以了
        - PreparedState会在编译阶段进行类型的安全检查，在编译就可能会报错
     因此：一般都会使用PreparedStatement,如果需要SQL进行拼接的话，就需要使用Statement
 */
public class JDBCTest07 {
    public static void main(String[] args) {
        // 初始化一个界面
        Map<String, String> userLoginInfo = initUI();
        // 验证用户名和密码
        boolean loginSuccess = login(userLoginInfo);
        // 最后输出结果
        System.out.println(loginSuccess ? "登录成功" : "登录失败");
    }

    /**
     * 用户登录
     * @param userLoginInfo 用户登录信息
     * @return false 失败， true 成功
     */
    private static boolean login(Map<String, String> userLoginInfo) {
        // JDBC代码
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //单独定义变量
        String loginName = userLoginInfo.get("loginName");
        String loginPwd = userLoginInfo.get("loginPwd");

        // 打标记
        boolean loginSuccess = false;

        try {
            // 1. 注册驱动，使用bundle
            ResourceBundle rb = ResourceBundle.getBundle("data");
            Class.forName(rb.getString("className"));

            // 2. 建立连接
            conn = DriverManager.getConnection(rb.getString("url"),rb.getString("username"),rb.getString("password"));

            // 3. 获取预编译数据库对象
            // SQL语句要提前，并且后面的参数要写成？占位符，一个？将来接收一个值
            // 注意不能'?'作为一个符号
            String sql = "select loginName, loginPwd from t_user where loginName = ? and loginPwd = ? ";
            ps = conn.prepareStatement(sql);
            // 给占位符传值，第一个问号下标为1，然后为2，依次递增
            ps.setString(1,loginName);
            ps.setString(2,loginPwd);
            // setString（下标，String类型变量名）
            //ps.setString(1,userLoginInfo.get("loginName")) 直接使用Map对象的方法找到值

            // 4. 执行sql语句
            rs = ps.executeQuery();


            // 因为Java字符串类型不能直接转换称为sql的字符串类型，所以这里要拼接字符串“+loginName+” 最后是’bubu‘


            // 5. 处理查询结果集合
            // 这里不需要循环，因为如果找到了也是只有一条结果，找到就是true
            if (rs.next()) {
                loginSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

        return loginSuccess;
    }

    /**
     * initUI
     * @return username and password
     */
    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);

        System.out.print("Username: ");
        String loginName = s.nextLine(); // 读取一行字符串

        System.out.print("Password: ");
        String loginPwd = s.nextLine();

        Map<String,String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("loginName", loginName);
        userLoginInfo.put("loginPwd", loginPwd);
        return userLoginInfo;

    }
}
