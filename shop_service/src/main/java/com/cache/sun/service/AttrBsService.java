package com.cache.sun.service;

import com.cache.sun.bean.AttrAsBean;
import com.cache.sun.bean.AttrBsBean;
import com.cache.sun.mapper.AttrBsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttrBsService implements AttrBsMapper {

    @Autowired
    private AttrBsMapper attrBsMapper;


    @Override
    public List<AttrBsBean> getAttrBsList() {
        return attrBsMapper.getAttrBsList();
    }

    @Override
    public int upAttrBsById(String id, String attrMemo) {
        return attrBsMapper.upAttrBsById(id,attrMemo);
    }

    @Override
    public int addAttrBs(String id, String attrName) {
        return attrBsMapper.addAttrBs(id,attrName);
    }

    @Override
    public List<AttrBsBean> getAttrBsById(String id) {
        return attrBsMapper.getAttrBsById(id);
    }

    @Override
    public int upAttrBsInfoById(String id, String attrName) {
        return attrBsMapper.upAttrBsInfoById(id,attrName);
    }

    @Override
    public int deleteAttrBsById(String id) {
        return attrBsMapper.deleteAttrBsById(id);
    }
}
