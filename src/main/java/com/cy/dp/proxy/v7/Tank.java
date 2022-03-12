package com.cy.dp.proxy.v7;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
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
 * v7: 如果有stop方法需要代理...
 * 如果想让LogProxy可以重用，不仅可以代理Movable，还可以代理任何其他可以代理的类型 Object
 * (毕竟日志记录，时间计算是很多方法都需要的东西)，这时该怎么做呢？
 * ----怎么让LogProxy可以代理Object任意类型？
 * 难点：
 * 1.如果代理Object，你不知道它的方法，你没法写死。 而Tank实现了Movable接口肯定有move方法所以你可以写。
 * 2.不是所有类都实现接口，怎么办?
 * <p>
 * 动态代理：
 * 1.代理类不是手写的，动态生成的。这个代理类运行的时候默默生成了。
 * <p>
 * 分离代理行为与被代理对象
 * 使用jdk的动态代理
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
        Tank tank = new Tank();

        //reflection 通过二进制字节码分析类的属性和方法

        Movable m = (Movable) Proxy.newProxyInstance(Tank.class.getClassLoader(),
                new Class[]{Movable.class},
                new LogHandler(tank));
        m.move();
    }
}

class LogHandler implements InvocationHandler{
    Tank tank;

    public LogHandler(Tank tank) {
        this.tank = tank;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method " + method.getName() + " start...");
        Object o = method.invoke(tank, args);
        System.out.println("method " + method.getName() + " end!");
        return o;
    }
}

interface Movable {
    void move();
}
