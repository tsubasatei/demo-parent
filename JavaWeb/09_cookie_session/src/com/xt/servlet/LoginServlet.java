package com.xt.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends BaseServlet{

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("admin".equals(username) && password.equals("123456")) {
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60 * 60 * 24 * 7); // 当前Cookie 一周内有效
            resp.addCookie(cookie);
            System.out.println("登录 成功");
        } else {
            System.out.println("登录 失败");
        }
    }
}
