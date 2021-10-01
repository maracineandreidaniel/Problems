package com.company;

import ThreadStates.*;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExchangerProblem exchangerProblem=new ExchangerProblem();
        ExchangerProblem.Consumer consumer=new ExchangerProblem.Consumer();
        consumer.run();
        ExchangerProblem.Producer producer=new ExchangerProblem.Producer();
        producer.run();

    }
}
