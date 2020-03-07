package com.xt.test;

import com.xt.entity.Book;
import com.xt.service.BookService;
import com.xt.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() throws Exception {
        bookService.addBook(new Book(null,"国哥在手，天下我有！", "1125", new BigDecimal(1000000),
                100000000, 0, null));
    }

    @Test
    public void deleteBookById() throws Exception {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() throws Exception {
        bookService.updateBook(new Book(22,"社会我国哥，人狠话不多！", "1125", new BigDecimal(999999),
                10, 111110, null));
    }

    @Test
    public void queryBookById() throws Exception {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() throws Exception {
        bookService.queryBooks().forEach(System.out::println);
    }
}