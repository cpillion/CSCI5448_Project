package com.csci5448.pages;

import java.util.function.Consumer;
import java.util.function.Function;

public class PageAction<T> {

    private final Consumer<T> consumer;
    private final Function<String, T> transformer;

    public PageAction(Consumer<T> consumer, Function<String, T> transformer) {
        this.consumer = consumer;
        this.transformer = transformer;
    }

    public void accept(String argument) {
        consumer.accept(transformer.apply(argument));
    }

}
