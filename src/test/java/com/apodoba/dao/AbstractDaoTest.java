package com.apodoba.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

public class AbstractDaoTest {

	private static SessionFactory sessionFactory = null;

    private static Session session = null;

    private Transaction tx = null;

    @BeforeClass
    public static void beforeClass() {
        HibernateUtil.init();
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Before
    public void before() {
    	session = sessionFactory.openSession();
        tx = session.beginTransaction();
    }

    @After
    public void after() {
        tx.rollback();
        session.clear();
        session.close();
    }

    @AfterClass
    public static void afterClass() {
        sessionFactory.close();
    }

    protected Session getCurrentSession() {
        return session;
    }
}
