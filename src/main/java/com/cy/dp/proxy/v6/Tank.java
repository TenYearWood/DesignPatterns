package com.cy.dp.proxy.v6;

import java.util.Random;

/**
 * 问题：我想记录坦克的移动时间
 * 最简单的办法：修改代码，记录时间
 * 问题2：如果无法改变方法源码呢？
 * 用继承？
 * <p>
 * v4：使用代理
 * v5: 代理有各种类型
 * 问题：如何实现代理的各种组合？继承？Decorator?
 * v6: 代理的对象改成Movable类型-越来越像Decorator了。
 * <p>
 * 最简单最基础的静态代理版本
 */
public class Tank implements Movable {

    /**
     * 模拟坦克移动了一段儿时间
     */
    @Override
    public void move() {
        System.out.println("Tank moving claclacla...");
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TankLogProxy(
                new TankTimeProxy(
                        new Tank())
        ).move();
    }
}

/**
 * 聚合的好处在哪？
 * TankTimeProxy里面可以聚合Movable，只要是可移动的都可以进行代理。
 */
class TankTimeProxy implements Movable {
    Movable m;

    public TankTimeProxy(Movable m) {
        this.m = m;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        m.move();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

class TankLogProxy implements Movable {
    Movable m;

    public TankLogProxy(Movable m) {
        this.m = m;
    }

    @Override
    public void move() {
        System.out.println("start moving...");
        m.move();
        System.out.println("stopped!");
    }
}

interface Movable {
    void move();
}
