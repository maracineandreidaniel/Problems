package com.company;



import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProblemsTest {


    @Test
    public void lambdaTest(){
        List<String> names= Arrays.asList("ion7","adi8","and3i");
        List<String> rez=Problems.replace(names,(String s)->s.replaceAll("\\d",""));
        assertEquals(rez,Arrays.asList("ion","adi","andi"));
    }

    @Test
    public void testInterface(){
        Function<String,String> f1=(String s)->s.toUpperCase();
        Function<String,String> f2=(String s)->s.concat("done");
        Function<String,String> f=Problems.reduceStrings(f1,f2);
        assertEquals("TEST DONE",f.apply("test"));
    }

    @Test
    public void testFirstLast(){
        String t="asdsdadas";
        String result=Problems.firstLastChar.apply(t);
        assertEquals(result,"as");
    }


@Test
    public void testRndString(){
        String s1="catel";
        String res1=Problems.extractRandChar(s1);
        assertEquals(res1.length(),1);

}








}