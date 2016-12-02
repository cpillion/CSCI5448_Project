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
        super.addPageActionString(READ_ARTICLE_ID, this::readArticleAction);
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
        System.out.println(news.getHeadline() + "\nWritten by " + news.getAuthor() + "\n\n" + news.getBody());
    }

    public void displayPage() {
        System.out.println("Welcome to ESPNGen News!\n" +
                            "To view an article, please type \"read_article\" followed by the article number.\n");

        if (articles.isEmpty()) {
            System.out.println("There are no available articles for " +
                    sport.toString().toLowerCase()+ " at this time. Please check back later.");
        }
        else {
            // List Results for user to see
            System.out.println("Available articles related to " + sport.toString().toLowerCase() + ":");
            for (int i = 0; i < articles.size(); i++) {
                System.out.println("\t" + (i+1) + ": \"" +
                        articles.get(i).getHeadline() + "\" by " + articles.get(i).getAuthor());
            }
        }
    }
}
