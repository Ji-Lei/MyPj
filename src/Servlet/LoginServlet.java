package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by ${纪雷} on 2019/5/8.
 */

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("数据库驱动程序加载成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库驱动程序加载失败");
        }
    }

    public LoginServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/username?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF8","root","yulei970423L");
            PreparedStatement ps = conn.prepareStatement(
                    "select count(*) from userpass where nameid=?");
            //这个用户名是用户输入的
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //这个密码是数据库里面存的密码，然后你拿这个和输入的对比就可以了

                String password1 = rs.getString("password");
            }
            System.out.println("数据库连接成功!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接失败！");
        }
    }
}
