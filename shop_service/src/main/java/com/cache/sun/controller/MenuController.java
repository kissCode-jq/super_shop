package com.cache.sun.controller;


import com.cache.sun.bean.MenuBean;
import com.cache.sun.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
     获取菜单栏
     */
    @GetMapping("/getMenu")
    public List getMenus(){
        Map<String,String> resMap=new HashMap<String,String>();


        List<MenuBean> userList=menuService.getMenu();
        Map[] menuArr = new Map[4];
        Map<String,String> map= new HashMap<String,String>();
        map.put("id","110");
        map.put("authName","人员维护");
        map.put("path","Users");
        menuArr[0]=map;
        Map<String,String> map2= new HashMap<String,String>();
        map2.put("id","111");
        map2.put("authName","权限管理");
        map2.put("path","Rights");
        menuArr[1]=map2;
        Map<String,String> mapRole= new HashMap<String,String>();
        mapRole.put("id","114");
        mapRole.put("authName","角色管理");
        mapRole.put("path","Roles");
        menuArr[2]=mapRole;
        Map<String,String> mapAttr= new HashMap<String,String>();
        mapAttr.put("id","115");
        mapAttr.put("authName","参数管理");
        mapAttr.put("path","Attrs");
        menuArr[3]=mapAttr;
        userList.get(0).setChildren(menuArr);


        Map[] menuArr2 = new Map[2];
        Map<String,String> map3= new HashMap<String,String>();
        map3.put("id","112");
        map3.put("authName","当前订单");
        map3.put("path","Order");
        menuArr2[0]=map3;
        Map<String,String> map4= new HashMap<String,String>();
        map4.put("id","113");
        map4.put("authName","超时订单");
        map4.put("path","DelOrder");
        menuArr2[1]=map4;
        userList.get(1).setChildren(menuArr2);
        return userList;
    }

}
