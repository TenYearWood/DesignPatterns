package com.cy.dp.singleton;

/**
 * 懒汉式
 */
public class Mgr05 {

    private static Mgr05 INSTANCE;

    private Mgr05() {

    }

    /**
     * 缩小锁的粒度，放过业务代码，这是追求效率的写法。
     * 那么在多线程访问的情况下，能不能保障数据的一致性？ ---不行
     * 诞生了新的写法，看Mgr06
     *
     * @return
     */
    public static Mgr05 getInstance() {
        //业务逻辑
        //...

        if (INSTANCE == null) {
            /**
             * 妄图通过减小同步代码块的方式提高效率，然后不可行
             * if (INSTANCE == null)判断语句没有和INSTANCE = new Mgr05()语句作为一体化来操作
             */
            synchronized (Mgr05.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Mgr05();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(getInstance().hashCode())).start();
        }
    }
}
