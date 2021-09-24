package ThreadStates;

public class WaitingThread  {

    public void waitingThread(){
        new Thread(()->{
            Thread t1=Thread.currentThread();
            Thread t2=new Thread(()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(t1.getState());
            });

            t2.start();
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
