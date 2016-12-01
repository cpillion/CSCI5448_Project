package com.csci5448.pages.user_pages;

import com.csci5448.content.News;
import com.csci5448.content.Sport;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class NewsPage extends Page {

    public static String READ_ARTICLE_ID = "read_article";
    private final Sport sport;

    public NewsPage(Sport mySport) {
        sport = mySport;
        super.addPageAction(READ_ARTICLE_ID, this::readArticleAction);
    }

    private void readArticleAction(News news) {

    }

    public void displayPage() {
        System.out.println("Welcome to ESPNGen News!\n" +
                            "To view an article, please type \"read_article\" followed by the article headline.");

        try (Session session = Controller.sessionFactory.openSession()) {
            Query availableArticles = session.createQuery("FROM News WHERE sport=" + sport.ordinal());
            List<News> results = availableArticles.list();
            if (results.isEmpty()) {
                System.out.println("There are no available articles for " +
                        sport.toString().toLowerCase()+ " at this time. Please check back later.");
            }
            for (int i = 0; i<results.size(); i++){
                System.out.println(results.get(i).getHeadline());
            }
        }
    }

}
