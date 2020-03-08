package com.xt.dao.impl;

import com.xt.dao.BaseDao;
import com.xt.dao.OrderDao;
import com.xt.entity.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public boolean saveOrder(Order order) throws Exception {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        int update = update(sql,
                order.getOrderId(),
                order.getCreateTime(),
                order.getPrice(),
                order.getStatus(),
                order.getUserId());
        return update == 1;
    }

    @Override
    public List<Order> queryOrders() throws Exception {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order";
        return queryForList(Order.class, sql);
    }

    @Override
    public boolean updateStatus(String orderId, Integer status) throws Exception {
        String sql = "update t_order set status = ? where order_id = ?";
        return update(sql, status, orderId) == 1;
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer id) throws Exception {
        String sql = "select `order_id` orderId,`create_time` createTime,`price`,`status`,`user_id` userId from t_order where user_id = ?";
        return queryForList(Order.class, sql, id);
    }
}
