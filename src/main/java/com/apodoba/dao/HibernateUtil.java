package com.apodoba.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static void init() {
        Configuration hibConfiguration = new Configuration().configure("hibernate.cfg.xml");

        ServiceRegistry serviceRegistry =
                new ServiceRegistryBuilder().
                applySettings(hibConfiguration.getProperties()).
                buildServiceRegistry();
        sessionFactory = hibConfiguration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
