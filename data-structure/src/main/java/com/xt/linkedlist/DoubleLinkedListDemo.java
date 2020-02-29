package com.xt.linkedlist;

import lombok.Getter;

/**
 * 双向链表
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "Naruto", "鸣人");
        HeroNode2 hero2 = new HeroNode2(2, "Sasuke", "佐助");
        HeroNode2 hero3 = new HeroNode2(3, "Sakura", "小樱");
        HeroNode2 hero4 = new HeroNode2(4, "Kakashi", "卡卡西");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        HeroNode2 heroNode = new HeroNode2(3, "Sasori", "蝎");
        doubleLinkedList.update(heroNode);
        System.out.println("更新后的链表：");
        doubleLinkedList.list();

        doubleLinkedList.delete(3);
        System.out.println("删除后的链表：");
        doubleLinkedList.list();

        doubleLinkedList.addByOrder(heroNode);
        System.out.println("按顺序添加后的列表：");
        doubleLinkedList.list();
    }
}

@Getter
class DoubleLinkedList {
    private HeroNode2 head = new HeroNode2(0, "", "");

    // 添加节点到双向链表
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.prev = temp;
    }

    // 按编号顺序添加
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
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
            if (temp.next != null) {
                temp.next.prev = heroNode;
                heroNode.next = temp.next;
            }
            heroNode.prev = temp;
            temp.next = heroNode;
        }
    }

    // 修改节点的信息，根据no编号不能改
    public void update(HeroNode2 heroNode) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，不能更新");
            return;
        }
        // 找到需要修改的节点，根据no编号
        HeroNode2 temp = head.next;
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

    // 删除节点, 双向链表节点可自我删除
    public void delete(int no) {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空, 不能删除");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.prev.next = temp.next;
            if (temp.next != null) { // 非最后一个节点
                temp.next.prev = temp.prev;
            }
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
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

// 节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next; // 指向下一个节点
    public HeroNode2 prev; // 指向前一个节点

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}