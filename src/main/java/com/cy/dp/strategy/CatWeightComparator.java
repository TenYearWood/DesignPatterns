package com.cy.dp.strategy;

/**
 * 猫重量比较器
 */
public class CatWeightComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        return o1.weight - o2.weight;
    }
}
