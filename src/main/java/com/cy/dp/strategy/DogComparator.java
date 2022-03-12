package com.cy.dp.strategy;

/**
 * 狗的比较器
 */
public class DogComparator implements Comparator<Dog> {
    @Override
    public int compare(Dog o1, Dog o2) {
        return o1.food - o2.food;
    }
}
