package com.xt.servlet;

import com.xt.entity.User;
import com.xt.service.UserService;
import com.xt.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 注册
 */
public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // 2、检查验证码是否正确=== 写死,要求验证码为:abcde
        if (code != null && code.equals("abcde")) {
            // 3、检查用户名是否可用
            try {
                if (userService.existsUsername(username)) {
                    System.out.println("用户名[" + username + "]已存在!");
                    // 跳回注册页面
                    req.getRequestDispatcher("/pages/user/register.html").forward(req, resp);
                } else {
                    // 可用 调用 Service 保存到数据库
                    userService.registerUser(new User(null, username, password, email));
                    // 跳到注册成功页面regist_success.html
                    req.getRequestDispatcher("/pages/user/regist_success.html").forward(req, resp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/register.html").forward(req, resp);
        }
    }
}
