import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

/*
   实现功能：
    1. 需求：模拟用户登录功能的实现
    2. 业务描述：程序运行的时候，提供一个输入的入口,可以让用户输入用户名和密码
                用户输入用户名和密码之后，提交信息，java程序收集到用户信息
                java程序连接数据库验证用户名和密码是否合法
                合法：显示登录成功
                不合法：显示登录失败
    3. 实现功能需要有一个数据的准备：
        实际开发种，表的设计会使用专业的建模工具，PowerDesigner
        使用PD工具来进行数据库表的设计。(使用PD工具创建一个user_login.sql脚本)
    4. 当前存在的问题
    Username: fdsa
    Password: fdsa 'or'1'='1
    登录成功
    这种现象被称为SQL注入，黑客经常使用
    5. 导致SQL注入的根本原因是什么？
    拼接了一个or 1=1条件被当作sql语句编译进去了，正好完成了sql语句的拼接
    用户输入的信息中含有sql语句的关键字，进而达到sql注入


 */
public class JDBCTest06 {
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
        Statement stmt = null;
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

            // 3. 获取数据库对象
            stmt = conn.createStatement();

            // 4. 执行sql语句

            String sql = "select loginName, loginPwd from t_user where loginName='"+loginName+"' and loginPwd ='"+loginPwd+"' ";
            // 因为Java字符串类型不能直接转换称为sql的字符串类型，所以这里要拼接字符串“+loginName+” 最后是’bubu‘
            rs = stmt.executeQuery(sql);

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

        System.out.print("eingabe:");
        String str = s.next();
        System.out.println(str);

        Map<String,String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("loginName", loginName);
        userLoginInfo.put("loginPwd", loginPwd);
        return userLoginInfo;

    }


}
