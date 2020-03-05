package com.xt.test;

import com.xt.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class JdbcUtilsTest {

    @Test
    public void getConnection() throws Exception {
        for (int i = 0; i < 100; i++) {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection + " " + i);
            JdbcUtils.close(connection);
        }
    }

}