package com.xt.dao;

import com.xt.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    int addBook(Book book) throws SQLException;
    int deleteBookById(Integer id) throws SQLException;
    int updateBook(Book book) throws SQLException;
    Book queryBookById(Integer id) throws SQLException;
    List<Book> queryBooks() throws SQLException;
    Integer queryTotal() throws Exception;

    List<Book> page(int begin, int pageSize) throws Exception;

    Integer queryTotalByPrice(int min, int max) throws Exception;

    List<Book> pageByPrice(int begin, int pageSize, int min, int max) throws Exception;
}
