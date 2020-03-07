package com.xt.service;

import com.xt.entity.Book;
import com.xt.entity.Page;

import java.util.List;

public interface BookService {
    boolean addBook(Book book) throws Exception;
    boolean deleteBookById(Integer id) throws Exception;
    boolean updateBook(Book book) throws Exception;
    Book queryBookById(Integer id) throws Exception;
    List<Book> queryBooks() throws Exception;

    Page<Book> page(int pageNo, int pageSize) throws Exception;

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) throws Exception;
}
