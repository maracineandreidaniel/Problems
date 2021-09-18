package com.company;

import java.beans.BeanProperty;
import java.util.Comparator;

@Family
public class Caine {
    @Family
    private String nume;
    private int varsta;
    private String culoare;
    public boolean jucaus;

    @BeanProperty
    public int getVarsta() {
        return varsta;
    }

    public String getCuloare() {
        return culoare;
    }

    public String getNume() {
        return nume;
    }

    public Caine(String nume, int varsta){
        this.nume=nume;
        this.varsta=varsta;
    }

    public Caine(String nume, String culoare){
        this.nume=nume;
        this.culoare=culoare;
    }



    public void alearga(){
        System.out.println("cainele alearga");
    }

    public void mananca(String fel, int nr){
        System.out.println("mananca si el");
    }

    public static void priveste(){
        System.out.println("catelusul priveste");
    }


    public int compare(Caine o1, Caine o2) {
        return Integer.compare(o1.getVarsta(),o2.getVarsta());
    } //metoda sintetica bridge


    //trebuia ca, clasa sa implementeze Comparator si la compare
    //sa fie un @Override, dar ma incurca mai departe


    public class Blana{
        private String culoareBlana;
    }




}
