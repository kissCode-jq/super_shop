package com.cache.sun.service;


import com.cache.sun.bean.UserBean;
import com.cache.sun.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserBean> findAll(String loginName,String userPassword) {
        return userMapper.findAll(loginName,userPassword);
    }

    @Override
    public List<UserBean> getUserList(String query,int pageNum, int pagesize) {
        return userMapper.getUserList(query,pageNum,pagesize);
    }

    @Override
    public List<UserBean> getUserList2(int pageNum, int pagesize) {
        return userMapper.getUserList2(pageNum,pagesize);
    }


    @Override
    public Integer upUserStatus(Boolean status,String id) {
        return userMapper.upUserStatus(status,id);
    }

    @Override
    public Integer addUser(String uuid,String userName, int age, String loginName, String password) {
        return userMapper.addUser(uuid,userName,age,loginName,password);
    }

    @Override
    public List<UserBean> getUser(String id) {
        return userMapper.getUser(id);
    }

    @Override
    public Integer upUser(String loginName, int age, String password, String id) {
        return userMapper.upUser(loginName,age,password,id);
    }

    @Override
    public Integer delUser(String id) {
        return userMapper.delUser(id);
    }


}
