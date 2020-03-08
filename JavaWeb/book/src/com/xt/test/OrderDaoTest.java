package com.xt.test;

import com.xt.dao.OrderDao;
import com.xt.dao.impl.OrderDaoImpl;
import com.xt.dao.impl.OrderItemDao;
import com.xt.dao.impl.OrderItemDaoImpl;
import com.xt.entity.Order;
import com.xt.entity.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDaoTest {

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() throws Exception {

        orderDao.saveOrder(new Order("1234567890", new Date(), new BigDecimal(400),0, 1));

        orderItemDao.saveOrderItem(new OrderItem(null,"java 从入门到精通", 1, new BigDecimal(100), new BigDecimal(100),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"javaScript 从入门到精通", 2,new BigDecimal(100), new BigDecimal(200),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(null,"Netty 入门", 1,new BigDecimal(100),new BigDecimal(100),"1234567890"));

    }

    @Test
    public void queryOrders() throws Exception {
        List<Order> orders = orderDao.queryOrders();
        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemDao.queryOrderItemsByOrderId(order.getOrderId());
            orderItems.forEach(System.out::println);
            System.out.println(order);
            System.out.println("==============");
        }
    }

    @Test
    public void updateStatus() throws Exception {
        orderDao.updateStatus("1234567890", 1);
    }

    @Test
    public void queryOrdersByUserId() throws Exception {
        orderDao.queryOrdersByUserId(1).forEach(System.out::println);
    }
}