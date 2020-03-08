package com.xt.dao.impl;

import com.xt.entity.OrderItem;

import java.util.List;

public interface OrderItemDao {
    // 保存订单项
    boolean saveOrderItem(OrderItem orderItem) throws Exception;

    // 根据订单ID查询订单详情
    List<OrderItem> queryOrderItemsByOrderId(String orderId) throws Exception;

}
