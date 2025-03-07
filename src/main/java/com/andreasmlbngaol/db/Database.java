package com.andreasmlbngaol.db;

import com.andreasmlbngaol.utils.Config;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;
import java.util.function.Consumer;
import java.util.function.Function;

public class Database {
    private static SessionFactory sessionFactory;

    public static void init() {
        if (sessionFactory == null) {
            sessionFactory = buildSessionFactory();
        }
    }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            Properties settings = new Properties();
            settings.put(Environment.JAKARTA_JDBC_DRIVER, Config.getProperty("hibernate.connection.driver_class"));
            settings.put(Environment.JAKARTA_JDBC_URL, Config.getProperty("hibernate.connection.url"));
            settings.put(Environment.JAKARTA_JDBC_USER, Config.getProperty("hibernate.connection.username"));
            settings.put(Environment.JAKARTA_JDBC_PASSWORD, Config.getProperty("hibernate.connection.password"));
            settings.put(Environment.DIALECT, Config.getProperty("hibernate.dialect"));
            settings.put(Environment.SHOW_SQL, Config.getProperty("hibernate.show_sql"));
            settings.put(Environment.HBM2DDL_AUTO, Config.getProperty("hibernate.hbm2ddl.auto"));

            configuration.setProperties(settings);

            configuration.addAnnotatedClass(com.andreasmlbngaol.entity.UserEntity.class);
            configuration.addAnnotatedClass(com.andreasmlbngaol.entity.StudentEntity.class);
            configuration.addAnnotatedClass(com.andreasmlbngaol.entity.TeacherEntity.class);

            return configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("SessionFactory creation failed: " + e);
            throw new RuntimeException(e);
        }
    }

    public static void shutdown() {
        sessionFactory.close();
    }

    public static <R> R executeTransaction(Function<Session, R> action) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result;
            try {
                result = action.apply(session);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
            return result;
        }
    }

    public static void executeTransactionVoid(Consumer<Session> action) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                action.accept(session);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}