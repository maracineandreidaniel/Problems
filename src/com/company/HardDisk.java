package com.company;

public class HardDisk implements IODevice{
    @Override
    public void copy() {
        System.out.println("copying");
    }

    @Override
    public void delete() {

    }

    @Override
    public void move() {

    }
}
