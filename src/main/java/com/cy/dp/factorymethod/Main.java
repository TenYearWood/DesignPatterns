package com.cy.dp.factorymethod;

public class Main {

    public static void main(String[] args) {
//        Car c = new Car();
//        c.go();
//
//        Plane p = new Plane();
//        p.go();

        Moveable m = new Car();
        m.go();

        Moveable m2 = new CarFactory().create();
        m2.go();
    }





    /**
     * 1.实现Moveable接口，可以实现了定制交通工具。但是如果我想任意定制生产过程呢？
     * 比如Car的生产不new还可以有别的吗？---可以的。比如实际当中的应用，生产交通工具的时候，要求你控制权限，
     * 对于飞机、扫帚、汽车的权限是不一样的。这样如果直接写new Car()然后加权限判断，写死了，一旦改动代码会变来变去。
     *
     * 2.将产生对象的过程交给一个工厂方法。分为简单工厂、静态工厂、抽象工厂等。
     *
     * 3.抽象工厂
     *
     */
}
