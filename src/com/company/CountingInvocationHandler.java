package com.company;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountingInvocationHandler implements InvocationHandler {
    private  Map<String,Integer> counter=new HashMap<>();
    private  Object targetObject;

    public CountingInvocationHandler(Object targetObject) {
        this.targetObject = targetObject;
        for (Method method : targetObject.getClass().getDeclaredMethods()) {
            this.counter.put(method.getName() + Arrays.toString(method.getParameterTypes()),0);
        }
    }





    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object resultOfInvocation=method.invoke(targetObject,args);
        counter.computeIfPresent(method.getName()+Arrays.toString(method.getParameterTypes()),(k,v)->+v);
        return resultOfInvocation;
    }

    public Map<String, Integer> countOf(String method){
        Map<String,Integer> result= (Map<String, Integer>) counter.entrySet().stream().filter(e->e.getKey().startsWith(method)).filter(e->e.getValue()!=0);
        return result;
    }
}
