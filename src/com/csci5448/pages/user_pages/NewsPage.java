package com.csci5448.pages.user_pages;

import com.csci5448.content.News;
import com.csci5448.content.Sport;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class NewsPage extends Page {

    private static String READ_ARTICLE_ID = "read_article";

    private final Sport sport;

    private List<News> articles;

    public NewsPage(Sport mySport) {
        sport = mySport;
        super.addPageAction(READ_ARTICLE_ID, this::readArticleAction);
        try (Session session = Controller.sessionFactory.openSession()) {
            Query<News> availableArticles = session.createQuery("FROM News WHERE sport=" + sport.ordinal() +
                    "AND approved=true", News.class);
            articles = availableArticles.list();
        }
    }

    private void readArticleAction(String input) {
        int i;
        try {
            i = Integer.parseInt(input)-1;
        } catch (NumberFormatException e) {
            return;
        }

        if (i < 0 || i >= articles.size()) {
            return;
        }
        News news = articles.get(i);
        System.out.println("\t" + news.getHeadline() +
                            "\n\tWritten by " + news.getAuthor() +
                            "\n\n\t" + news.getBody());
        inputPrompt();
    }

    public void displayPage() {
        makeNewPage("ESPNGen " + capitalize(sport.toString()) + " News");
        System.out.println("\tTo view an article, please type \"read_article\" followed by the article number.\n");

        if (articles.isEmpty()) {
            System.out.println("\nThere are no available articles for " +
                    sport.toString().toLowerCase()+ " at this time. Please check back later.");
        }
        else {
            // List Results for user to see
            System.out.println("\tAvailable articles related to " + capitalize(sport.toString()) + ":");
            for (int i = 0; i < articles.size(); i++) {
                System.out.println("\t\t" + (i+1) + ": \"" +
                        articles.get(i).getHeadline() + "\" by " + articles.get(i).getAuthor());
            }
        }
        inputPrompt();
    }
}
