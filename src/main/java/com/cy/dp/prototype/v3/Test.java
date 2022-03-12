package com.cy.dp.prototype.v3;

/**
 * String类型需要深克隆吗？
 * ----不需要
 * 虽然拷贝p1的时候，loc在内存中复制了一份，loc1和loc2的street指向的是同一个常量池bj
 * 但是没关系，只要你改p1.loc.street的话，不会改常量池的bj，改的是p1.loc.street引用指向新的常量池里面的sh,
 * 而p2.loc.street指向的还是bj。所以，不用对String进行深克隆
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

        p1.loc.street.replace("sh", "sz");
        System.out.println(p2.loc.street);
    }

}

class Person implements Cloneable{
    int age = 8;
    int score = 100;

    Location loc = new Location("bj", 22);

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person p = (Person) super.clone();
        p.loc = (Location) loc.clone();
        return p;
    }
}

class Location implements Cloneable{
    String street;
    int roomNo;

    public Location(String street, int roomNo) {
        this.street = street;
        this.roomNo = roomNo;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Location{" +
                "street='" + street + '\'' +
                ", roomNo=" + roomNo +
                '}';
    }
}