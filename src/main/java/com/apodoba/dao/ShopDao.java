package com.apodoba.dao;

import java.util.List;

import com.apodoba.domain.UserEntity;

public interface ShopDao {

    /**
     * 
     * @return
     */
    List<UserEntity> getAllUsers();
}
