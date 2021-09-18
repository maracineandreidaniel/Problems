package com.company;

public class StatieIasi implements FireObserver{
    @Override
    public void fire(String adress) {
        if(adress.contains("iasi"))
            System.out.println("iasi dasdas");
    }
}
