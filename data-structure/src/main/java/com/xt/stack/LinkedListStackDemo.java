package com.xt.stack;

import com.google.gson.Gson;
import lombok.Getter;

import java.util.Scanner;

/**
 * 链表模拟栈
 */
public class LinkedListStackDemo {
    public static void main(String[] args) {
        SingleLinkedListStack singleLinkedListStack = new SingleLinkedListStack();
        String key; // 接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示栈");
            System.out.println("e(exit): 退出程序");
            System.out.println("p(push): 添加数据到栈");
            System.out.println("po(pop): 从栈取出数据");
            key = scanner.next(); // 接收一个字符
            switch (key) {
                case "s":
                    singleLinkedListStack.list();
                    break;
                case "p":
                    System.out.println("请输入要添加的数据：");
                    singleLinkedListStack.push(scanner.nextInt());
                    break;
                case "po":
                    try {
                        int value = singleLinkedListStack.pop();
                        System.out.println("取出的数据：" + value);
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

// 管理节点
@Getter
class SingleLinkedListStack {
    private MyNode head = new MyNode(0);
    // 入栈
    public void push(int value) {
        MyNode node = new MyNode(value);
        MyNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    // 出栈
    public int pop() {
        // 判断链表是否为空
        if (head.next == null) {
            throw new RuntimeException("栈已空，没有数据");
        }
        MyNode temp = head;
        while (true) {
            if (temp.next.next == null) {
                break;
            }
            temp = temp.next;
        }
        int value = temp.next.no;
        temp.next = null;
        return value;
    }

    // 显示链表，遍历
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空~~~");
            return;
        }
        // 只有一个节点
        if (head.next.next == null) {
            System.out.println(head.next);
            return;
        }
        // 定义一个辅助的指针(变量)，帮助遍历原来的链表
        MyNode cur = head.next;
        MyNode newNode;
        MyNode reverseNode = new MyNode(0);
        // 使用Gson序列化进行深拷贝
        Gson gson = new Gson();
        /**
         * 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseList的最前端
         */
        while (cur != null) {
            newNode = gson.fromJson(gson.toJson(cur), MyNode.class);
            newNode.next = reverseNode.next; // 将cur的下一个节点指向新链表的最前端
            reverseNode.next = newNode; // 让 cur 连接到新链表上
            cur = cur.next;; // cur后移
        }

        MyNode temp = reverseNode.next;
        while (true) {
            if (temp == null) {
                break;
            }
            // 输出节点信息
            System.out.println(temp);
            temp = temp.next; // temp 后移
        }
    }
}

// 节点
class MyNode {
    public int no;
    public MyNode next; // 指向下一个节点
    public MyNode(int no) {
        this.no = no;
    }
    @Override
    public String toString() {
        return "MyNode{" +
                "no=" + no +
                '}';
    }
}
