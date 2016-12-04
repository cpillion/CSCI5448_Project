package com.csci5448.pages.journalist_pages;

import com.csci5448.content.News;
import com.csci5448.content.Sport;
import com.csci5448.content.SportFactory;
import com.csci5448.control.Controller;
import com.csci5448.control.EmailControl;
import com.csci5448.data.SessionManager;
import com.csci5448.pages.Page;
import org.hibernate.Session;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class WriteArticlePage extends Page {

    private enum Stage {
        SPORT, HEADLINE, AUTHOR, BODY, SUBMIT;

        @Override
        public String toString() {
            String stage = super.toString();
            return stage.substring(0,1).toUpperCase() + stage.substring(1).toLowerCase();
        }
    };

    private static final String SUBMIT_ARTICLE_ID = "submit_article";

    private Sport sport;
    private String headline;
    private String author;
    private String body;
    private Stage currentStage;

    private final Map<Stage, ArticleStage> stageMap;

    public WriteArticlePage() {
        this.currentStage = Stage.SPORT;
        stageMap = new HashMap<>();
        stageMap.put(Stage.SPORT, new ArticleStage(this::sportStage, null));
        stageMap.put(Stage.HEADLINE, new ArticleStage(this::headlineStage,  "\tPlease write the headline of the article."));
        stageMap.put(Stage.AUTHOR, new ArticleStage(this::authorStage, "\tPlease enter the author's name."));
        stageMap.put(Stage.BODY, new ArticleStage(this::bodyStage, "\tPlease write the body of the article."));
        stageMap.put(Stage.SUBMIT, new ArticleStage(null, "\tPlease type \'" + SUBMIT_ARTICLE_ID + "\' to submit this article."));
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

    private void sportStage(String sportName) {
        Sport sport = SportFactory.chooseSport(sportName);
        if (sport == null) {
            return;
        }
        this.sport = sport;
        setCurrentStage(Stage.HEADLINE);
    }

    private void headlineStage(String headline) {
        this.headline = headline;
        setCurrentStage(Stage.AUTHOR);
    }

    private void authorStage(String author) {
        this.author = author;
        setCurrentStage(Stage.BODY);
    }

    private void bodyStage(String body) {
        this.body = body;
        setCurrentStage(Stage.SUBMIT);
        super.addPageAction(SUBMIT_ARTICLE_ID, this::submitArticleAction);
    }

    private void setCurrentStage(Stage stage) {
        this.currentStage = stage;
        ArticleStage articleStage = stageMap.get(stage);
        if (articleStage == null || articleStage.getStageMessage() == null) {
            return;
        }
        System.out.println(articleStage.getStageMessage());
    }

    @Override
    public void performDefaultAction(String identifier, String arg) {
        ArticleStage articleStage = stageMap.get(currentStage);
        if (articleStage == null || articleStage.getStageAction() == null) {
            return;
        }
        articleStage.getStageAction().accept(String.join(" ", identifier, arg).trim());
    }

    public void displayPage() {
        System.out.println("\n\tThank you for your interest in contributing to ESP-NGen News!\n");
        System.out.println("\tPlease type the name of the sport that the article will be about.");
    }

    private class ArticleStage {

        private final Consumer<String> stageAction;
        private final String stageMessage;

        public ArticleStage(Consumer<String> stageAction, String stageMessage) {
            this.stageAction = stageAction;
            this.stageMessage = stageMessage;
        }

        public Consumer<String> getStageAction() {
            return stageAction;
        }

        public String getStageMessage() {
            return stageMessage;
        }
    }

}
