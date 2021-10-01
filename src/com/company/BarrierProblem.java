package com.company;

import java.util.concurrent.CyclicBarrier;
import java.util.logging.Logger;

public class BarrierProblem {

    public class ServerInstance implements Runnable{
        private  Logger logger= Logger.getLogger(ServerInstance.class.getName());
        private Runnable barrierAction=()-> logger.info("services sratr");
        private CyclicBarrier barrier=new CyclicBarrier(3,barrierAction);



        @Override
        public void run() {
            logger.info("server starts");
            logger.info("starting");
            long starting=System.currentTimeMillis();
            Thread service1=new Thread(new ServerService(barrier,"HTTPListeners"));
            Thread service2=new Thread(new ServerService(barrier,"JMX"));
            Thread service3=new Thread(new ServerService(barrier,"Connectors"));
            service1.start();
            service2.start();
            service3.start();
            try{
                service1.join();
                service2.join();
                service3.join();
                logger.info("server started in " + System.currentTimeMillis());
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
                logger.severe(()->"Exception" + e);
            }
        }
    }



}
