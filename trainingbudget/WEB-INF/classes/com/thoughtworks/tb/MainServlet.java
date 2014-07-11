package com.thoughtworks.tb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by hjli on 7/9/14.
 */
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        //super.doPost(req, resp);
        response.setContentType("text/html;charset=GBK");
        // 浏览器显示的编码，默认的是iso
        response.setCharacterEncoding("GBK");
        // 浏览器到数据库取数据时候使用的编码
        request.setCharacterEncoding("GBK");

        String name = request.getParameter("name"); //获得用户姓名
        String sid = request.getParameter("id");   //获得用户ID
        // int id=1;
        int left = 2000;   //初始金额
        String actualName = "";
        // if(sid!=null&&sid!="")
        //     id=Integer.parseInt(sid);
        Connection con = DataManager.getConnection();
        Statement statement = DataManager.getStatement(con);
        String userSql = "select * from employee where uid = '" + sid + "';"; //查询用户信息
        ResultSet userResult = DataManager.getRs(statement, userSql);
        String errorStr = "";
        try {
            if (userResult.next()) {
                actualName = userResult.getString("uname");
                if (!actualName.equals(name))
                    errorStr = name + " and " + sid + "not match";  //id and name not match

                userResult.close();

                String sql = "select * from budgetTable where uid = '" + sid + "';"; //查询消费记录
                ResultSet result = DataManager.getRs(statement, sql);

                if (result != null) {
                    // try {
                    while (result.next()) {

                        left -= result.getInt("cost");
                        // query += result.getInt("cost") + " ";
                        if (left <= 0) { //余额不能出现负数
                            left = 0;
                            break;
                        }

                    }
                    result.close();
                    statement.close();
                    con.close();
                }
            } else {
                errorStr = sid + " not exist ";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (errorStr != "" && !errorStr.equals("")) {
            // PrintWriter pw  = response.getWriter();//得到一个输出流
            //写给Client端一个简单网页信息
            // pw.println("<html><head></head><body>" + errorStr+ "</body></html>");
            // pw.flush();
            // pw.close();
            request.setAttribute("errorStr", errorStr); //错误信息
            request.getRequestDispatcher("index.jsp").forward(request, response); //查询出错回到初始页面

        } else {
            request.setAttribute("left", left); //余额
            request.setAttribute("name", actualName);//姓名
            request.setAttribute("id", sid);//ID号
            request.getRequestDispatcher("showBalance.jsp").forward(request, response); //页面跳转
        }

    }
}
