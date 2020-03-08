package com.xt.service.impl;

import com.xt.dao.BookDao;
import com.xt.dao.OrderDao;
import com.xt.dao.impl.BookImpl;
import com.xt.dao.impl.OrderDaoImpl;
import com.xt.dao.impl.OrderItemDao;
import com.xt.dao.impl.OrderItemDaoImpl;
import com.xt.entity.*;
import com.xt.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) throws Exception {
        // 订单号===唯一性
        String orderId = System.currentTimeMillis() + "";
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);
        // 保存订单
        orderDao.saveOrder(order);

        // 遍历购物车中每一个商品项转换成为订单项保存到数据库
        Map<Integer, CartItem> items = cart.getItems();
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            // 获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), order.getOrderId());
            // 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            // 更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        // 清空购物车
        cart.clear();

        return orderId;
    }

    @Override
    public List<Order> queryAllOrders() throws Exception {
        return orderDao.queryOrders();
    }

    @Override
    public List<Order> queryAllOrdersByUserId(Integer userId) throws Exception {
        return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    public boolean sendOrder(String orderId) throws Exception {
        return orderDao.updateStatus(orderId, 1);
    }

    @Override
    public List<OrderItem> showOrderDetails(String orderId) throws Exception {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public boolean receiveOrder(String orderId) throws Exception {
        return orderDao.updateStatus(orderId, 2);
    }
}
