package com.cy.dp.builder;


public class Main {
    public static void main(String[] args) {
        TerrainBuilder builder = new ComplexTerrainBuilder();
        Terrain t = builder.buildWall().buildFort().buildMine().build();

        /**
         * 可以灵活指定对象的某个部分
         */
        Person p = new Person.PersonBuilder()
                .basicInfo(1, "zhangsan", 10)
                .score(100)
                .weight(50.0)
                .loc("street", "1001")
                .build();
    }
}
