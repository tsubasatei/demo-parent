package com.xt.dao;

import com.xt.entity.User;

import java.sql.SQLException;

public interface UserDao {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 如果返回null,说明没有这个用户。反之亦然
     */
    User queryUserByUsername(String username) throws SQLException;
    /**
     * 根据用户名和密码查询用户信息
     * @param username
     * @param password
     * @return 如果返回null,说明用户名或密码错误,反之亦然
     */
    User queryUserByUsernameAndPassword(String username,String password) throws SQLException;
    /**
     * 保存用户信息
     * @param user
     * @return 返回-1 表示操作失败，其他是sql 语句影响的行数
     */
    int saveUser(User user) throws SQLException;
}
