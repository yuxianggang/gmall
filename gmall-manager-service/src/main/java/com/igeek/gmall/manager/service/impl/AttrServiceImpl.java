package com.igeek.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.igeek.gmall.manager.mapper.AttrMapper;
import com.igeek.gmall.manager.mapper.AttrValueMapper;
import com.igeek.gmall.pojo.PmsBaseAttrInfo;
import com.igeek.gmall.pojo.PmsBaseAttrValue;
import com.igeek.gmall.service.AttrService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-27 14:22
 */
@Service
public class AttrServiceImpl implements AttrService {

    @Autowired
    private AttrMapper attrMapper;

    @Autowired
    private AttrValueMapper attrValueMapper;

    @Override
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id) {
        PmsBaseAttrInfo attrInfo = new PmsBaseAttrInfo();
        attrInfo.setCatalog3Id(catalog3Id);
        List<PmsBaseAttrInfo> list = attrMapper.select(attrInfo);
        for (PmsBaseAttrInfo baseAttrInfo : list) {
            PmsBaseAttrValue baseAttrValue = new PmsBaseAttrValue();
            baseAttrValue.setAttrId(baseAttrInfo.getId());
            List<PmsBaseAttrValue> attrValueList = attrValueMapper.select(baseAttrValue);
            baseAttrInfo.setAttrValueList(attrValueList);
        }
        return list;
    }

    @Override
    @Transactional
    public int saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo) {
        System.out.println(pmsBaseAttrInfo);
        String id = pmsBaseAttrInfo.getId();
        if(StringUtils.isBlank(id)){//id 为空，表示是添加属性
            int result = attrMapper.insertSelective(pmsBaseAttrInfo);
            if(result ==1){
                for (PmsBaseAttrValue pmsBaseAttrValue : pmsBaseAttrInfo.getAttrValueList()) {
                    pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                    attrValueMapper.insert(pmsBaseAttrValue);
                }
            }
        }else{//修改属性
            attrMapper.updateByPrimaryKey(pmsBaseAttrInfo);
            PmsBaseAttrValue attrValue = new PmsBaseAttrValue();
            attrValue.setAttrId(pmsBaseAttrInfo.getId());
            attrValueMapper.delete(attrValue);
            for (PmsBaseAttrValue pmsBaseAttrValue : pmsBaseAttrInfo.getAttrValueList()) {
                pmsBaseAttrValue.setAttrId(pmsBaseAttrInfo.getId());
                attrValueMapper.insert(pmsBaseAttrValue);
            }
        }
        return 1;
    }

    @Override
    public List<PmsBaseAttrValue> getAttrValueList(String attrId) {
        PmsBaseAttrValue attrValue = new PmsBaseAttrValue();
        attrValue.setAttrId(attrId);
        return attrValueMapper.select(attrValue);
    }
}
