package com.xt.test;

import com.xt.entity.User;
import com.xt.service.UserService;
import com.xt.service.impl.UserServiceImpl;
import org.junit.Test;

import java.sql.SQLException;

public class UserServiceImplTest {

    private UserService userService = new UserServiceImpl();

    @Test
    public void registerUser() throws SQLException {
        System.out.println(userService.registerUser(new User(null, "sakura", "123456", "sakura@163.com")));
    }

    @Test
    public void login() throws SQLException {
        System.out.println(userService.login(new User(null, "admin", "admin", null)));
    }

    @Test
    public void existsUsername() throws SQLException {
        boolean existsUsername = userService.existsUsername("admin");
        System.out.println(existsUsername);
    }
}