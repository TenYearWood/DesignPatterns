package com.cy.dp.factorymethod;

/**
 * 生产交通工具的工厂
 * 1.简单工厂。
 * 但是这种方式可扩展性并不好。
 * 对每一种交通工具使用自己的工厂方法，见CarFactory
 */
public class SimpleVehicleFactory {

    public Car createCar(){
        //before processing
        return new Car();
    }

    public Broom createBroom(){
        return new Broom();
    }
}
