package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class Formula {
    private Scanner scanner;
    private double result;

    private Formula() throws IOException{
        result=0.0d;
        scanner=new Scanner(Path.of("fisier.txt"), StandardCharsets.UTF_8);

    }


    public static double compute(Function<Formula, Double> f) throws IOException{
        Scanner scanner;
        Formula formula=new Formula();
        double result=0.0d;
        result=f.apply(formula);
        return  result;
    }

    public Formula add(){
        if(scanner.hasNextDouble()){
            result+=scanner.nextDouble();
        }
        return this;
    }

    public Formula minus(){
        if(scanner.hasNextDouble())
            result-=scanner.nextDouble();
        return this;
    }

    public double getResult(){
        return  result;
    }






}
