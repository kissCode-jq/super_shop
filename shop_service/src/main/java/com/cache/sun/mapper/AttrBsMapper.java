package com.cache.sun.mapper;
import com.cache.sun.bean.AttrBsBean;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface AttrBsMapper {

    @Select("SELECT * FROM ATTRBS  ")
    List<AttrBsBean> getAttrBsList();

    @Update("UPDATE ATTRBS SET ATTRMEMO=#{attrMemo}  WHERE ID=#{id}")
    int upAttrBsById(String id,String attrMemo);

    @Insert("INSERT INTO ATTRBS(ID,ATTRNAME)VALUES(#{id},#{attrName})")
    int addAttrBs(String id,String attrName);

    @Select("SELECT * FROM ATTRBS WHERE ID=#{id} ")
    List<AttrBsBean> getAttrBsById(String id);

    @Update("UPDATE ATTRBS SET ATTRNAME=#{attrName}  WHERE ID=#{id}")
    int upAttrBsInfoById(String id,String attrName);

    @Delete("DELETE FROM ATTRBS WHERE ID=#{id}")
    int deleteAttrBsById(String id);
}
