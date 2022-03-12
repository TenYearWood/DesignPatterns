package com.cy.dp.strategy;

/**
 * 比较器
 */
@FunctionalInterface
public interface Comparator<T> {

    int compare(T o1, T o2);

    default void m(){
        System.out.println("m");
    }

    /**
     * 1.8之后可以在接口中写方法
     */
}
