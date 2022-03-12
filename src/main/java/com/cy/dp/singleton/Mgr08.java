package com.cy.dp.singleton;

/**
 * 不仅可以解决线程同步，还可以防止反序列化
 * 枚举单例
 * 枚举类是没有构造方法的，就算是拿到class文件也无法通过反射构造他的对象
 * 枚举类反编译之后是一个Abstract Class
 */
public enum Mgr08 {

    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(Mgr08.INSTANCE.hashCode())).start();
        }
    }
}
