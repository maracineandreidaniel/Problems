package com.company;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class LatchesProblems {

    public class ServerInstance implements Runnable{

        private  final Logger logger=Logger.getLogger(com.company.ServerInstance.class.getName());
        private final CountDownLatch latch=new CountDownLatch(3);


        @Override
        public void run() {
            logger.info("the server starts");
            logger.info("starting");
            long starting=System.currentTimeMillis();
            Thread service1=new Thread(new ServerService(latch,"HTTP Listeners"));
            Thread service2=new Thread(new ServerService(latch,"JMX"));
            Thread service3=new Thread(new ServerService(latch,"Connectors"));
            service1.start();
            service2.start();
            service3.start();

            try{
                latch.await();
                logger.info(()->"server started in");
            }catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }

        }



        public class ServerService implements Runnable {
            private  final Logger logger=Logger.getLogger(com.company.ServerService.class.getName());
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

    }

}
