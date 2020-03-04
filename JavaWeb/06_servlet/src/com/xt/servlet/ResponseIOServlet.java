package com.xt.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseIOServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 解决响应中文乱码方案一（不推荐使用）
        // 设置服务器字符集为UTF-8
        resp.setCharacterEncoding("UTF-8");
        // 通过响应头，设置浏览器也使用UTF-8 字符集
        resp.setHeader("Content-Type", "text/html; charset=UTF-8");


        // 解决响应中文乱码方案二（推荐）：
        // 它会同时设置服务器和客户端都使用UTF-8 字符集，还设置了响应头
        // 此方法一定要在获取流对象之前调用才有效
        resp.setContentType("text/html; charset=UTF-8");


        // 要求： 往客户端回传字符串数据。
        PrintWriter writer = resp.getWriter();
        writer.write("response's content! 响应结束");
    }
}
