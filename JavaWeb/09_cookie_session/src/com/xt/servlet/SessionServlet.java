package com.xt.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionServlet extends BaseServlet{

    // 往Session 中保存数据
    protected void createSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        session.setAttribute("key1", "value");
        boolean isNew = session.isNew();
        // 获取Session会话的唯一标识 id
        String id = session.getId();

        resp.getWriter().write("得到的Session，它的id是：" + id + " <br /> ");
        resp.getWriter().write("这个Session是否是新创建的：" + isNew + " <br /> ");
    }

    // 设置Session 域中的数据
    protected void setSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.getSession().setAttribute("key1", "newValue1");
        resp.getWriter().write("已经往Session中保存了数据");
    }

    // 获取Session 域中的数据
    protected void getSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        Object attribute = req.getSession().getAttribute("key1");
        resp.getWriter().write("从Session 中获取出key1 的数据是：" + attribute);
    }

    // 默认存活时间
    protected void defaultSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        int maxInactiveInterval = req.getSession().getMaxInactiveInterval();
        resp.getWriter().write("默认超时时长(秒)：" + maxInactiveInterval);
    }


    protected void life3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        // 设置当前Session3 秒后超时
        req.getSession().setMaxInactiveInterval(3);
        resp.getWriter().write("当前Session 已经设置为3 秒后超时");
    }

    // 立即删除
    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        // 让Session 会话马上超时
        req.getSession().invalidate();
        resp.getWriter().write("Session 已经设置为超时（无效）");
    }
}
