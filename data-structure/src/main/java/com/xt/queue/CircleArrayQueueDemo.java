package com.xt.queue;

import java.util.Scanner;

/**
 * 队列
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue arrayQueue = new CircleArrayQueue(4); // 设置4，其队列的有效数据最大是3
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

// 使用数组模拟队列-编写一个CircleArrayQueue类
class CircleArrayQueue {
    private int maxSize; // 表示数组的最大容量
    private int front;  // 指向队列的第一个元素，也就是说arr[front],初始值0
    private int rear;   // 指向队列的最后一个元素的后一个位置，空出一个元素，初始值0
    private int[] array;  // 该数据用于存放数据，模拟队列

    // 创建队列的构造器
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        array = new int[maxSize];
        this.front = 0;
        this.rear = 0;
    }

    // 添加数据到队列
    public void addQueue(int value) {
        if (isFull()) {
            System.out.println("队列已满，无法加入数据");
        } else {
            array[rear] = value;
            rear = (rear + 1) % maxSize; // rear 后移，考虑取模
        }
    }

    // 获取队列的数据，出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取数据");
        }
        /**
         * front指向的第一个元素
         * 1. 先把 front 对应的值保留到一个临时变量
         * 2. 将 front 后移
         * 3. 将临时保存的变量返回
         */
        int value = array[front];
        front = (front + 1) % maxSize; // front 后移
        return value;
    }

    // 显示队列
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        /**
         * 思路：从 front 开始遍历，遍历多少个元素
         */
        for (int i = front; i < front + size(); i++) {
            System.out.printf("array[%d] = %d\n", i % maxSize, array[i % maxSize]);
        }
    }

    // 队列有效元素的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列的头数据，注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法获取数据");
        }
        return array[front];
    }

    // 判断队列是否空
    private boolean isEmpty() {
        return rear == front;
    }

    // 判断
    private boolean isFull() {
        return (rear + 1) % maxSize == front;
    }
}