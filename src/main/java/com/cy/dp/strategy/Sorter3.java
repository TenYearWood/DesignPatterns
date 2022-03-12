package com.cy.dp.strategy;

/**
 * 如果想对某个类进行排序，麻烦实现Comparable接口。
 *
 * 问题：
 * 1.虽然Sorter3实现了可以排序实现Comparable接口的类，但是仍然不够灵活。
 * 不灵活在哪个地方呢？现在Cat里是根据weight来决定两只猫的大小，假如现在要根据height来判断，怎么办?
 * 猫的比较大小的策略可以灵活的指定。那该怎么做？
 * 尽量不去修改原来的代码。见Sorter4
 */
public class Sorter3 {

    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;

            for (int j = i + 1; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) == -1 ? j : minPos;
            }

            swap(arr, i, minPos);
        }
    }


    static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
