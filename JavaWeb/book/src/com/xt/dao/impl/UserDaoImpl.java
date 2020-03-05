package com.xt.dao.impl;

import com.xt.dao.BaseDao;
import com.xt.dao.UserDao;
import com.xt.entity.User;

import java.sql.SQLException;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) throws SQLException {
        String sql = "select id, username, password, email from t_user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "select id, username, password, email from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public int saveUser(User user) throws SQLException {
        String sql = "insert into t_user(username, password, email) values (?, ?, ?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());

    }
}
