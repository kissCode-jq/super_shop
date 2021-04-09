package com.cache.sun.service;



import com.cache.sun.bean.MenuBean;
import com.cache.sun.bean.UserBean;
import com.cache.sun.mapper.MenuMapper;

import com.cache.sun.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService implements MenuMapper {

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<MenuBean> getMenu() {
        return menuMapper.getMenu();
    }

    /*@Override
    public void insertUser(UserBean userBean) {
        userMapper.insertUser(userBean);
    }/*/
}
