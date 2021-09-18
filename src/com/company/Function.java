package com.company;

public interface Function<T,R> {
    static Function<String, String> identity() {
        return null;
    }


    R apply(T t);

    static Function<String, String> andThen(Function<String, String> function, Function<String, String> function1) {
        return null;
    }

}
