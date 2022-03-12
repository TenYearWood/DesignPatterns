package com.cy.dp.iterator.v6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * v1:构建一个容器，可以添加对象
 * v2:用链表来实现一个容器
 * v3:添加容器的共同接口，实现容器的替换
 * v4:如何对容器遍历呢？
 * v5:用一种统一的遍历方式，要求每一个容器都要提供Iterator的实现类。
 *
 * v6:jdk容器的iterator
 */
public class Main {
    public static void main(String[] args) {
        Collection list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add(new String("s" + i));
        }
        System.out.println(list.size());

        //这个接口的调用方式：
        Iterator it = list.iterator();
        while(it.hasNext()){
            Object o = it.next();
            System.out.println(o);
        }
    }
}

