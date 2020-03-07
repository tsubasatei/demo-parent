package com.xt.service.impl;

import com.xt.dao.BaseDao;
import com.xt.dao.BookDao;
import com.xt.dao.impl.BookImpl;
import com.xt.entity.Book;
import com.xt.entity.Page;
import com.xt.service.BookService;

import java.util.List;

public class BookServiceImpl extends BaseDao implements BookService {

    private BookDao bookDao = new BookImpl();

    @Override
    public boolean addBook(Book book) throws Exception {
        int i = bookDao.addBook(book);
        return i == 1;
    }

    @Override
    public boolean deleteBookById(Integer id) throws Exception {
        int i = bookDao.deleteBookById(id);
        return i == 1;
    }

    @Override
    public boolean updateBook(Book book) throws Exception {
        int i = bookDao.updateBook(book);
        return i == 1;
    }

    @Override
    public Book queryBookById(Integer id) throws Exception {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() throws Exception {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) throws Exception {
        Page<Book> page = new Page<>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryTotal();
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);

        // 求总页码
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (pageNo - 1) * pageSize;
        // 求当前页数据
        List<Book> items = bookDao.page(begin, pageSize);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) throws Exception{
        Page<Book> page = new Page<>();
        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryTotalByPrice(min, max);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);

        // 求总页码
        int pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal += 1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);
        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (pageNo - 1) * pageSize;
        // 求当前页数据
        List<Book> items = bookDao.pageByPrice(begin, pageSize, min, max);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }
}
