package com.company;

public class SimpleThreadPoolExecutor implements Runnable {

    private int taskId;

    public SimpleThreadPoolExecutor(int taskId){
        this.taskId=taskId;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("execute task " + taskId + Thread.currentThread().getName());

    }
}
