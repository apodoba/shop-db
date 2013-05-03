package com.apodoba.dao;

import java.util.List;

import com.apodoba.domain.CategoryEntity;
import com.apodoba.domain.GoodsEntity;
import com.apodoba.domain.ImageEntity;
import com.apodoba.domain.UserEntity;

public interface ShopDao {

    CategoryEntity getCategoryById(Long id);
    GoodsEntity getGoodsById(Long id);
    UserEntity getUserById(Long id);
    ImageEntity getImageById(Long id);

    void saveOrUpdate(CategoryEntity category);
    void saveOrUpdate(GoodsEntity goods);
    void saveOrUpdate(UserEntity user);
    void saveOrUpdate(ImageEntity image);
    /**
     * 
     * @return
     */
    List<UserEntity> getAllUsers();

    UserEntity getUserByEmail(String email);

    List<CategoryEntity> getCategoriesWithParent(Long parentCategotyId);
}
