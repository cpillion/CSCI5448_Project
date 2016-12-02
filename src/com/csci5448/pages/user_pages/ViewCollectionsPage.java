package com.csci5448.pages.user_pages;

import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class ViewCollectionsPage<T> extends Page {

    private final List<T> collection;
    private final Function<T, String> itemNameFunc;
    private final Function<T, Page> pageFunc;

    public ViewCollectionsPage(Function<Session, Query<T>> colQueryFunc, Function<T, String> itemNameFunc,
                               Function<T, Page> pageFunc) {
        this.itemNameFunc = itemNameFunc;
        this.pageFunc = pageFunc;
        try (Session session = Controller.sessionFactory.openSession()) {
            Query<T> colQuery = colQueryFunc.apply(session);
            collection = colQuery.list();
        }
        for (T item : collection) {
            super.addPageAction(itemNameFunc.apply(item), this::viewItemAction);
        }
    }

    public ViewCollectionsPage(List<T> collection, Function<T, String> itemNameFunc, Function<T, Page> pageFunc) {
        this.collection = collection;
        this.itemNameFunc = itemNameFunc;
        this.pageFunc = pageFunc;
        for (T item : collection) {
            super.addPageAction(itemNameFunc.apply(item), this::viewItemAction);
        }
    }

    private void viewItemAction(String[] itemNameArr) {
        String itemName = String.join(" ", itemNameArr);
        T item = getItem(itemName);
        if (item == null) {
            return;
        }

        Controller.setCurrentPage(pageFunc.apply(item));
    }

    public void removeItem(T item) {
        collection.remove(item);
    }

    public T getItem(String itemName) {
        List<T> filteredCollection = collection.stream().filter(item ->
                itemNameFunc.apply(item).equalsIgnoreCase(itemName)).collect(Collectors.toList());

        if (filteredCollection.size() == 0) {
            return null;
        }
        return filteredCollection.get(0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : collection) {
            sb.append("\t" + itemNameFunc.apply(item) + "\n");
        }
        return sb.toString();
    }

}
