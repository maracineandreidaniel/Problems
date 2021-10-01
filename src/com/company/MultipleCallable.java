package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class MultipleCallable {

    private static final BlockingQueue<String> queue=new LinkedBlockingQueue<>();
    private static final int MAX_PROD_BULBS = 7;
    private static int MAX_PROD_TIME_MS=5*1000;
    private static int MAX_CONS_TIME_MS=7*1000;
    private static int TIMEOUT_MS=MAX_CONS_TIME_MS+1000;
    private static Random rnd=new Random();
    private static volatile boolean runningProducer;
    private static Logger logger;
    private static ExecutorService producerService;
    public static ExecutorService consumerService;

    private static void simulatingProducers(){
        for(int i=0;i<MAX_PROD_BULBS;i++){
            queue.offer("bulb"+rnd.nextInt(1000));
        }
    }

    private static class Consumer implements Callable{

        @Override
        public Object call() throws Exception {
            String bulb=queue.poll();
            Thread.sleep(100);
            if(bulb!=null){
                logger.info(()->"packed"+bulb + Thread.currentThread().getName());
                return bulb;
            }
            return "";
        }
    }

    public static void execute() throws InterruptedException, ExecutionException {
        List<Callable<String>> tasks=new ArrayList<>();
        List<Future<String>> futures=consumerService.invokeAll(tasks);
        for(Future<String> future:futures){
            String bulb=future.get();
            logger.info(()->"future done"+bulb);
        }
    }



}
