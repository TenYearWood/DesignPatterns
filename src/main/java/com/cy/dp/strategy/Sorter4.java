package com.cy.dp.strategy;

/**
 * 现在sort方法里不仅可以传各种类型，还可以不断扩展比较策略。
 * 写法的可扩展性就强得多了。
 *
 * 策略模式：
 * 1.想要扩展的话只需要添加新的策略就行了。
 * 策略模式封装的是做一件事情的时候不同的执行方式。比如说sort方法里面比较大小的方式不同，就换不同的策略就行了。
 *
 */
public class Sorter4<T> {

    /**
     * 要求传T类型的数组，必须传一个T类型的比较器，告诉我你这种类型到底要怎么比较大小
     *
     * @param arr
     * @param comparator
     */
    public void sort(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;

            for (int j = i + 1; j < arr.length; j++) {
                minPos = comparator.compare(arr[j], arr[minPos]) == -1 ? j : minPos;
            }

            swap(arr, i, minPos);
        }
    }


    private void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
