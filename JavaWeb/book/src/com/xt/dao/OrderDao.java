package com.xt.dao;

import com.xt.entity.Order;

import java.util.List;

public interface OrderDao {

    // 保存订单
    boolean saveOrder(Order order) throws Exception;

    // 查询全部订单
    List<Order> queryOrders() throws Exception;

    // 修改订单状态
    boolean updateStatus(String orderId, Integer status) throws Exception;

    // 根据用户Id查询订单信息
    List<Order> queryOrdersByUserId(Integer id) throws Exception;
}
