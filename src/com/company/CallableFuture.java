package com.company;

import java.beans.IntrospectionException;
import java.util.Random;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class CallableFuture {
    private static int MAX_PROD_TIME_MS=5*1000;
    private static int MAX_CONS_TIME_MS=7*1000;
    private static int TIMEOUT_MS=MAX_CONS_TIME_MS+1000;
    private static Random rnd=new Random();
    private static volatile boolean runningProducer;
    private static TransferQueue<String> queue=new LinkedTransferQueue<>();
    private static Logger logger;
    private static ExecutorService producerService;
    public static ExecutorService consumerService;

    private static class Producer implements Callable {
    private  String bulb;
        private Object DefectBulbException;

        private Producer(String bulb){
        this.bulb=bulb;
    }
        @Override
        public Object call() throws Exception {
                if(runningProducer){
                    Thread.sleep(rnd.nextInt(MAX_PROD_TIME_MS));
                    if(rnd.nextInt(100)<5){
                        try {
                            throw new DefectBulbException("Defect" + bulb);
                        } catch (DefectBulbException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        logger.info(()->"checked" + bulb);
                    }
                   return bulb;
                }
                return "";
            }

        }


        private static class Consumer implements Runnable{

            private String bulb;

            private Consumer(String bulb){
                this.bulb=bulb;
            }

            @Override
            public void run() {
                if(runningProducer){

                        try {
                            Thread.sleep(rnd.nextInt(MAX_CONS_TIME_MS));
                            logger.info(()->"packed"+bulb);
                        } catch (InterruptedException e) {
                           Thread.currentThread().interrupt();
                        }
                    }
                }

                public static void startAssemblyLine(){
                runningProducer=true;
                consumerService= Executors.newSingleThreadExecutor();
                runningProducer=true;
                producerService=Executors.newSingleThreadExecutor();

                }
            }
        }




