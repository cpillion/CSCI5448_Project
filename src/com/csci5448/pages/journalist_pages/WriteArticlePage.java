package com.csci5448.pages.journalist_pages;

import com.csci5448.accounts.Account;
import com.csci5448.content.News;
import com.csci5448.content.Sport;
import com.csci5448.content.SportFactory;
import com.csci5448.control.Controller;
import com.csci5448.pages.Page;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Scanner;

public class WriteArticlePage extends Page {

    private Sport sport;
    public static final String SUBMIT_ARTICLE_ID = "submit_article";

    public WriteArticlePage() {
        sport = Sport.FOOTBALL;
        super.addPageAction(SUBMIT_ARTICLE_ID, this::submitArticleAction);
    }

    private void submitArticleAction(News news) {
        System.out.println("Your news has been submitted.");
        System.out.println("Sport: " + news.getSport() +
                            "\nHeadline: " + news.getHeadline() +
                            "\nAuthor: " + news.getAuthor() +
                            "\nBody: " + news.getBody());

        try (Session session = Controller.sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            try {
                session.save(news);
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
            }
        }

    }

    private void selectSport() {
        Sport allSports[] = Sport.values();
        for (Sport sportI : allSports) {
            System.out.println("   " + sportI);
        }
        System.out.print("   Selection: ");
        Scanner scanner = new Scanner(System.in);
        String currentSport = scanner.next();
        sport = SportFactory.chooseSport(currentSport);
        System.out.println("Current Sport Selection: " + sport.toString());
    }

    public void displayPage() {
        System.out.println("Thank you for your interest in contributing to ESP-NGen News!\n\n" +
                           "Please fill out each section when prompted and press Enter to move to the next section.");
        // Select the sport to write about
        System.out.println("Select the sport the news will be related to:");
        selectSport();

        Scanner scanner = new Scanner(System.in);
        // Write Headline
        System.out.println("Write the headline for your article:");
        String newsHeadline = scanner.nextLine();
        // Write Author Name
        System.out.println("Author's Name:");
        String author = scanner.nextLine();
        // Write article story
        System.out.println("Write the body of your news article. Press Enter when finished.");
        String newsBody = scanner.nextLine();
        News addedNews = new News(sport, newsHeadline, author, newsBody);

        System.out.println("Please review your submission: When you are finished, type \"done\"");
        while (true) {
            if (scanner.nextLine().equalsIgnoreCase("done")) {
                Controller.sendCommandToPage(SUBMIT_ARTICLE_ID, addedNews);
                break;
            }
        }
    }

}
