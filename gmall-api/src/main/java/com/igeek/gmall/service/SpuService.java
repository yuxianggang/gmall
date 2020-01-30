package com.igeek.gmall.service;

import com.igeek.gmall.pojo.PmsBaseSaleAttr;
import com.igeek.gmall.pojo.PmsProductImage;
import com.igeek.gmall.pojo.PmsProductInfo;
import com.igeek.gmall.pojo.PmsProductSaleAttr;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-28 11:04
 */
public interface SpuService {
    List<PmsProductInfo> supList(String catalog3Id);

    List<PmsBaseSaleAttr> baseSaleAttrList();

    String saveSpuInfo(PmsProductInfo productInfo);

    List<PmsProductImage> supImageList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String skuId, String spuId);
}
