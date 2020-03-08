package com.xt.service;

import com.xt.entity.Cart;
import com.xt.entity.Order;
import com.xt.entity.OrderItem;

import java.util.List;

public interface OrderService {

    // 生成订单
    String createOrder(Cart cart, Integer userId) throws Exception;

    // 查询全部订单
    List<Order> queryAllOrders() throws Exception;

    // 查询某用户全部订单
    List<Order> queryAllOrdersByUserId(Integer userId) throws Exception;

    // 发货
    boolean sendOrder(String orderId) throws Exception;

    // 查看订单详情
    List<OrderItem> showOrderDetails(String orderId) throws Exception;

    // 签收订单/确认订单
    boolean receiveOrder(String orderId) throws Exception;
}
