package com.cache.sun.mapper;

import com.cache.sun.bean.UserBean;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {

    @Select("SELECT * FROM sy_user WHERE login_name=#{loginName} and user_password=#{userPassword} ")
    List<UserBean> findAll(String loginName,String userPassword);

    @Select("SELECT * FROM (SELECT rownum AS NUM,T.*,(SELECT COUNT(1) FROM sy_user where user_name like '%'|| #{query}||'%' ) AS TOTAL  FROM sy_user T where   t.user_name like '%'|| #{query}||'%') where NUM<=#{pageNum}*#{pagesize} AND  NUM>#{pageNum}*#{pagesize}-#{pagesize}  ")
    List<UserBean> getUserList(String query,int pageNum, int pagesize);


    @Select("SELECT * FROM (SELECT rownum AS NUM,T.*,(SELECT COUNT(1) FROM sy_user) AS TOTAL  FROM sy_user T ) where NUM<=#{pageNum}*#{pagesize} AND  NUM>#{pageNum}*#{pagesize}-#{pagesize} ")
    List<UserBean> getUserList2(int pageNum, int pagesize);


   /* @Insert("insert into sy_user(user_name,user_password,suer_age,login_name)values(#{username},#{userpassword,#{userage},#{loginname})")
    void insertUser(UserBean userBean);
*/

    @Update("update sy_user set status=#{status} where id=#{id}")
    Integer upUserStatus(Boolean status,String id);

    @Insert("insert into sy_user(id,user_name,user_age,login_name,user_password)VALUES(#{uuid},#{userName},#{age},#{loginName},#{password})")
    Integer addUser(String uuid,String userName,int age,String loginName,String password);

    //根据ID获取用户的信息
    @Select("select * from sy_user where id=#{id}")
    List<UserBean> getUser(String id);

    @Update("update sy_user set login_name=#{loginName},user_age=#{age},user_password=#{password}  where id=#{id}")
    Integer upUser(String loginName,int age,String password,String id);

    @Delete("delete from sy_user where id=#{id}")
    Integer delUser(String id);



}
