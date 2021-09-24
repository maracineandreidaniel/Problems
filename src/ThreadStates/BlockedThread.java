package ThreadStates;

public class BlockedThread {

    public void blockedThread() throws InterruptedException {
        Thread t1=new Thread(new SyncCode());
        Thread t2=new Thread(new SyncCode());
        t1.start();
        Thread.sleep(2000);
        t2.start();
        Thread.sleep(2000);
        System.out.println(t1.getState());
        System.out.println(t2.getState());
    }
}
