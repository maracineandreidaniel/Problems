package com.company;

public class Remover {
    private Remover(){
        throw new AssertionError("Cant");
    }

    public static String remove(String s, RemoveStrategy strategy){
        return strategy.execute(s);
    }
}
