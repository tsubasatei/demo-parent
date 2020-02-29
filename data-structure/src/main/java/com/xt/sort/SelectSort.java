package com.xt.sort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1, -1, 90, 123};
//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));

        // 测试选择排序的时间复杂度O(n^2),创建80000个随机数的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 80000);
        }
        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("排序前的时间：" + startTime);
        selectSort(arr);
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println("排序后的时间：" + endTime);
        System.out.println("时间间隔：" + Duration.between(startTime, endTime).toMillis()); // 1989，比冒泡快
//        System.out.println("排序后   ：");
//        System.out.println(Arrays.toString(arr));
    }

    // 选择排序时间复杂度O(n^2)
    private static void selectSort(int[] arr) {
        int minIndex;
        int min;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) { // 说明假定的最小值，并不是最小
                    minIndex = j; // 重置 minIndex
                    min = arr[j]; // 重置 min
                }
            }
            if (minIndex != i) {
                // 将最小值，放在arr[i],即交换
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
//            System.out.printf("第%d趟排序:\n", i + 1);
//            System.out.println(Arrays.toString(arr));
        }
    }
}
