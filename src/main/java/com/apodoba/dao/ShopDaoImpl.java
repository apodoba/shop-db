package com.apodoba.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.apodoba.domain.CategoryEntity;
import com.apodoba.domain.GoodsEntity;
import com.apodoba.domain.ImageEntity;
import com.apodoba.domain.UserEntity;

public class ShopDaoImpl implements ShopDao {

    private Session session;

    public ShopDaoImpl(Session session) {
       this.session = session;
    }

    public CategoryEntity getCategoryById(Long id) {
        return (CategoryEntity) session.get(CategoryEntity.class, id);
    }

    public GoodsEntity getGoodsById(Long id) {
        return (GoodsEntity) session.get(GoodsEntity.class, id);
    }

    public UserEntity getUserById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    public ImageEntity getImageById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    public void saveOrUpdate(CategoryEntity category) {
        session.saveOrUpdate(category);
    }

    public void saveOrUpdate(GoodsEntity goods) {
        session.saveOrUpdate(goods);
    }

    public void saveOrUpdate(UserEntity user) {
        // TODO Auto-generated method stub
        
    }

    public void saveOrUpdate(ImageEntity image) {
        // TODO Auto-generated method stub
        
    }

    @SuppressWarnings("unchecked")
    public List<UserEntity> getAllUsers(){
        return session.createCriteria(UserEntity.class).list();
    }

    public UserEntity getUserByEmail(String email){
        return (UserEntity) session.createCriteria(UserEntity.class).add(Restrictions.eq("email", email)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
	public List<CategoryEntity> getCategoriesWithParent(Long parentCategotyId) {
        Query query = session.getNamedQuery(CategoryEntity.CATEGORIES_WITH_PARENT);
        query.setParameter("categoryId", parentCategotyId);
        return query.list();
    }

}
