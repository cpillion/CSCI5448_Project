package com.csci5448.pages;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class PageAction<T> {

    private final Consumer<T> consumer;
    private final BiFunction<String, String, T> transformer;

    public PageAction(Consumer<T> consumer, BiFunction<String, String, T> transformer) {
        this.consumer = consumer;
        this.transformer = transformer;
    }

    public void accept(String command, String argument) {
        consumer.accept(transformer.apply(command, argument));
    }

}
