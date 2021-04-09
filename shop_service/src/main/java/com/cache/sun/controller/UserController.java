package com.cache.sun.controller;


import com.cache.sun.bean.UserBean;
import com.cache.sun.bs.Rep;
import com.cache.sun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("server/login")
    public Rep getUser(@RequestBody Map<String, Object> params){
        String username=params.get("username").toString();
        String password=params.get("password").toString();
        List<UserBean> userList=userService.findAll(username,password);
        //生成唯一值
        UUID uuid=UUID.randomUUID();
        String uuidStr=uuid.toString();
        //判断账号信息是否正确
        if(userList.isEmpty()||userList.size()==0){
            return new Rep<>().error("null");
        }else{
            return new Rep<>().ok(uuidStr);
        }
    }

    @GetMapping("/getUser")
    public List getUser(@RequestParam("query") String query,@RequestParam("pageNum") int pageNum,@RequestParam("pagesize") int pagesize){
        Map<String,String> resMap=new HashMap<String,String>();
        List<UserBean> userList=null;
        if(!query.isEmpty()){
             userList=userService.getUserList(query,pageNum,pagesize);
        }else{
             userList=userService.getUserList2(pageNum,pagesize);
        }


        return userList;
    }

    @PutMapping("/upUserStatus")
    public int upStatus(@RequestParam("status") boolean status,@RequestParam("id") String id){
        int result=userService.upUserStatus(status,id);
        return 0;
    }


    @PostMapping("/addUser")
    public int addUser(@RequestParam("username") String username,@RequestParam("age") int age,@RequestParam("loginname") String loginname,@RequestParam("password") String password){
        UUID uuid=UUID.randomUUID();
        String uuidStr=uuid.toString();
        int result=userService.addUser(uuidStr,username,age,loginname,password);
        return 0;
    }

    @GetMapping("/getUserBean")
    public List getUserBean(@RequestParam("id") String id) {
        Map<String, String> resMap = new HashMap<String, String>();
        List<UserBean> userList = userList = userService.getUser(id);
        return userList;
    }


    @PostMapping("/upUser")
    public int upUser(@RequestParam("loginName") String loginName,@RequestParam("age") int age,@RequestParam("password") String password,@RequestParam("id") String id){
        UUID uuid=UUID.randomUUID();
        String uuidStr=uuid.toString();
        int result=userService.upUser(loginName,age,password,id);
        return 0;
    }

    @PostMapping("/delUser")
    public int delUser(@RequestParam("id") String id){
        int result=userService.delUser(id);
        return 0;
    }


}
