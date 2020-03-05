package com.xt.test;

import com.xt.dao.UserDao;
import com.xt.dao.impl.UserDaoImpl;
import com.xt.entity.User;
import org.junit.Test;

import java.sql.SQLException;

public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void queryUserByUsername () throws SQLException {
        User user = userDao.queryUserByUsername("admin");
        if (null == user) {
            System.out.println("用户名可用");
        } else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword () throws SQLException {
        User user = userDao.queryUserByUsernameAndPassword("admin", "admin");
        if (null == user) {
            System.out.println("用户名或密码不正确，登录失败");
        } else {
            System.out.println("查询成功");
        }
    }
    @Test
    public void saveUser () throws SQLException {
        User user = new User(null, "sanae", "123456", "sanae@163.com");
        int i = userDao.saveUser(user);
        if (i == -1) {
            System.out.println("添加失败");
        } else {
            System.out.println("添加成功");
        }

    }
}
