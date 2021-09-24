package ThreadStates;

public class RunnableThread {
    public void runnableThread(){
        Thread t=new Thread(()->{});
        t.start();
        System.out.println(t.getState());
    }
}
