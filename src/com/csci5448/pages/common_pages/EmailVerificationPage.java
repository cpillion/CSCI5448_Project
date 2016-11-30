package com.csci5448.pages.common_pages;

import com.csci5448.accounts.Account;
import com.csci5448.accounts.JournalistAccount;
import com.csci5448.control.Controller;
import com.csci5448.control.EmailControl;
import com.csci5448.pages.Page;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.mail.MessagingException;
import java.math.BigInteger;
import java.util.Random;

public class EmailVerificationPage extends Page {

    public static final String RESEND_EMAIL_ID = "resend";

    private final Account account;
    private final Page lobbyPage;

    public EmailVerificationPage(Account account, Page lobbyPage) {
        this.account = account;
        this.lobbyPage = lobbyPage;
        super.addPageAction(RESEND_EMAIL_ID, this::sendVerificationEmailAction);
    }

    private void sendVerificationEmailAction(Object o) {
        System.out.println("Please enter the code included in the email.\nIf you need a new code sent to you," +
                " please type \'resend\'");
        System.out.print("Code: ");

        final String generatedCode = new BigInteger(32, new Random()).toString(32);
        super.addPageAction(generatedCode, this::codeEnteredCorrectlyAction);

        try {
            EmailControl.getEmailControl().sendEmail(account.getUsername(), "ESPNGen Account Verification",
                    "Verification code: " + generatedCode);
        } catch (MessagingException e) {
            e.printStackTrace();
            return;
        }
    }

    private void codeEnteredCorrectlyAction(Object o) {
        System.out.println("Thank you for verifying your email.");
        if (account instanceof JournalistAccount) {
            System.out.println("A system admin will verify your profession shortly. Once this happens," +
                    " you will be able to write and submit news articles for approval.");
            try {
                EmailControl.getEmailControl().sendEmail("espngen@gmail.com", "ESPNGen Journalist Profession Verification",
                        "A new Journalist Account has been created for " + account.getUsername() + "!\n" +
                                "If you would like to approve this person's profession, please update the journalist database.");
            } catch (MessagingException e) {
                e.printStackTrace();
                return;
            }
        }

        try (Session session = Controller.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            account.setActivated(true);
            try {
                session.update(account);
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
                return;
            }
        }

        System.out.println("You are now being taken to the lobby page. You may logout at any time by typing " +
                "\'" + Page.LOGOUT_ID + "\'. You may also return to this lobby at any time by typing " +
                "\'" + Page.HOME_ID + "\'.");

        Controller.setCurrentAccount(account);
        Controller.setCurrentPage(lobbyPage);
    }

    @Override
    public void displayPage() {
        System.out.println("An email will be sent to " + account.getUsername());
    }

}