package com.xt.servlet;


import com.xt.entity.Cart;
import com.xt.entity.Order;
import com.xt.entity.User;
import com.xt.service.OrderService;
import com.xt.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();

    // 生成订单
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 先获取Cart 购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        if (cart.getItems() == null) {
            resp.sendRedirect(req.getHeader("Referer"));
        }
        String order = orderService.createOrder(cart, loginUser.getId());
        req.getSession().setAttribute("orderId", order);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    // 查看所有订单
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取Userid
        User loginUser = (User) req.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        List<Order> orders = orderService.queryAllOrdersByUserId(loginUser.getId());
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);

    }

    // 发货
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    // 查看订单详情
    protected void showOrderDetails(HttpServletRequest req, HttpServletResponse resp) throws Exception {

    }

    // 查看我的订单
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Order> orders = orderService.queryAllOrders();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    // 签收订单
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
