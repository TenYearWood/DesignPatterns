package com.cy.dp.strategy;

/**
 * 如果又要对狗进行排序呢？那还在再写吗？
 * 所以在sort的入参里面写死类型的话肯定是不行的。将来想对所有的类型进行排序，要想做到这点。
 * 见Sorter3
 */
public class Sorter2 {

    /**
     * 增加对猫进行排序的方法
     * @param arr
     */
    public static void sort(Cat[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;

            for (int j = i + 1; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) == -1 ? j : minPos;
            }

            swap(arr, i, minPos);
        }
    }


    static void swap(Cat[] a, int i, int j) {
        Cat temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
