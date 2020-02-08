package com.igeek.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.igeek.gmall.manager.mapper.BaseAttrMapper;
import com.igeek.gmall.manager.mapper.BaseAttrValueMapper;
import com.igeek.gmall.pojo.PmsBaseAttrInfo;
import com.igeek.gmall.pojo.PmsBaseAttrValue;
import com.igeek.gmall.pojo.PmsBaseSaleAttr;
import com.igeek.gmall.service.BaseAttrService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 余祥刚
 * @create 2020-02-07 14:10
 */
@Service
public class BaseAttrServiceImpl implements BaseAttrService {
    
    @Autowired
    private BaseAttrMapper baseAttrMapper;
    

    @Override
    public List<PmsBaseAttrInfo> getAttrList(Set<String> attrValueIdSet) {
        List<PmsBaseAttrInfo> list = baseAttrMapper.selectListByAttrValueIds(attrValueIdSet);

        return list;
    }
}
