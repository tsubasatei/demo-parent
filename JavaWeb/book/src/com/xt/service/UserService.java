package com.xt.service;

import com.xt.entity.User;

import java.sql.SQLException;

public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    boolean registerUser(User user) throws SQLException;
    /**
     * 登录
     * @param user
     * @return 如果返回null，说明登录失败，返回有值，是登录成功
     */
    User login(User user) throws SQLException;
    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true 表示用户名已存在，返回false 表示用户名可用
     */
    boolean existsUsername(String username) throws SQLException;
}
