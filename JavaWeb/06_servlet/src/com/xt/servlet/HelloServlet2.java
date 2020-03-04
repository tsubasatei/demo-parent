package com.xt.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet2 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("重写了init初始化方法，做了一些工作");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet2 doPost()");
        ServletConfig servletConfig = getServletConfig();
        System.out.println(servletConfig);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet2 doGet()");

        ServletConfig servletConfig = getServletConfig();
        System.out.println(servletConfig);
        System.out.println("初始化参数username的值是：" + servletConfig.getInitParameter("username")); // null
        System.out.println("初始化参数username的值是：" + servletConfig.getInitParameter("username2")); // root2
        // 3. 获取ServletContext 对象
        System.out.println(servletConfig.getServletContext()); // org.apache.catalina.core.ApplicationContextFacade@5ed78154
    }
}
