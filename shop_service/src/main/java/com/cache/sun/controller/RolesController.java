package com.cache.sun.controller;

import com.cache.sun.bean.RolesBean;
import com.cache.sun.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@ResponseBody
public class RolesController {

    @Autowired
    private RolesService rolesService;

    /*
     获取角色权限列表
     */
    @GetMapping("/getRoles")
    public List getMenus(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        Map<String,Object> resMap=new HashMap<String,Object>();
        List<RolesBean> rolesList=rolesService.getRolesList(pageNum,pageSize);

        Map[] menuArr = new Map[1];
        Map[] childrenMenuArr = new Map[1];
        Map<String,String> mapRole= new HashMap<String,String>();
        mapRole.put("id","110");
        mapRole.put("authName","商品管理");
        mapRole.put("path","goods");

        menuArr[0]=mapRole;
        rolesList.get(0).setChildren(menuArr);


        return rolesList;
    }

    @GetMapping("/getRolesById")
    public List getRolesById(@RequestParam("id") String id) {
        Map<String, String> resMap = new HashMap<String, String>();
        List<RolesBean> rolesList = rolesService.getRolesById(id);
        return rolesList;
    }

    @PostMapping("/addRoles")
    public int addRoles(@RequestParam("roleName") String roleName,@RequestParam("roleDescript") String roleDescript){
        UUID uuid=UUID.randomUUID();
        String uuidStr=uuid.toString();
        int result=rolesService.addRoles(uuidStr,roleName,roleDescript);
        return 0;
    }


    @PostMapping("/upRolesById")
    public int upRolesById(@RequestParam("id") String id,@RequestParam("roleName") String roleName,@RequestParam("roleDescript") String roleDescript){
        int result=rolesService.upRolesById(id,roleName,roleDescript);
        return 0;
    }

    @GetMapping("/delRolesById")
    public int delRolesById(@RequestParam("id") String id){
        int result=rolesService.delRolesById(id);
        return 0;
    }



}
