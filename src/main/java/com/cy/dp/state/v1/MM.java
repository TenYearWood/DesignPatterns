package com.cy.dp.state.v1;

/**
 * MM state不同时smile、cry、say时的动作都不同。
 *
 * 当增加新的状态时非常不方便
 */
public class MM {
    String name;
    MMState state;

    private enum MMState {HAPPY, SAD}

    public void smile(){
        //switch case
        switch (state){
            case HAPPY:
                System.out.println("哈哈大笑...");
                break;
            case SAD:
                System.out.println("冷冷的笑...");
                break;
        }
    }

    public void cry(){
        //switch case
        // 也类似于上面，根据state不同，表现不同...
    }

    public void say(){
        //switch case
    }
}
