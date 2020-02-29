package com.xt.linkedlist;

import java.util.Stack;

/**
 * 演示栈的基本使用
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        // 入栈
        stack.add("a");
        stack.add("b");
        stack.add("c");
        // 出栈：c,b,a
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
