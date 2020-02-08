package com.igeek.gmall.manager.mapper;

import com.igeek.gmall.pojo.PmsBaseAttrInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author 余祥刚
 * @create 2020-02-07 14:14
 */
public interface BaseAttrMapper extends Mapper<PmsBaseAttrInfo> {
    List<PmsBaseAttrInfo> selectListByAttrValueIds(Set<String> attrValueIdSet);
}
