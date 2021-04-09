package com.cache.sun.controller;


import com.cache.sun.bean.RightsBean;
import com.cache.sun.service.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class RightsController {

    @Autowired
    private RightsService rightService;

    /*
     获取菜单栏
     */
    @GetMapping("/getRights")
    public List getMenus(){
        Map<String,String> resMap=new HashMap<String,String>();
        List<RightsBean> userList=rightService.getRightsList();
        return userList;
    }

}
