package com.company;

public interface ICaine {
    boolean latra();


    default void sare(){
        System.out.println("catelul sare");
    }
}
