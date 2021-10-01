package pb202;

import java.beans.IntrospectionException;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;
import java.util.logging.Logger;

public class AssemblyLine {

    private static int MAX_PROD_TIME_MS=5*1000;
    private static int MAX_CONS_TIME_MS=7*1000;
    private static int TIMEOUT_MS=MAX_CONS_TIME_MS+1000;
    private static Random rnd=new Random();
    private static volatile boolean runningProducer;
    private static TransferQueue<String> queue=new LinkedTransferQueue<>();
    private static Logger logger;
    private static ExecutorService producerService;
    public static ExecutorService consumerService;

    private AssemblyLine(){
        throw new AssertionError("There is a single assembly line");
    }


    private static class Producer implements Runnable{

        @Override
        public void run() {
            while(runningProducer){
                try{
                    String bulb="bulb-" + rnd.nextInt(100);

                    Thread.sleep(rnd.nextInt(MAX_PROD_TIME_MS));
                    boolean transfered=queue.tryTransfer(bulb,TIMEOUT_MS, TimeUnit.MILLISECONDS);
                    if(transfered){
                        logger.info(()->"Checked" + bulb);
                    }
                } catch(InterruptedException ex){
                    Thread.currentThread().interrupt();
                    logger.severe(()->"Exception" + ex);
                    break;
                }
            }
        }
    }



    private static volatile boolean runningConsummer;

    private static class Consumer implements Runnable{

        @Override
        public void run() {
            while(runningConsummer){

                try {
                    String bulb=  queue.poll(MAX_PROD_TIME_MS,TimeUnit.MILLISECONDS);

                    if(bulb!=null){
                        Thread.sleep(rnd.nextInt(MAX_PROD_TIME_MS));
                        logger.info(()->"Packed" + bulb);
                    }
                } catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                    logger.severe(()->"exception" + ex);
                    break;
                }
            }
        }
    }

    public static void stopAssemblyLine(){
        logger.info("stopping assembly line");
        boolean isProducerDown=shutdownProducer();
        boolean isConsumerDown=shutDownConsumer();

        if(!isProducerDown||!isConsumerDown){
            logger.severe("something happened");
            System.exit(0);
        }
        logger.info("assembly line was stopped");
    }

    private static boolean shutdownProducer(){
        runningProducer=false;
        return shutdownExecutor(producerService);
    }

    private static boolean shutDownConsumer(){
        runningConsummer=false;
        return shutdownExecutor(consumerService);
    }

    private static boolean shutdownExecutor(ExecutorService executor){
        executor.shutdown();
        try{
            if(!executor.awaitTermination(TIMEOUT_MS*2,TimeUnit.MILLISECONDS)){
                executor.shutdownNow();
                return executor.awaitTermination(TIMEOUT_MS*2,TimeUnit.MILLISECONDS);
            }
            return true;
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            logger.severe(()->"Exception" + e);
        }
        return false;
    }

    public static void fixedNrThreads(){
        Producer producer=new Producer();
        Consumer consumer=new Consumer();
        int nrProducers=3;
        int nrConsumers=2;
        producerService=Executors.newFixedThreadPool(nrProducers);
        for(int i=0;i<nrProducers;i++)
            producerService.execute(producer);

        consumerService=Executors.newFixedThreadPool(nrConsumers);
        for(int i=0;i<nrConsumers;i++)
            consumerService.execute(consumer);
    }

    private static int extraProdTime;








}
