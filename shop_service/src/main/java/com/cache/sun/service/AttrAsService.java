package com.cache.sun.service;



import com.cache.sun.bean.AttrAsBean;
import com.cache.sun.mapper.AttrAsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttrAsService implements AttrAsMapper {

    @Autowired
    private AttrAsMapper attrAsMapper;


    @Override
    public List<AttrAsBean> getAttrAsList() {
        return attrAsMapper.getAttrAsList();
    }

    @Override
    public int upAttrAsById(String id, String attrMemo) {
        return attrAsMapper.upAttrAsById(id,attrMemo);
    }

    @Override
    public int addAttrAs(String id, String attrName) {
        return attrAsMapper.addAttrAs(id,attrName);
    }

    @Override
    public List<AttrAsBean> getAttrAsById(String id) {
        return attrAsMapper.getAttrAsById(id);
    }

    @Override
    public int upAttrAsInfoById(String id, String attrName) {
        return attrAsMapper.upAttrAsInfoById(id,attrName);
    }

    @Override
    public int deleteAttrAsById(String id) {
        return attrAsMapper.deleteAttrAsById(id);
    }
}
