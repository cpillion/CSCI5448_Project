package com.csci5448.pages.user_pages;


import com.csci5448.accounts.UserAccount;
import com.csci5448.content.Sport;
import com.csci5448.content.SportItem;
import com.csci5448.control.Controller;
import com.csci5448.data.SessionManager;
import com.csci5448.pages.Page;
import org.hibernate.Session;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FavoritesPage<T extends SportItem> extends ViewCollectionsPage<T> {

    private static final String DELETE_FAVORITE_ITEM = "delete";

    private final UserAccount userAccount;
    private final Consumer<T> addFavorite;
    private final Consumer<T> deleteFavorite;
    private final Function<T, Page> getPage;

    public FavoritesPage(Sport sport, UserAccount userAccount, Set<T> favorites, Consumer<T> addFavorite,
                         Consumer<T> deleteFavorite, Function<T, Page> getPage) {
        super(favorites.stream().filter(favorite ->  favorite.getSport() == sport).collect(Collectors.toList()),
                SportItem::getName, getPage);

        this.userAccount = userAccount;
        this.addFavorite = addFavorite;
        this.deleteFavorite = deleteFavorite;
        this.getPage = getPage;

        super.addPageAction(DELETE_FAVORITE_ITEM, this::deleteFavoriteItemAction);
    }

    private void deleteFavoriteItemAction(String itemName) {
        T favoriteItem = super.getItem(itemName);
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
        super.removeItem(favoriteItem);
        displayPage();
    }

    public void displayPage() {
        System.out.println("Your favorites are:");
        System.out.println(super.toString());
        System.out.println("\nType \'" + DELETE_FAVORITE_ITEM + " <name>\' to delete an entry from your favorites,\n" +
                "or type the name of one of your favorite items to view more information about it.");
    }

}
