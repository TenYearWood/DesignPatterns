package com.cy.dp.strategy;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
//        int[] a = {9, 2, 3, 5, 7, 1, 4};
        Cat[] a = {new Cat(3, 8), new Cat(5, 2), new Cat(1, 6)};
        Sorter2.sort(a);
        System.out.println(Arrays.toString(a));

        Dog[] b = {new Dog(8), new Dog(5), new Dog(10)};
        Sorter3.sort(b);
        System.out.println(Arrays.toString(b));

        Sorter4<Dog> sorter4 = new Sorter4<>();
        sorter4.sort(b, new DogComparator());
        System.out.println(Arrays.toString(b));

        Sorter4<Cat> sorter4Cat = new Sorter4<>();
        sorter4Cat.sort(a, new CatWeightComparator());
        System.out.println(Arrays.toString(a));

        sorter4Cat.sort(a, new CatHeightComparator());
        System.out.println(Arrays.toString(a));
    }
}
