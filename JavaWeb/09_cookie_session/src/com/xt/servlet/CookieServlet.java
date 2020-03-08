package com.xt.servlet;

import com.xt.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends BaseServlet{

    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        //1 创建Cookie 对象
        Cookie cookie = new Cookie("key1", "value1");
        //2 通知客户端保存Cookie
        resp.addCookie(cookie);
        resp.getWriter().write("Cookie创建成功");
    }

    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = CookieUtils.findCookie("key1", req.getCookies());
        if (cookie != null) {
            // getName 方法返回Cookie 的key（名）
            // getValue 方法返回Cookie 的value 值
            resp.getWriter().write("Cookie[" + cookie.getName() + "=" + cookie.getValue() + "] <br/>");
        }
    }

    protected void setCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //方案一：
        // 1、先创建一个要修改的同名的Cookie 对象
        // 2、在构造器，同时赋于新的Cookie 值。
        // 3、调用response.addCookie( Cookie ); 通知客户端保存修改
//        Cookie cookie = new Cookie("key1", "newValue1");
//        resp.addCookie(cookie);
//        resp.getWriter().write("构造器更新cookie");

//        方案二：
//        1、先查找到需要修改的Cookie 对象
//        2、调用setValue()方法赋于新的Cookie 值。
//        3、调用response.addCookie()通知客户端保存修改

        Cookie cookie = CookieUtils.findCookie("key1", req.getCookies());
        if (cookie != null) {
            cookie.setValue("newCookie2");
            resp.addCookie(cookie);
        }
    }

    // 默认存活时间
    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("defaultLife", "defaultLife");
        int maxAge = cookie.getMaxAge(); // -1
        resp.addCookie(cookie);
        resp.getWriter().write("maxAge = " + maxAge);
    }

    // 立即删除
    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        // 先找到你要删除的Cookie 对象
        Cookie cookie = CookieUtils.findCookie("key1", req.getCookies());
        if (cookie != null) {
            // 调用setMaxAge(0); 设置存活时间
            cookie.setMaxAge(0); // 表示马上删除，都不需要等待浏览器关闭
            // 调用response.addCookie(cookie);
            resp.addCookie(cookie);
            resp.getWriter().write("key1 的Cookie 已经被删除");
        }
    }

    // 设置存活1 个小时的Cooie
    protected void life3600(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        Cookie cookie = new Cookie("life3600", "life3600");
        cookie.setMaxAge(60 * 60);
        resp.addCookie(cookie);
        resp.getWriter().write("已经创建了一个存活一小时的Cookie");
    }

    protected void testPath(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        Cookie cookie = new Cookie("path1", "path1");
        // getContextPath() ===>>>> 得到工程路径
        cookie.setPath( req.getContextPath() + "/abc" ); // ===>>>> /工程路径/abc
        resp.addCookie(cookie);
        resp.getWriter().write("创建了一个带有Path 路径的Cookie");
    }
}
