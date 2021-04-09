package com.cache.sun.mapper;

import com.cache.sun.bean.MenuBean;
import com.cache.sun.bean.MenuBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface MenuMapper {

    @Select("SELECT * FROM sy_memu  ")
    List<MenuBean> getMenu();


}
