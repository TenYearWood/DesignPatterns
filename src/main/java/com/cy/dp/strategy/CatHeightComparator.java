package com.cy.dp.strategy;

/**
 * 猫身高比较器
 */
public class CatHeightComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        return o1.height - o2.height;
    }
}
