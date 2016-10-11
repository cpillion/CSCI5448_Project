package com.csci5448.pages;

public abstract class PageAction<T> {

    private final String identifier;

    public PageAction(String identifier) {
        this.identifier = identifier;
    }

    public abstract void performAction(T arg);

    public String getIdentifier() { return identifier; }

}
