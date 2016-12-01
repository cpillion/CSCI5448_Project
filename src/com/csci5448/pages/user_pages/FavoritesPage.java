package com.csci5448.pages.user_pages;


import com.csci5448.accounts.UserAccount;
import com.csci5448.content.Sport;
import com.csci5448.content.SportItem;
import com.csci5448.control.Controller;
import com.csci5448.data.SessionManager;
import com.csci5448.pages.Page;
import org.hibernate.Session;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FavoritesPage<T extends SportItem> extends Page {

    public static final String DELETE_FAVORITE_ITEM = "delete";
    public static final String VIEW_FAVORITE_ITEM = "view";

    private final UserAccount userAccount;
    private final List<T> favorites;
    private final Consumer<T> addFavorite;
    private final Consumer<T> deleteFavorite;
    private final Function<T, Page> getPage;

    public FavoritesPage(Sport sport, UserAccount userAccount, Set<T> favorites, Consumer<T> addFavorite,
                         Consumer<T> deleteFavorite, Function<T, Page> getPage) {
        this.userAccount = userAccount;
        this.favorites = favorites.stream().filter(favorite ->
                favorite.getSport() == sport).collect(Collectors.toList());
        this.addFavorite = addFavorite;
        this.deleteFavorite = deleteFavorite;
        this.getPage = getPage;

        super.addPageAction(DELETE_FAVORITE_ITEM, this::deleteFavoriteItemAction);
        super.addPageAction(VIEW_FAVORITE_ITEM, this::viewFavoriteItemAction);
    }

    private void deleteFavoriteItemAction(String itemName) {
        T favoriteItem = getFavoriteItem(itemName);
        if (favoriteItem == null) {
            return;
        }

        deleteFavorite.accept(favoriteItem);
        try (Session session = Controller.sessionFactory.openSession()) {
            if (!SessionManager.getSessionManager().performOp(session, session::update, userAccount)) {
                addFavorite.accept(favoriteItem);
                return;
            }
        }

        System.out.println(favoriteItem.getName() + " has been removed from your favorites.\n\n");
        favorites.remove(favoriteItem);
        displayPage();
    }

    private void viewFavoriteItemAction(String itemName) {
        T favoriteItem = getFavoriteItem(itemName);
        if (favoriteItem == null) {
            return;
        }

        Controller.setCurrentPage(getPage.apply(favoriteItem));
    }

    private T getFavoriteItem(String itemName) {
        List<T> filteredList = favorites.stream().filter(favorite ->
                favorite.getName().equalsIgnoreCase(itemName)).collect(Collectors.toList());

        if (filteredList.size() == 0) {
            return null;
        }
        return filteredList.get(0);
    }

    public void displayPage() {
        System.out.println("Your favorites are:");
        for (T favorite : favorites) {
            System.out.println("\t" + favorite.getName());
        }
        System.out.println("\nType \'" + DELETE_FAVORITE_ITEM + " <name>\' to delete an entry from your favorites,\n" +
                "or type \'" + VIEW_FAVORITE_ITEM + " <name>\' to view more information about a specific entry.");
    }

}
