package com.cy.dp.abstractfactory;

/**
 * 为什么这里不用接口？
 * 1.用接口也行，但是体会一下哪种更好。
 * 其实这里是一个语意的问题，食品现实世界中存在的，但是又不是一个具体的事物，所以用抽象类合适
 * 2.而Moveable使用接口，可移动的，是某一事物的属性，用接口合适
 */
public abstract class Food {
    abstract void printName();
}
