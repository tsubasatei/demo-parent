package com.xt.servlet;

import com.xt.entity.Book;
import com.xt.entity.Cart;
import com.xt.entity.CartItem;
import com.xt.service.BookService;
import com.xt.service.impl.BookServiceImpl;
import com.xt.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();


    // 加入购车
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 获取请求的参数商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // 调用bookService.queryBookById(id):Book 得到图书的信息
        Book book = bookService.queryBookById(id);
        if (book == null) {
            System.out.println("图书不存在编号" + id);
            return;
        }
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        // 调用Cart.addItem(CartItem);添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);

        System.out.println("请求头Referer的值：" + req.getHeader("Referer"));

        // 最后一个添加的shangpin
        req.getSession().setAttribute("lastName", cartItem.getName());

        // 重定向回原来商品所在的地址页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    // 删除商品项
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            cart.deleteItem(id); // 删除了购物车商品项
            resp.sendRedirect(req.getHeader("Referer")); // 重定向回原来购物车展示页面
        }
    }

    // 清空购物车
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer")); // 重定向回原来购物车展示页面
        }
    }

    // 修改商品数量
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        // 获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            cart.updateCount(id, count);
            resp.sendRedirect(req.getHeader("Referer")); // 重定向回原来购物车展示页面
        }
    }
}
