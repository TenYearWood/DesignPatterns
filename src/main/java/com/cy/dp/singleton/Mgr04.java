package com.cy.dp.singleton;

/**
 * 懒汉式
 * 通过synchronized解决，但也带来效率下降
 */
public class Mgr04 {

    private static Mgr04 INSTANCE;

    private Mgr04() {

    }

    /**
     * 锁定的是Mgr04.class对象
     * synchronized上了一把锁，在同一时间段里面，只允许一个线程执行里面的代码
     * 这个线程执行完了，另外一个线程才允许执行这个代码
     * 这么做肯定没问题，因为在同一时刻只有一个线程把这个整段的代码执行完，另外一个线程才可以运行。
     * 会把整个的语句，方法下面所有的语句当成一个原子不可分割，中间不允许打断。
     *
     * 如果整个方法里面包含一些其他的业务逻辑，也包含了整个的初始化，就会发现我锁定的代码就有一些
     * 耗时太长了，业务逻辑从数据库里读一些数据出来我有必要上锁吗？--没必要
     * 所以我们为了让锁的粒度稍微变得细一些，不给整个方法上锁，看Mgr05
     * @return
     */
    public static synchronized Mgr04 getInstance() {
        //业务逻辑
        //...

        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr04();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(getInstance().hashCode())).start();
        }
    }
}
