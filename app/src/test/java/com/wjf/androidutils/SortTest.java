package com.wjf.androidutils;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * @Description
 * @Author WuJianFeng
 * @Date 2024/1/19 14:42
 */

public class SortTest {

    private int[] arr;
    @Before
    public void setup(){
        arr = new int[]{3,1,5,2,4,9,6,7};
    }

    /**
     * 冒泡排序
     */
    @Test
    public void bubble(){
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        System.out.println("__sort-bubble"+Arrays.toString(arr));
    }

    /**
     * 插入排序
     */
    @Test
    public void insertion(){
        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {
            // 记录要插入的数据
            int tmp = arr[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            // 存在比其小的数，插入
            if (j != i) {
                arr[j] = tmp;
            }
        }
        System.out.println("__sort-insertion"+Arrays.toString(arr));

    }

    /**
     * 选择排序
     */
    @Test
    public void selection(){
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i; // 用来记录最小值的索引位置，默认值为i
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; // 遍历 i+1~length 的值，找到其中最小值的位置
                }
            }
            // 交换当前索引 i 和最小值索引 minIndex 两处的值
            if (i != minIndex) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
            // 执行完一次循环，当前索引 i 处的值为最小值，直到循环结束即可完成排序
        }
        System.out.println("__sort-selection"+Arrays.toString(arr));
    }

    /**
     * 希尔排序
     */
    @Test
    public void hill(){
        int gap = 1;
        while (gap < arr.length) {
            gap = gap * 3 + 1;
        }
        while (gap > 0) {
            for (int i = gap; i < arr.length; i++) {
                int tmp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = tmp;
            }
            gap = (int) Math.floor(gap / 3);
        }
        System.out.println("__sort-hill"+Arrays.toString(arr));

    }

    /**
     * 快速排序
     */
    @Test
    public void quick(){

    }

    /**
     * 归并排序
     */
    @Test
    public void merge(){

    }

    /**
     * 堆排序
     */
    @Test
    public void heap(){

    }

    /**
     * 计数排序
     */
    @Test
    public void count(){

    }

    /**
     * 基数排序
     */
    @Test
    public void radix(){

    }

    /**
     * 桶排序
     */
    @Test
    public void bucket(){

    }
}
