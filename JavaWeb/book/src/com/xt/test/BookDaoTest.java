package com.xt.test;

import com.xt.dao.BookDao;
import com.xt.dao.impl.BookImpl;
import com.xt.entity.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

public class BookDaoTest {

    private BookDao bookDao = new BookImpl();

    @Test
    public void addBook() {
        try {
            bookDao.addBook(new Book(null,"国哥为什么这么帅！", "191125", new
                    BigDecimal(9999),1100000,0,null
            ));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteBookById() throws SQLException {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() throws SQLException {
        bookDao.updateBook(new Book(21,"大家都可以这么帅！", "国哥", new
                BigDecimal(9999),1100000,0,null
        ));
    }

    @Test
    public void queryBookById() throws SQLException {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() throws SQLException {
        bookDao.queryBooks().forEach(System.out::println);
    }
}