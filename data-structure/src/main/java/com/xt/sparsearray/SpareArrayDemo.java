package com.xt.sparsearray;


/**
 * 稀疏数组
 */
public class SpareArrayDemo {

    public static void main(String[] args) {
        /**
         * 创建一个原始的二维数组 11*11
         * 0：表示没有棋子，1：表示黑子，2：表示蓝子
         */
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        printArr(chessArr1);

        System.out.println("二维数组转稀疏数组");
        int[][] sparseArray = transfer2SparseArray(chessArr1);
        printArr(sparseArray);

        System.out.println("稀疏数组还原二维数组");
        int[][] chessArr2 = transferFromSparseArray(sparseArray);
        printArr(chessArr2);
    }

    // 稀疏数组 ==》二维数组
    private static int[][] transferFromSparseArray(int[][] sparseArray) {
        // 1. 先读取稀疏数组的第一行数据，创建原始的二维数组
        int[][] chessArr = new int[sparseArray[0][0]][sparseArray[0][1]];

        // 2. 在读取稀疏数组后几行的数据(从第二行开始)，并赋值给原始的二维数据即可
        for (int i = 1; i < sparseArray.length; i++) {
                chessArr[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        return chessArr;
    }

    // 二维数组 ==》稀疏数组
    private static int[][] transfer2SparseArray(int[][] array) {
        // 1. 先遍历二维数组，得到非0数据的个数
        int diff = 0;
        for (int[] ints : array) {
            for (int value : ints) {
                if (0 != value) {
                    diff++;
                }
            }
        }
        // 2. 创建对应的稀疏数组
        int[][] sparseArr = new int[diff + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = array.length;
        sparseArr[0][1] = array[0].length;
        sparseArr[0][2] = diff;

        // 3. 遍历二维数组，江非0的值放到 sparseArr 中
        int count = 0; // 用于记录是第几个非0数据
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (0 != array[i][j]) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = array[i][j];
                }
            }
        }
        return sparseArr;
    }

    // 打印二维数组
    private static void printArr(int[][] array) {
        for (int[] ints : array) {
            for (int value : ints) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}