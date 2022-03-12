package com.cy.dp.strategy;

public class Dog implements Comparable<Dog>{

    //饭量，饭量大的狗算大
    int food;

    public Dog(int food) {
        this.food = food;
    }

    @Override
    public int compareTo(Dog c){
        if(this.food < c.food) return -1;
        else if(this.food > c.food) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                '}';
    }
}
