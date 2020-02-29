package com.xt.recursion;

/**
 * 迷宫问题
 */
public class MiGong {
    public static void main(String[] args) {
        // 创建一个二维数组，模拟迷宫地图
        int[][] map = new int[8][7];
        // 使用1表示墙，上下全置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右全置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        // 输出地图
        System.out.println("地图情况：");
        printArray(map);

        // 使用递归回溯给小球找路
//        setWay(map, 1, 1);
        setWay2(map, 1, 1);
        System.out.println("小球走过，并标识过的地图情况：");
        printArray(map);
    }

    /**
     * 使用递归回溯来给小球找路
     * 说明
     * 1. map表示地图
     * 2. i,j表示从地图的哪个位置开始出发(1, 1)
     * 3. 如果小球能到map[6][5]位置，则说明通路找到
     * 4. 约定：当map[i][j]为 0：表示该点没有走过，1：表示墙，2：表示通路，3：表示该点已经走过，但是走不通
     * 5. 在走迷宫时，需要确定一个策略(方法)下->右->上->，如果该点走不通，再回溯
     * @param map：地图
     * @param i：从哪个位置开始找
     * @param j
     * @return ：如果找到通路，就返回true，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) { // 如果当前这个点没有走过
                map[i][j] = 2; // 假定该点可以走通
                if (setWay(map, i+1, j)) { // 下
                    return true;
                } else if (setWay(map, i, j+1)) { // 右
                    return true;
                } else if (setWay(map, i-1, j)) { // 上
                    return true;
                } else if (setWay(map, i, j-1)) { // 左
                    return true;
                } else {
                    // 说明该点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map[i][j]!=0,可能是1，2，3
                return false;
            }
        }
    }

    // 修改找路的策略，改成上->右->下->左
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) { // 如果当前这个点没有走过
                map[i][j] = 2; // 假定该点可以走通
                if (setWay(map, i-1, j)) { // 上
                    return true;
                } else if (setWay(map, i, j+1)) { // 右
                    return true;
                } else if (setWay(map, i+1, j)) { // 下
                    return true;
                } else if (setWay(map, i, j-1)) { // 左
                    return true;
                } else {
                    // 说明该点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map[i][j]!=0,可能是1，2，3
                return false;
            }
        }
    }

    // 打印
    private static void printArray(int[][] map) {
        for (int[] ints : map) {
            for (int value : ints) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
