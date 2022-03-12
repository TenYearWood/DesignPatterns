package com.cy.dp.strategy;

/**
 * 1.sort这个方法目前只是对int类型的数组进行排序，如果要排序double类型的怎么办？
 * 如果又要比较float类型的呢？每个类型的都去写一遍代码就太累了。
 * 2.如果对Cat进行排序怎么做？ 见Sorter2
 *
 */
public class Sorter {

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;

            for (int j = i + 1; j < arr.length; j++) {
                minPos = arr[j] < arr[minPos] ? j : minPos;
            }

            swap(arr, i, minPos);
        }
    }


    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
