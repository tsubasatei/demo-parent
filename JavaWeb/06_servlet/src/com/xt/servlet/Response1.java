package com.xt.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Response1 extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 请求重定向的第一种方案：
        // 设置响应状态码302 ，表示重定向，（已搬迁）
//        resp.setStatus(302);
        // 设置响应头，说明新的地址在哪里
//        resp.setHeader("Location", "response2");

        // 请求重定向的第二种方案（推荐使用）：
        resp.sendRedirect("http://localhost:8080/06_servlet/response2");
    }
}
