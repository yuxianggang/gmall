package com.igeek.gmall.manager.mapper;

import com.igeek.gmall.pojo.PmsProductSaleAttr;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author 余祥刚
 * @create 2020-01-28 15:53
 */
public interface ProductSaleAttrMapper extends Mapper<PmsProductSaleAttr> {

    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(Map<String, String> map);
}
