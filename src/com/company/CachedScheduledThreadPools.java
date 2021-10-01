package com.company;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.logging.Logger;

public class CachedScheduledThreadPools {

    private static volatile boolean runningProducer;
    private static volatile boolean runningConsumer;
    private static ExecutorService producerService;
    private static final Producer producer=new Producer();
    private static Random rnd=new Random();
    private static final int MAX_PROD_TIME_MS=1*1000;
    private static final int MAX_CONS_TIME_MS=10*1000;
    private static TransferQueue<String> queue=new LinkedTransferQueue<>();
    private static int extraProdTime;
    private static Logger logger;

    private static class Producer implements Runnable{

        @Override
        public void run() {
            while(runningProducer){
                try{
                    String bulb="bulb-"+rnd.nextInt(1000);
                    Thread.sleep(rnd.nextInt(MAX_PROD_TIME_MS)+extraProdTime);
                    queue.offer(bulb);
                    logger.info(()->"Checked"+bulb);
                }
                catch(InterruptedException ex){
                    Thread.currentThread().interrupt();
                    logger.severe(()->"exception " +ex);
                    break;
                }
            }
        }
    }


    private static class Consumer implements Runnable{

        @Override
        public void run() {
            while(runningConsumer && queue.size()>0){
                try{
                    String bulb=queue.poll(MAX_PROD_TIME_MS+extraProdTime, TimeUnit.MILLISECONDS);
                    if(bulb!=null) {
                        Thread.sleep(rnd.nextInt(MAX_CONS_TIME_MS));
                        logger.info(() -> "packed " + bulb + Thread.currentThread().getName());

                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    logger.severe(()->"exception" + e);
                    break;
                }
            }
        }
    }



}
