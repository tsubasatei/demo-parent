package com.xt.sort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));

        // 测试选择排序的时间复杂度O(n^2),创建80000个随机数的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 80000);
        }
        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("排序前的时间：" + startTime);
        insertSort(arr);
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println("排序后的时间：" + endTime);
        System.out.println("时间间隔：" + Duration.between(startTime, endTime).toMillis()); // 542
//        System.out.println("排序后：");
//        System.out.println(Arrays.toString(arr));
    }

    // 插入排序
    private static void insertSort(int[] arr) {

        int insertValue;
        int insertIndex; // 即arr[1]的前面的这个数的下标

        for (int i = 1; i < arr.length; i++) {
            // 第1轮
            insertValue = arr[i];
            insertIndex = i - 1; // 即arr[1]的前面的这个数的下标

            /**
             * 给insertValue找到插入的位置
             * 说明：
             * 1. insertIndex >= 0：保证在给insertValue找插入位置，不越界
             * 2. insertValue < arr[insertIndex] 待插入的数，还没有找到插入位置
             * 3. 需要将arr[insertIndex]后移
             */
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex != i - 1) {
                // 当退出while循环时，说明插入的位置找到，insertIndex+1
                arr[insertIndex + 1] = insertValue;
            }
//            System.out.println("第" + i + "轮插入：");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
