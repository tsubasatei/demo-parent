package com.xt.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

// 监听器
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("MyServletContextListener...#contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("MyServletContextListener...#contextDestroyed");
    }
}
