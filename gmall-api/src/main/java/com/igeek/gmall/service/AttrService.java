package com.igeek.gmall.service;

import com.igeek.gmall.pojo.PmsBaseAttrInfo;
import com.igeek.gmall.pojo.PmsBaseAttrValue;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-27 14:21
 */
public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    int saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);
}
