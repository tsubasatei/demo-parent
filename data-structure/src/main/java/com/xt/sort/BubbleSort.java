package com.xt.sort;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Arrays;

/**
 * 冒泡排序
 * 时间复杂度 O(n^2)
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, 20};
//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));

        // 测试冒泡排序的时间复杂度O(n^2),创建80000个随机数的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 80000);
        }
        LocalDateTime startTime = LocalDateTime.now();
        System.out.println("排序前的时间：" + startTime);
        bubbleSort(arr);
        LocalDateTime endTime = LocalDateTime.now();
        System.out.println("排序后的时间：" + endTime);
        System.out.println("时间间隔：" + Duration.between(startTime, endTime).toMillis()); // 11658
//        System.out.println("排序后：");
//        System.out.println(Arrays.toString(arr));
    }

    // 冒泡排序
    private static void bubbleSort(int[] arr) {
        int temp; // 临时变量
        boolean flag = false; // 标识变量是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) { // 如果前面的数比后面的大，则交换
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.printf("第%d趟排序:\n", i + 1);
//            System.out.println(Arrays.toString(arr));
            if (!flag) { // 在一趟排序中，一次交换都没有发生过
                break;
            } else {
                flag = false; // 重置flag，进行下次判断
            }
        }
    }
}
