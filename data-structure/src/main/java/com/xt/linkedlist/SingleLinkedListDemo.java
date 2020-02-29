package com.xt.linkedlist;

import lombok.Getter;

import java.util.Stack;

/**
 * 单链表
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 创建节点
        HeroNode hero1 = new HeroNode(1, "Naruto", "鸣人");
        HeroNode hero2 = new HeroNode(2, "Sasuke", "佐助");
        HeroNode hero3 = new HeroNode(3, "Sakura", "小樱");
        HeroNode hero4 = new HeroNode(4, "Kakashi", "卡卡西");

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        // 加入方式一
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        // 加入按编号顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero3);

        // 测试修改
        HeroNode heroNode = new HeroNode(3, "sakura", "春野樱");
        singleLinkedList.update(heroNode);

        // 测试删除
        singleLinkedList.delete(3);

        singleLinkedList.list();

        // 求单链表有效节点的个数
        System.out.println("有效节点的个数：" + getLength(singleLinkedList.getHead()));

        // 查找单链表中的倒数第 k 个节点
        System.out.println("查找单链表中的倒数第 3 个节点");
        System.out.println(findLastIndexNode(singleLinkedList.getHead(), 3));

        System.out.println("单链表反转：");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();

        System.out.println("从尾到头打印单链表：");
        printReverse(singleLinkedList.getHead());
    }

    /**
     * 面试题1：获取单链表的有效节点的个数(如果是带头节点的链表，需要不统计头节点)
     * @param head: 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) { // 空链表
            return 0;
        }
        HeroNode cur = head.next;
        int count = 0;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        return count;
    }

    /**
     * 面试题2：查找单链表中的倒数第 k 个节点
     * 思路：
     * 1. 编写一个方法，接收head节点，同时接收一个index
     * 2. 先把链表从头到尾遍历，得到链表的总长度 getLength()
     * 3. 得到size后，从链表的第一个开始遍历(size-index)个，就可以得到
     * 4. 如果找到了，则返回该节点，否则返回null
     * @param head：头节点
     * @param index：倒数第index个节点
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        int size = getLength(head);
        if (size == 0) {
            return null;
        }
        // index 校验
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义辅助变量, for循环定位到倒数index
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    // 面试题3：单链表反转
    public static void reverseList(HeroNode head) {
        // 当前链表为空或者只有一个节点, 无需反转, 直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        // 定义一个辅助的指针(变量)，帮助遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next; // 指向当前节点(cur)的下一个节点
        HeroNode reverseNode = new HeroNode(0, "", "");
        /**
         * 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseList的最前端
         */
        while (cur != null) {
            next = cur.next; // 先暂时保存当前节点的下一个节点
            cur.next = reverseNode.next; // 将cur的下一个节点指向新链表的最前端
            reverseNode.next = cur; // 让 cur 连接到新链表上
            cur = next; // cur后移
        }
        // 将head.next 指向reverseNode.next, 实现单链表的反转
        head.next = reverseNode.next;
        reverseNode.next = null;
    }

    // 面试题4：利用栈：从尾到头打印单链表
    public static void printReverse(HeroNode head) {
        Stack<HeroNode> stack = new Stack<>();
        if (head.next == null) {
            System.out.println("链表为空, 不能打印");
            return;
        }
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}

// 管理节点
@Getter
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点到单向链表
     * 思路，当不考虑编号顺序时
     * 1. 找到当前链表的最后节点
     * 2. 将最后这个节点的next指向新的节点
     */
    public void add(HeroNode heroNode) {
        // head 节点不能动，需要一个辅助遍历 temp
        HeroNode temp = head;
        // 遍历链表，找到最后
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后，将temp后移
            temp = temp.next;
        }
        /**
         * 当退出while循环时，temp就指向了链表的最后
         * 将最后这个节点的 next 指向 新的节点
         */
        temp.next = heroNode;
    }

    /**
     * 按编号顺序添加
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        // 单链表，temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; // 标志添加的编号是否存在，默认false
        while (true) {
            if (temp.next == null) { // temp 已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) { // 位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) { // 说明希望添加的 heroNode 的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next; // 后移遍历当前链表
        }
        // 判断 flag 的值
        if (flag) { // 不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了，不能加入\n", heroNode.no);
        } else {
            // 插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点的信息，根据no编号不能改
     */
    public void update(HeroNode heroNode) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // 找到需要修改的节点，根据no编号
        HeroNode temp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true; // 找到
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.printf("没找到编号 %d 的节点，不能修改\n", heroNode.no);
        }
    }

    /**
     * 删除节点, 比较 temp.next.no 和 no
     */
    public void delete(int no) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到编号 %d 的节点，无法删除\n", no);
        }
    }

    // 显示链表，遍历
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
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
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next; // 指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
