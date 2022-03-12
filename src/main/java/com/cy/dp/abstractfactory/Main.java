package com.cy.dp.abstractfactory;

/**
 * 抽象工厂
 * 1.定义了一系列产品族的概念
 * 现代人: 开车、AK47的武器、嚼面包
 * 魔法人：骑扫帚、魔法棒、吃蘑菇
 * 有没有一种方式能灵活的指定不同的族系？当新的产品族加入代码的时候，怎么能不怎么改代码？
 * 怎么灵活的更换产品一族？
 *
 * 2.抽象工厂在产品族上扩展非常方便。
 * 但是在单一产品的维度上扩展不是很方便
 *
 */
public class Main {

    public static void main(String[] args) {
        /*
        Car c = new Car();
        c.go();
        AK47 w = new AK47();
        w.shoot();
        Bread b = new Bread();
        b.printName();*/

        AbstractFactory f = new ModernFactory();
        Vehicle c = f.createVehicle();
        Weapon w = f.createWeapon();
        Food b = f.createFood();


    }
}
