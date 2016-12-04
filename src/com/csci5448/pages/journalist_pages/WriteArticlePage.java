package com.csci5448.pages.journalist_pages;

import com.csci5448.content.News;
import com.csci5448.content.Sport;
import com.csci5448.content.SportFactory;
import com.csci5448.control.Controller;
import com.csci5448.control.EmailControl;
import com.csci5448.data.SessionManager;
import com.csci5448.pages.Page;
import com.csci5448.pages.PageDisplay;
import org.hibernate.Session;

import javax.mail.MessagingException;

public class WriteArticlePage extends Page {

    private static final String SUBMIT_ARTICLE_ID = "submit_article";
    private static final String SPORT_ID =  "sport";
    private static final String HEADLINE_ID = "headline";
    private static final String AUTHOR_ID = "author";
    private static final String BODY_ID = "body";

    private Sport sport;
    private String headline;
    private String author;
    private String body;

    public WriteArticlePage() {
        super.addPageAction(SUBMIT_ARTICLE_ID, this::submitArticleAction);
        super.addPageAction(SPORT_ID, this::selectSportAction);
        super.addPageAction(HEADLINE_ID, this::headlineAction);
        super.addPageAction(AUTHOR_ID, this::authorAction);
        super.addPageAction(BODY_ID, this::bodyAction);
    }

    private void submitArticleAction(String arg) {
        if (sport == null || headline == null || author == null || body == null) {
            System.out.println("\tPlease ensure that you have set the sport, headline, author," +
                    " and body before submitting.");
            return;
        }

        News news = new News(sport, headline, author, body, false);

        try (Session session = Controller.sessionFactory.openSession()) {
            if (!SessionManager.getSessionManager().performOp(session, session::save, news)) {
                return;
            }
        }

        try {
            EmailControl.getEmailControl().sendSelfEmail("ESPNGen Article Submission Ready For Approval",
                            "A new article has been submitted by " + news.getAuthor() + "!\n" +
                            "If you would like to approve this article, please update the news database.");
        } catch (MessagingException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("\n\tYour news has been submitted for approval.");
        System.out.println("\t\tSport: " + news.getSport() +
                "\n\t\tHeadline: " + news.getHeadline() +
                "\n\t\tAuthor: " + news.getAuthor() +
                "\n\t\tBody: " + news.getBody());
        Controller.goToLobbyPage();
    }

    private void selectSportAction(String sport) {
        Sport selectedSport = SportFactory.chooseSport(sport);
        if (selectedSport != null) {
            System.out.println("\t" + selectedSport + " has been selected.");
            this.sport = selectedSport;
            PageDisplay.getPageDisplay().showInputPrompt();
        }
    }

    private void headlineAction(String headline) {
        System.out.println("\tHeadline successfully set.");
        this.headline = headline;
        PageDisplay.getPageDisplay().showInputPrompt();
    }

    private void authorAction(String author) {
        System.out.println("\tAuthor successfully set.");
        this.author = author;
        PageDisplay.getPageDisplay().showInputPrompt();
    }

    private void bodyAction(String body) {
        System.out.println("\tBody successfully set.");
        this.body = body;
        PageDisplay.getPageDisplay().showInputPrompt();
    }

    public void displayPage() {
        System.out.println("\n\tThank you for your interest in contributing to ESP-NGen News!\n");
        System.out.println("\tPlease use the following commands to write your article: \n\t\t\'" +
                SPORT_ID + " <sport>\' to select the sport the article will be " +
                "about,\n\t\t" + "\'" + AUTHOR_ID + " <author>\' to specify the author of the article,\n\t\t\'" +
                HEADLINE_ID + " <headline>\' to specify " +
                "the headline of the article,\n\t\t\'" + BODY_ID + " <body>\' to specify the body of the article.\n\t" +
                "Finally, please type \'" + SUBMIT_ARTICLE_ID + "\' to submit your article.");
        PageDisplay.getPageDisplay().showInputPrompt();
    }

}
