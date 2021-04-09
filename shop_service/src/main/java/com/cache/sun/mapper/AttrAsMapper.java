package com.cache.sun.mapper;

import com.cache.sun.bean.AttrAsBean;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface AttrAsMapper {

    @Select("SELECT * FROM ATTRAS  ")
    List<AttrAsBean> getAttrAsList();

    @Update("UPDATE ATTRAS SET ATTRMEMO=#{attrMemo}  WHERE ID=#{id}")
    int upAttrAsById(String id,String attrMemo);

    @Insert("INSERT INTO ATTRAS(ID,ATTRNAME)VALUES(#{id},#{attrName})")
    int addAttrAs(String id,String attrName);

    @Select("SELECT * FROM ATTRAS WHERE ID=#{id} ")
    List<AttrAsBean> getAttrAsById(String id);

    @Update("UPDATE ATTRAS SET ATTRNAME=#{attrName}  WHERE ID=#{id}")
    int upAttrAsInfoById(String id,String attrName);

    @Delete("DELETE FROM ATTRAS WHERE ID=#{id}")
    int deleteAttrAsById(String id);

}
