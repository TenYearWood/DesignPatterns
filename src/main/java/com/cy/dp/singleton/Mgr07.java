package com.cy.dp.singleton;

/**
 * 静态内部类方式
 * JVM保证单例
 * 加载外部类时不会加载内部类，这样可以实现懒加载
 */
public class Mgr07 {

    private Mgr07() {
    }

    private static class Mgr07Holder {
        private static final Mgr07 INSTANCE = new Mgr07();
    }

    /**
     *  如果只加载Mgr01，它里面的Mgr07Holder是不会被初始化的
     *  调getInstance的时候静态内部类Mgr07Holder才会被加载
     *
     *  JVM保证线程安全：
     *  JVM加载类的时候只会加载一次
     */
    public static Mgr07 getInstance(){
        return Mgr07Holder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(Mgr07.getInstance().hashCode())).start();
        }
    }
}
