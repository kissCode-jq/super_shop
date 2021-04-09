package com.cache.sun.mapper;

import com.cache.sun.bean.RolesBean;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface RolesMapper {

    @Select("SELECT * FROM (SELECT ROWNUM AS NUM,T.*,(SELECT COUNT(1) FROM ROLES) AS TOTAL  FROM ROLES T ) WHERE NUM<=#{pageNum}*#{pageSize} AND  NUM>#{pageNum}*#{pageSize}-#{pageSize} ")
    List<RolesBean> getRolesList(int pageNum, int pageSize );

    @Select("SELECT * FROM ROLES WHERE ID=#{id} ")
    List<RolesBean> getRolesById(String id);

    @Update("UPDATE ROLES SET ROLE_NAME=#{roleName},ROLE_DESCRIT=#{roleDescript}  WHERE ID=#{id} ")
    int upRolesById(String id,String roleName,String roleDescript);

    @Insert("INSERT INTO ROLES(ID,ROLE_NAME,ROLE_DESCRIT)VALUES(#{uuid},#{roleName},#{roleDescript})")
    Integer addRoles(String uuid,String roleName,String roleDescript);

    @Delete("DELETE FROM ROLES WHERE ID=#{id}")
    Integer delRolesById(String id);
}
