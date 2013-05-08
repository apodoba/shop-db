package com.apodoba;

import org.hibernate.Session;

import com.apodoba.dao.HibernateUtil;
import com.apodoba.dao.ShopDao;
import com.apodoba.dao.ShopDaoImpl;

public class Connect {
	private static Session session;
    
	
	public static Session getSession(){
		HibernateUtil.init();
        session = HibernateUtil.getSessionFactory().openSession();
        return session;		
	}
	
	public static void closeSession(){
		if(session.isOpen()){
			session.close();
		}				
	}
	
	public static ShopDao getDao(){
		ShopDao shopDao = null;
		if(session != null && session.isOpen() && session.isConnected()){
			shopDao = new ShopDaoImpl(session);
		}else{
			shopDao = new ShopDaoImpl(getSession());
		}
		
		return shopDao;
	}

}
