package com.apodoba.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.apodoba.domain.CategoryEntity;
import com.apodoba.domain.UserEntity;

public class ShopDaoImplTest extends AbstractDaoTest {

    @Test
    public void testFindCategoty() {
        ShopDao shopDao = new ShopDaoImpl(getCurrentSession());
        CategoryEntity categoryEntity = shopDao.getCategoryById(1L);
        Assert.assertNotNull(categoryEntity);
        Assert.assertEquals("Electronic", categoryEntity.getName());
        Assert.assertEquals(null, categoryEntity.getParentCategory());

        CategoryEntity childCategoryEntity = shopDao.getCategoryById(2L);
        Assert.assertNotNull(childCategoryEntity);
        Assert.assertEquals("Notebooks", childCategoryEntity.getName());
        Assert.assertEquals(categoryEntity, childCategoryEntity.getParentCategory());
    }

    @Test
    public void testPersistCategoty() {

    }

    @Test
    public void testGetAllUsers() {
        ShopDao shopDao = new ShopDaoImpl(getCurrentSession());
        List<UserEntity> entities = shopDao.getAllUsers();
        Assert.assertNotNull(entities);
        Assert.assertTrue("Shoul retriev at least one user", entities.size() > 0);
    }
    
    @Test
    public void testGetUserByEmail() {
    	String email = "admin@shop.com";
    	Long id = 1L;
        ShopDao shopDao = new ShopDaoImpl(getCurrentSession());
        UserEntity entity = shopDao.getUserByEmail(email);
        Assert.assertNotNull(entity);    
        Assert.assertEquals(id, entity.getId());
    }

}
