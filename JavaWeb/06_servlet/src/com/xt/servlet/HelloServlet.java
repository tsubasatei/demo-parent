package com.xt.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HelloServlet implements Servlet {

    public HelloServlet() {
        System.out.println("HelloServlet 构造器 1 ...");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("HelloServlet init 2 ...");

        // 1、可以获取Servlet 程序的别名servlet-name 的值
        System.out.println("HelloServlet 程序的别名是:" + servletConfig.getServletName()); // HelloServlet
        // 2、获取初始化参数init-param
        System.out.println("初始化参数username的值是：" + servletConfig.getInitParameter("username")); // root
        // 3. 获取ServletContext 对象
        System.out.println(servletConfig.getServletContext()); // org.apache.catalina.core.ApplicationContextFacade@29878ee0
    }


    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    // service 方法是专门用来处理请求和响应的
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("HelloServlet service 被调用了 3");
        // 类型转换（因为它有getMethod()方法）
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 获取请求的方法
        String method = httpServletRequest.getMethod();
        if ("GET".equals(method)) {
            doGet();
        } else if ("POST".equals(method)) {
            doPost();
        }
    }

    /**
     * 做get 请求的操作
     */
    public void doGet() {
        System.out.println("get 请求");
    }

    /**
     * 做post 请求的操作
     */
    public void doPost() {
        System.out.println("post 请求");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("HelloServlet destroy 4 ...");
    }
}
