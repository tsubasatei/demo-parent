package com.xt.dao.impl;

import com.xt.dao.BaseDao;
import com.xt.dao.BookDao;
import com.xt.entity.Book;

import java.sql.SQLException;
import java.util.List;

public class BookImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) throws SQLException {
        String sql = "insert into t_book(`name` , `author` , `price` , `sales` , `stock` , `img_path`) " +
                "values(?, ?, ?, ?, ?, ?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) throws SQLException {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) throws SQLException {
        String sql = "update t_book set name = ?, " +
                "author = ?, " +
                "price = ?, " +
                "sales = ?, " +
                "stock = ? " +
                "where id = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) throws SQLException {
        String sql = "select id, name, author, price, sales, stock, img_path from t_book where id = ?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() throws SQLException {
        String sql = "select id, name, author, price, sales, stock, img_path from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryTotal() throws Exception {
        String sql = "select count(*) from t_book";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> page(int begin, int pageSize) throws Exception{
        String sql = "select id, name, author, price, sales, stock, img_path from t_book limit ?, ?";
        return queryForList(Book.class, sql, begin, pageSize);
    }

    @Override
    public Integer queryTotalByPrice(int min, int max) throws Exception {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> pageByPrice(int begin, int pageSize, int min, int max) throws Exception {
        String sql = "select id, name, author, price, sales, stock, img_path from t_book where price between ? and ? limit ?, ?";
        return queryForList(Book.class, sql, min, max, begin, pageSize);
    }
}
