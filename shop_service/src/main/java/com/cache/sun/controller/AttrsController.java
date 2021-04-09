package com.cache.sun.controller;


import com.cache.sun.bean.AttrAsBean;
import com.cache.sun.bean.AttrBsBean;
import com.cache.sun.bean.RightsBean;
import com.cache.sun.service.AttrAsService;
import com.cache.sun.service.AttrBsService;
import com.cache.sun.service.RightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController

public class AttrsController {

    @Autowired
    private AttrAsService attrAsService;

    @Autowired
    private AttrBsService attrBsService;

    /*
     获取参数列表
     */
    @GetMapping("/getAttrs")
    public List getAttrs(@RequestParam("type") String type){
        Map<String,String> resMap=new HashMap<String,String>();
        if(type.equals("manry")){
            List<AttrAsBean> attrAsList=attrAsService.getAttrAsList();
            return attrAsList;
        }else{
            List<AttrBsBean> attrBsList=attrBsService.getAttrBsList();
            return attrBsList;
        }

    }


    @PostMapping("/upAttrsById")
    public int upAttrsById(@RequestParam("id") String id,@RequestParam("attrMemo") String attrMemo,@RequestParam("type") String type){
        if(type.equals("manry")){
            int result=attrAsService.upAttrAsById(id,attrMemo);
        }else{
            int result=attrBsService.upAttrBsById(id,attrMemo);
        }
        return 0;
    }

    @PostMapping("/addAttr")
    public int addAttr(@RequestParam("type") String type,@RequestParam("attrName") String attrName){
        UUID uuid=UUID.randomUUID();
        String uuidStr=uuid.toString();
        if(type.equals("manry")){
            int result=attrAsService.addAttrAs(uuidStr,attrName);
        }else{
            int result=attrBsService.addAttrBs(uuidStr,attrName);
        }
        return 0;
    }

     /*
     获取参数列表
     */
    @GetMapping("/getAttrById")
    public List getAttrById(@RequestParam("type") String type,@RequestParam("id") String id){
        Map<String,String> resMap=new HashMap<String,String>();
        if(type.equals("manry")){
            List<AttrAsBean> attrAsList=attrAsService.getAttrAsById(id);
            return attrAsList;
        }else{
            List<AttrBsBean> attrBsList=attrBsService.getAttrBsById(id);
            return attrBsList;
        }
    }

         /*
          根据ID修改参数
          */
         @PostMapping("/upAttrInfoById")
         public int upAttrInfoById(@RequestParam("id") String id,@RequestParam("attrName") String attrName,@RequestParam("type") String type){
             if(type.equals("manry")){
                 int result=attrAsService.upAttrAsInfoById(id,attrName);
             }else{
                 int result=attrBsService.upAttrBsInfoById(id,attrName);
             }
             return 0;
         }



    @GetMapping("/deleteAttrById")
    public int deleteAttrById(@RequestParam("type") String type,@RequestParam("id") String id){
        Map<String,String> resMap=new HashMap<String,String>();
        if(type.equals("manry")){
            int result=attrAsService.deleteAttrAsById(id);
        }else{
            int result=attrBsService.deleteAttrBsById(id);
        }
        return 0;
    }


}
