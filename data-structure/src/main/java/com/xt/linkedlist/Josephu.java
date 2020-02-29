package com.xt.linkedlist;

import lombok.Data;

/**
 * 约瑟夫问题
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

// 创建一个环形的单向链表
class CircleSingleLinkedList {
    // 创建一个 first 节点，当前没有编号
    private Boy first;
    // 添加小孩节点，构建一个环形的链表
    public void addBoy(int nums) {
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null; // 辅助指针，帮助构建环形链表
        // 使用for循环创建单向环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号创建小孩
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if (i == 1) {
                first = boy;
                first.setNext(first); // 构成环
                curBoy = first; // 让 curBoy 指向第一个小孩
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    // 遍历当前的环形链表
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("没有任何小孩~");
            return;
        }

        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) { // 说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext(); // curBoy 后移
        }
    }

    /**
     * 根据用户的输入，计算出小孩的出圈的顺序
     * @param startNo: 表示从第几个小孩开始数数
     * @param countNum: 表示数几下
     * @param nums: 表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums || countNum < 1 || nums < 1) {
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        // 创建辅助指针helper，指向环形链表的最后节点，帮助完成小孩出圈
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) { // 说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }
        // 小孩报数前，先让 first 和 helper 移动 startNo - 1 次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        /**
         * 当小孩报数时，让 first 和 helper 指针同时移动 countNum - 1 次，然后出圈
         * 这里是循环操作，知道圈中只有一个节点
         */
        while (true) {
            if (helper == first) { // 圈中只有一个节点
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这是first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩 %d 出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号是：%d \n", first.getNo());
    }
}

// 创建一个Boy，表示一个节点
@Data
class Boy {
    private int no; // 编号
    private Boy next; // 指向下一个节点，默认null
    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
