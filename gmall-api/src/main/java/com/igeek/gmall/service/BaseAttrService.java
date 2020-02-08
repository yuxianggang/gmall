package com.igeek.gmall.service;

import com.igeek.gmall.pojo.PmsBaseAttrInfo;

import java.util.List;
import java.util.Set;

/**
 * @author 余祥刚
 * @create 2020-02-07 14:09
 */
public interface BaseAttrService {
    List<PmsBaseAttrInfo> getAttrList(Set<String> attrValueIdSet);
}
