package com.cache.sun.service;


import com.cache.sun.bean.MenuBean;
import com.cache.sun.bean.RightsBean;
import com.cache.sun.mapper.MenuMapper;
import com.cache.sun.mapper.RightsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RightsService implements RightsMapper {

    @Autowired
    private RightsMapper rightsMapper;


    @Override
    public List<RightsBean> getRightsList() {
        return rightsMapper.getRightsList();
    }
}
