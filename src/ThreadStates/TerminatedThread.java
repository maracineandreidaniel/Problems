package ThreadStates;

public class TerminatedThread {
    public void terminatedThread() throws InterruptedException {
        Thread t=new Thread(()->{});
        t.start();
        Thread.sleep(1000);
        System.out.println(t.getState());

    }
}
