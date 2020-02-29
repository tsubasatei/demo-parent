package com.xt.queue;

import java.util.Scanner;

/**
 * 队列
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key; // 接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0); // 接收一个字符
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入要添加的数据：");
                    arrayQueue.addQueue(scanner.nextInt());
                    break;
                case 'g':
                    try {
                        int queue = arrayQueue.getQueue();
                        System.out.println("取出的数据：" + queue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = arrayQueue.headQueue();
                        System.out.println("对列头数据：" + head);
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

// 使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueue {
    private int maxSize; // 表示数组的最大容量
    private int front;  // 队列头
    private int rear;   // 队列尾
    private int[] array;  // 该数据用于存放数据，模拟队列

    // 创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        this.front = -1; // 指向队列头部，front是指向队列头的前一个位置
        this.rear = -1; // 指向队列尾，指向队列尾的数据(即就是队列最后一个数据)
    }

    // 添加数据到队列
    public void addQueue(int value) {
        if (!isFull()) {
            rear++; // rear 后移
            array[rear] = value;
        } else {
            System.out.println("队列已满，无法加入数据");
        }
    }

    // 获取队列的数据，出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取数据");
        }
        front++; // front 后移
        return array[front];
    }

    // 显示队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front + 1; i <= rear; i++) {
            System.out.printf("array[%d] = %d\n", i, array[i]);
        }
    }

    // 显示队列的头数据，注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取数据");
        }
        return array[front + 1];
    }

    // 判断队列是否空
    private boolean isEmpty() {
        return rear == front;
    }

    // 判断
    private boolean isFull() {
        return rear == maxSize -1;
    }
}