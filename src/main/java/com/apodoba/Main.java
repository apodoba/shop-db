package com.apodoba;

import org.hibernate.Session;

import com.apodoba.dao.HibernateUtil;
import com.apodoba.dao.ShopDao;
import com.apodoba.dao.ShopDaoImpl;
import com.apodoba.domain.UserEntity;

public class Main {

    public static void main(String[] args) {

        HibernateUtil.init();

        Session session = HibernateUtil.getSessionFactory().openSession();

        ShopDao shopDao = new ShopDaoImpl(session);
        for (UserEntity user : shopDao.getAllUsers()) {
            System.out.println(user.getEmail());
        }

        session.close();
    }
}
