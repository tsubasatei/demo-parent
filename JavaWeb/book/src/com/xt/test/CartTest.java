package com.xt.test;

import com.xt.entity.Cart;
import com.xt.entity.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author xt
 * @create 2020/3/8 18:00
 * @Desc
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        CartItem cartItem = new CartItem(1, "abc", 1, new BigDecimal(10), new BigDecimal(100));
        CartItem cartItem2 = new CartItem(1, "abc", 1, new BigDecimal(10), new BigDecimal(100));
        CartItem cartItem3 = new CartItem(2, "cde", 2, new BigDecimal(10), new BigDecimal(10));
        cart.addItem(cartItem);
        cart.addItem(cartItem2);
        cart.addItem(cartItem3);
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        CartItem cartItem = new CartItem(1, "abc", 1, new BigDecimal(10), new BigDecimal(100));
        CartItem cartItem2 = new CartItem(1, "abc", 1, new BigDecimal(10), new BigDecimal(100));
        CartItem cartItem3 = new CartItem(2, "cde", 2, new BigDecimal(10), new BigDecimal(10));
        cart.addItem(cartItem);
        cart.addItem(cartItem2);
        cart.addItem(cartItem3);
        System.out.println(cart);

        System.out.println("-------------");

        cart.deleteItem(2);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        CartItem cartItem = new CartItem(1, "abc", 1, new BigDecimal(10), new BigDecimal(100));
        CartItem cartItem2 = new CartItem(1, "abc", 1, new BigDecimal(10), new BigDecimal(100));
        CartItem cartItem3 = new CartItem(2, "cde", 2, new BigDecimal(10), new BigDecimal(10));
        cart.addItem(cartItem);
        cart.addItem(cartItem2);
        cart.addItem(cartItem3);

        cart.clear();
        System.out.println(cart);

    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        CartItem cartItem = new CartItem(1, "abc", 1, new BigDecimal(10), new BigDecimal(100));
        CartItem cartItem2 = new CartItem(1, "abc", 1, new BigDecimal(10), new BigDecimal(100));
        CartItem cartItem3 = new CartItem(2, "cde", 2, new BigDecimal(10), new BigDecimal(10));
        cart.addItem(cartItem);
        cart.addItem(cartItem2);
        cart.addItem(cartItem3);
        System.out.println(cart);
        System.out.println("-------------");
        cart.updateCount(1, 10);
        System.out.println(cart);
    }
}