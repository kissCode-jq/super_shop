package com.cache.sun.service;



import com.cache.sun.bean.MenuBean;
import com.cache.sun.bean.RolesBean;
import com.cache.sun.mapper.RolesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService implements RolesMapper {

    @Autowired
    private RolesMapper rolesMapper;


    @Override
    public List<RolesBean> getRolesList(int pageNum,int pageSize) {
        return rolesMapper.getRolesList(pageNum,pageSize);
    }

    @Override
    public List<RolesBean> getRolesById(String id) {
        return rolesMapper.getRolesById(id);
    }

    @Override
    public int upRolesById(String id, String roleName, String roleDescript) {
        return rolesMapper.upRolesById(id,roleName,roleDescript);
    }

    @Override
    public Integer addRoles(String uuid, String roleName, String roleDescript) {
        return rolesMapper.addRoles(uuid,roleName,roleDescript);
    }

    @Override
    public Integer delRolesById(String id) {
        return rolesMapper.delRolesById(id);
    }

}
