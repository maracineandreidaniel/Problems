package com.company;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class ServerService implements Runnable {
    private static final Logger logger=Logger.getLogger(ServerService.class.getName());
    private String serviceName;
    private CountDownLatch latch;
    private Random rnd=new Random();

    public ServerService(CountDownLatch latch, String serviceName){
        this.latch=latch;
        this.serviceName=serviceName;
    }



    @Override
    public void run() {
        int startingIn=rnd.nextInt(10)*1000;
        try{
            logger.info(()->"starting service" + serviceName);
            Thread.sleep(startingIn);
            logger.info(()->"service"+serviceName + "starts in "+startingIn/1000 );

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        finally {
            latch.countDown();
            logger.info(()->"serive" + serviceName);
        }
    }
}
