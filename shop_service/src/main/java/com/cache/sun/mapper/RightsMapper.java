package com.cache.sun.mapper;

import com.cache.sun.bean.RightsBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface RightsMapper {

    @Select("SELECT * FROM RIGHTS  ")
    List<RightsBean> getRightsList();


}
