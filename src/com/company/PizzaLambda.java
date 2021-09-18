package com.company;

import java.util.function.Consumer;

public class PizzaLambda  {
    public void make(Pizza pizza, Consumer<Pizza> addTopIngredients ){
        makeDough(pizza);
        addTopIngredients.accept(pizza);
        bake(pizza);
    }

    private void bake(Pizza pizza) {
    }

    private void makeDough(Pizza pizza) {
    }
}
