package com.xt.stack;

import java.util.Scanner;

/**
 * 数组模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(4);
        String key; // 接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示栈");
            System.out.println("e(exit): 退出程序");
            System.out.println("p(push): 添加数据到栈");
            System.out.println("po(pop): 从栈取出数据");
            System.out.println("pe(peek): 查看栈顶的数据");
            key = scanner.next(); // 接收一个字符
            switch (key) {
                case "s":
                    arrayStack.list();
                    break;
                case "p":
                    System.out.println("请输入要添加的数据：");
                    arrayStack.push(scanner.nextInt());
                    break;
                case "po":
                    try {
                        int value = arrayStack.pop();
                        System.out.println("取出的数据：" + value);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "pe":
                    try {
                        int head = arrayStack.peek();
                        System.out.println("栈顶数据：" + head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("退出程序，Bye!");
    }
}

// 数组模拟栈
class ArrayStack {
    private int maxSize; // 栈的大小
    private int[] array; // 数组模拟栈，存放数据
    private int top = -1; // 栈顶，初始化-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
    }

    // 入栈
    public void push(int value) {
        // 先判断栈是否满
        if (isFull()) {
            System.out.println("栈已满，不能添加数据");
            return;
        }
        top++;
        array[top] = value;
    }

    // 出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空，没有数据");
        }
        int value = array[top];
        top--;
        return value;
    }

    // 显示
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空, 没有数据~");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("array[%d]=%d\n", i, array[i]);
        }
    }

    // 栈顶
    public int peek() {
        if (isEmpty()) {
            System.out.println("栈空, 没有数据");
        }
        return array[top];
    }

    // 栈满
    private boolean isFull () {
        return top == maxSize - 1;
    }

    // 栈空
    private boolean isEmpty() {
        return top == -1;
    }
}
