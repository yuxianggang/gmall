package com.igeek.gmall.service;

import com.igeek.gmall.pojo.PmsSkuInfo;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-28 19:07
 */
public interface SkuService {
    String saveSkuInfo(PmsSkuInfo pmsSkuInfo);

    PmsSkuInfo getSkuInfo(String skuId);

    List<PmsSkuInfo> skuSaleAttrHsah(String productId);

    List<PmsSkuInfo> getSkuAll();
}
