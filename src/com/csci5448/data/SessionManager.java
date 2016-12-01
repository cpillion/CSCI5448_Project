package com.csci5448.data;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.function.Consumer;

public class SessionManager {

    private static SessionManager sessionManager;

    public static SessionManager getSessionManager() {
        if (sessionManager == null) {
            sessionManager = new SessionManager();
        }
        return sessionManager;
    }

    public <T> boolean performOp(Transaction transaction, Consumer<T> op, T arg) {
        try {
            op.accept(arg);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

}
