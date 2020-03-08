package com.xt.test;

import com.xt.entity.Cart;
import com.xt.entity.CartItem;
import com.xt.service.OrderService;
import com.xt.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() throws Exception {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1, "java 从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        System.out.println( "订单号是：" + orderService.createOrder(cart, 1) );
    }

    @Test
    public void queryAllOrders() throws Exception {
        orderService.queryAllOrders().forEach(System.out::println);
    }

    @Test
    public void queryAllOrdersByUserId() throws Exception {
        orderService.queryAllOrdersByUserId(1).forEach(System.out::println);
    }

    @Test
    public void sendOrder() throws Exception {
        orderService.sendOrder("1583693783025");
    }

    @Test
    public void showOrderDetails() throws Exception {
        orderService.showOrderDetails("1583693783025").forEach(System.out::println);
    }

    @Test
    public void receiveOrder() throws Exception {
        orderService.receiveOrder("1583693783025");
    }
}