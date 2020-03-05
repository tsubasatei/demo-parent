package com.xt.service.impl;

import com.xt.dao.UserDao;
import com.xt.dao.impl.UserDaoImpl;
import com.xt.entity.User;
import com.xt.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean registerUser(User user) throws SQLException {
        int i = userDao.saveUser(user);
        if (i == 1) {
            return true;
        }
        return false;
    }

    @Override
    public User login(User user) throws SQLException {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) throws SQLException {
        User user = userDao.queryUserByUsername(username);
        if (user == null) {
            return false;
        }
        return true;
    }
}
