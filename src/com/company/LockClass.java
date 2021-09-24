package com.company;

public class LockClass {
private static Object lock=new Object();
    public void method() {
        synchronized (LockClass.class) {
        }
        synchronized (lock){

        }
        synchronized (this){

        }
    }
}
