package com.xt.servlet;

import com.xt.entity.Book;
import com.xt.entity.Page;
import com.xt.service.BookService;
import com.xt.service.impl.BookServiceImpl;
import com.xt.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1 通过BookService 查询全部图书
            List<Book> books = bookService.queryBooks();
            // 2 把全部图书保存到Request 域中
            req.setAttribute("books", books);
            //
            // 3、请求转发到/pages/manager/book_manager.jsp 页面
            req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        int pageTotalCount = WebUtils.parseInt(req.getParameter("pageTotalCount"), 0);
        if (pageTotalCount % pageNo == 0) {
            pageNo += 1;
        }
        Book book = WebUtils.copyParam2Bean(new Book(), req.getParameterMap());
        try {
            boolean flag = bookService.addBook(book);
            if (flag) {
                resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
            } else {
                req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNo = req.getParameter("pageNo");
        Book book = WebUtils.copyParam2Bean(new Book(), req.getParameterMap());
        try {
            boolean flag = bookService.updateBook(book);
            if (flag) {
                resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
            } else {
                req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageNo = req.getParameter("pageNo");
        // 1、获取请求的参数id，图书编程
        String id = req.getParameter("id");
        int i = WebUtils.parseInt(id, 0);
        try {
            // 2、调用bookService.deleteBookById();删除图书
            boolean flag = bookService.deleteBookById(i);
            if (flag) {
                // 3、重定向回图书列表管理页面
                resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数图书编号
        String id = req.getParameter("id");
        int i = WebUtils.parseInt(id, 0);
        //2 调用bookService.queryBookById 查询图书
        try {
            Book book = bookService.queryBookById(i);
            //3 保存到图书到Request 域中
            req.setAttribute("book", book);
            //4 请求转发到。pages/manager/book_edit.jsp 页面
            req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 分页
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1 获取请求的参数pageNo 和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用BookService.page(pageNo，pageSize)：Page 对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3 保存Page 对象到Request 域中
        req.setAttribute("page", page);
        //4 请求转发到pages/manager/book_manager.jsp 页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }


}
