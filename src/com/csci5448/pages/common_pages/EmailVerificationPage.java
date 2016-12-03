package com.csci5448.pages.common_pages;

import com.csci5448.accounts.Account;
import com.csci5448.accounts.JournalistAccount;
import com.csci5448.control.Controller;
import com.csci5448.control.EmailControl;
import com.csci5448.data.SessionManager;
import com.csci5448.pages.Page;
import org.hibernate.Session;

import javax.mail.MessagingException;
import java.math.BigInteger;
import java.util.Random;

public class EmailVerificationPage extends Page {

    static final String RESEND_EMAIL_ID = "resend";

    private final Account account;
    private final Page lobbyPage;

    public EmailVerificationPage(Account account, Page lobbyPage) {
        this.account = account;
        this.lobbyPage = lobbyPage;
        super.addPageAction(RESEND_EMAIL_ID, this::sendVerificationEmailAction);
    }

    private void sendVerificationEmailAction(String arg) {
        final String generatedCode = new BigInteger(32, new Random()).toString(32);
        super.addPageAction(generatedCode, this::codeEnteredCorrectlyAction);

        System.out.println("\tSending verification email...");

        try {
            EmailControl.getEmailControl().sendEmail(account.getUsername(), "ESPNGen Account Verification",
                    "Verification code: " + generatedCode);
        } catch (MessagingException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("\tPlease enter the code included in the email.\n\tIf you need a new code sent to you," +
                " please type \'" + RESEND_EMAIL_ID + "\'.");
        System.out.print("\tCode: ");
    }

    private void codeEnteredCorrectlyAction(String arg) {
        try (Session session = Controller.sessionFactory.openSession()) {
            account.setActivated(true);
            if (!SessionManager.getSessionManager().performOp(session, session::update, account)) {
                account.setActivated(false);
                return;
            }
        }

        System.out.println("\n\tThank you for verifying your email.");
        if (account instanceof JournalistAccount) {
            System.out.println("\tA system admin will verify your profession shortly. Once this happens," +
                    " you will be able to write and submit news articles for approval.");
            try {
                EmailControl.getEmailControl().sendSelfEmail("ESPNGen Journalist Profession Verification",
                        "A new Journalist Account has been created for " + account.getUsername() + "!\n" +
                                "If you would like to approve this person's profession, please update the journalist database.");
            } catch (MessagingException e) {
                e.printStackTrace();
                return;
            }
        }

        System.out.println("\tYou are now being taken to the lobby page.");

        Controller.setCurrentAccount(account);
        Controller.setCurrentPage(lobbyPage);
    }

    @Override
    public void displayPage() {
        System.out.println("\tAn email will be sent to " + account.getUsername());
    }

}
