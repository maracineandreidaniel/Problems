package ThreadStates;

public class SyncCode implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static synchronized void syncMethod(){
        System.out.println(Thread.currentThread().getName());
        while(true){

        }
    }
}
