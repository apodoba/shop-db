package com.apodoba.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.apodoba.domain.UserEntity;

public class ShopDaoImpl implements ShopDao {

    private Session session;

    public ShopDaoImpl(Session session) {
       this.session = session;
    }

    @SuppressWarnings("unchecked")
	public List<UserEntity> getAllUsers(){
        return session.createCriteria(UserEntity.class).list();
    }
    
    public UserEntity getUserByEmail(String email){
    	return (UserEntity) session.createCriteria(UserEntity.class).add(Restrictions.eq("email", email)).uniqueResult();
    }
}
