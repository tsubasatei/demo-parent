package com.xt.dao.impl;

import com.xt.dao.BaseDao;
import com.xt.entity.OrderItem;

import java.util.List;

/**
 * @author xt
 * @create 2020/3/9 2:10
 * @Desc
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public boolean saveOrderItem(OrderItem orderItem) throws Exception {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(),
                        orderItem.getOrderId()) > 0;
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) throws Exception {
        String sql = "select id, `name`,`count`,`price`,`total_price` totalPrice,`order_id` orderId from t_order_item where order_id = ?";
        return queryForList(OrderItem.class, sql, orderId);
    }
}
