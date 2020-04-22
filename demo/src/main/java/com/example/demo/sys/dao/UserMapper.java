package com.example.demo.sys.dao;

import com.example.demo.sys.entity.UserEntity;

public interface UserMapper {
    int deleteByPrimaryKey(Long user_id);

    int insert(UserEntity record);

    int insertSelective(UserEntity record);

    UserEntity selectByPrimaryKey(Long user_id);

    int updateByPrimaryKeySelective(UserEntity record);

    int updateByPrimaryKey(UserEntity record);
}