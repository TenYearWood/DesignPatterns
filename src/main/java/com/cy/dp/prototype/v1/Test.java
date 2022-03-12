package com.cy.dp.prototype.v1;

/**
 * 浅克隆
 * 如果想做到自己的loc再克隆一份，怎么办呢？见v2
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person p1 = new Person();
        Person p2 = (Person) p1.clone();
        System.out.println(p2.age + " " + p2.score);
        System.out.println(p2.loc);

        System.out.println(p1.loc == p2.loc);
        p1.loc.street = "sh";
        System.out.println(p2.loc);
    }

}

/**
 * Cloneable标记性的接口
 * interface Cloneable里面没有任何的方法
 *
 * clone方法是使用Object父类的clone方法，它的实现：
 * 1.直接在内存中copy一份出来。
 * 2.age和score是基本数据类型可以拷贝过去。但是loc Location为引用类型，所以clone出来的p2和p1的loc引用都指向了同一个Location
 * 所以，p1.loc == p2.loc  true.两个对象的引用指向同一个对象，互相之间就会有影响。
 */
class Person implements Cloneable{
    int age = 8;
    int score = 100;

    Location loc = new Location("bj", 22);


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Location {
    String street;
    int roomNo;

    public Location(String street, int roomNo) {
        this.street = street;
        this.roomNo = roomNo;
    }

    @Override
    public String toString() {
        return "Location{" +
                "street='" + street + '\'' +
                ", roomNo=" + roomNo +
                '}';
    }
}