package com.xt.recursion;

/**
 * 八皇后问题
 */
public class Queen8 {
    // 定义一个max表示共有多少个皇后
    int max = 8;
    // 定义数组array，保存皇后放置位置的结束，比如arr={0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0; // 多少种方法
    static int judgeCount = 0; // 回溯次数
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有揭发：%d\n", count); //92
        System.out.printf("一共判断冲突的次数: %d\n", judgeCount); // 15720
    }

    /**
     * 编写一个方法，放置第n个皇后
     * 注意：check 是每一次递归，进入到check中都有for(int i=0; i<max; i++),因此会有回溯
     */
    private void check(int n) {
        if (n == max) { // n=8时，8个皇后已然放好
            print();
            return;
        }
        // 依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            /**
             * 先把当前这个皇后n，放到该行的第1列，判断是否有冲突
             */
            array[n] = i;
            if (judge(n)) { // 不冲突
                // 接着放n+1个皇后，即开始递归
                check(n + 1);
            }
            // 如果冲突，继续执行array[n]=i;即将第n个皇后，放置在本行的后移一个位置
        }
    }

    /**
     * 查看当放置第n个皇后，检测该皇后是否和前面已经摆放的皇后是否冲突
     * @param n 表示第n个皇后
     * @return true: 不冲突，false: 冲突
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            /**
             * 说明
             * 1. array[i] == array[n] 表示第n个皇后和前面的第i个皇后在同一列
             * 2. Math.abs(n-i) == Math.abs(array[n]-array[i]) 表示判断 第n个皇后是否和第i个皇后在同一斜线
             * 如 n=1,放置第2列,n=1,array[1]=1, Math.abs(1-0)=Math.abs(array[1]-array[0])=1
             * 3. 判断是否在同一行，没有必要，n每次都在递增
             */
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        count++;
        for (int i = 0; i < max; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
