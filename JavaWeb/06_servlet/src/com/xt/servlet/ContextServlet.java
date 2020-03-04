package com.xt.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、获取web.xml 中配置的上下文参数context-param
        ServletContext context = getServletConfig().getServletContext(); // org.apache.catalina.core.ApplicationContextFacade@19cbd95e
        System.out.println(context);
        String name = context.getInitParameter("name");
        System.out.println("context-param 参数username 的值是:" + name);

        // 2、获取当前的工程路径，格式: /工程路径
        System.out.println( "当前工程路径:" + context.getContextPath() ); // /06_servlet
        // 3、获取工程部署后在服务器硬盘上的绝对路径
        /**
         * / 斜杠被服务器解析地址为:http://ip:port/工程名/ 映射到IDEA 代码的web 目录<br/>
         */
        System.out.println("工程部署的路径是:" + context.getRealPath("/")); // D:\ideaProjects\demo-parent\out\artifacts\06_servlet_war_exploded\
        context.setAttribute("key1", "value1");
        System.out.println("==============");

        // 获取ServletContext 对象
        ServletContext context2 = getServletContext();
        System.out.println(context2);
        System.out.println("保存之前: Context 获取key1 的值是:" + context.getAttribute("key1"));
        context.setAttribute("key1", "value1");
        System.out.println("Context2 中获取域数据key1 的值是:"+ context2.getAttribute("key1"));
    }
}
