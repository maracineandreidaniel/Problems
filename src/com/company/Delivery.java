package com.company;

import java.util.function.Consumer;

public class Delivery {
    public Delivery firstname(String firstname){
        System.out.println(firstname);
        return this;
    }

    public Delivery lastName(String lastName){
        System.out.println(lastName);
        return this;
    }

    public static void deliver(Consumer<Delivery> parcel){
        Delivery delivery=new Delivery();
        parcel.accept(delivery);
        System.out.println("done");
    }







}
