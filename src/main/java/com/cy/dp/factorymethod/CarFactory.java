package com.cy.dp.factorymethod;

/**
 * 工厂方法：只生产一种产品的抽象工厂
 */
public class CarFactory {

    public Moveable create(){
        System.out.println("a car created!");
        return new Car();
    }
}
