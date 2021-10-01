package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.logging.Logger;

public class ExchangerProblem {

    private static int BASKET_CAPACITY=5;
    private static Exchanger<List<String>> exchanger=new Exchanger<List<String>>();
    private static int MAX_PROD_TIME_MS=2*1000;
    private static Random rnd=new Random();
    private static volatile boolean runningProducer;





    public static class Producer implements Runnable{
        private List<String> basket=new ArrayList<>(BASKET_CAPACITY);
        private Logger logger;
        @Override
        public void run() {
            while(runningProducer){
                try{
                    for(int i=0;i<BASKET_CAPACITY;i++){
                        String bulb="bulb"+rnd.nextInt(1000);
                        Thread.sleep(rnd.nextInt(MAX_PROD_TIME_MS));
                        basket.add(bulb);
                        logger.info(()->"added bulb "+bulb);
                    }
                    logger.info("waiting to exchange baskets");
                    basket=exchanger.exchange(basket);

                } catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                    logger.severe(()-> String.valueOf(ex));
                    break;
                }
            }
        }
    }



    public static class Consumer implements Runnable{
        private List<String> basket=new ArrayList<>(BASKET_CAPACITY);
        private static int MAX_CONS_TIME_MS=5*1000;
        private static final Random rnd=new Random();
        private Logger logger;
        private static volatile boolean runningConsumer;
        @Override
        public void run() {
            while(runningConsumer){
                try{
                    logger.info(()->"received+"+basket);
                    basket =exchanger.exchange(basket);
                    logger.info(()->"received"+basket);
                    for(String bulb:basket){
                        if(bulb!=null){
                            Thread.sleep(rnd.nextInt(MAX_CONS_TIME_MS));
                            logger.info(()->"packed"+bulb);
                        }
                    }
                    basket.clear();
                }catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                    logger.severe(()->"Exception" + ex);
                    break;
                }
            }
        }
    }
}
