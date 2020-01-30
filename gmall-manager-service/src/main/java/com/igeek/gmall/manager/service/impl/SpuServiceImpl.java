package com.igeek.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.igeek.gmall.manager.mapper.*;
import com.igeek.gmall.pojo.*;
import com.igeek.gmall.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 余祥刚
 * @create 2020-01-28 11:05
 */
@Service
public class SpuServiceImpl implements SpuService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;

    @Autowired
    private ProductImageMapper productImageMapper;

    @Autowired
    private ProductSaleAttrMapper productSaleAttrMapper;

    @Autowired
    private ProductSaleAttrValueMapper productSaleAttrValueMapper;

    @Override
    public List<PmsProductInfo> supList(String catalog3Id) {
        PmsProductInfo productInfo = new PmsProductInfo();
        productInfo.setCatalog3Id(catalog3Id);
        return productInfoMapper.select(productInfo);
    }

    @Override
    public List<PmsBaseSaleAttr> baseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }

    @Override
    public String saveSpuInfo(PmsProductInfo productInfo) {
        productInfoMapper.insertSelective(productInfo);
        for (PmsProductImage pmsProductImage : productInfo.getSpuImageList()) {
            pmsProductImage.setProductId(productInfo.getId());
            productImageMapper.insertSelective(pmsProductImage);
        }
        for (PmsProductSaleAttr pmsProductSaleAttr : productInfo.getSpuSaleAttrList()) {
            pmsProductSaleAttr.setProductId(productInfo.getId());
            productSaleAttrMapper.insertSelective(pmsProductSaleAttr);
            for (PmsProductSaleAttrValue pmsProductSaleAttrValue : pmsProductSaleAttr.getSpuSaleAttrValueList()) {
                pmsProductSaleAttrValue.setProductId(productInfo.getId());
                pmsProductSaleAttrValue.setSaleAttrId(pmsProductSaleAttr.getId());
                productSaleAttrValueMapper.insertSelective(pmsProductSaleAttrValue);
            }
        }
        return "success";
    }

    @Override
    public List<PmsProductImage> supImageList(String spuId) {
        PmsProductImage pmsProductImage = new PmsProductImage();
        pmsProductImage.setProductId(spuId);
        return  productImageMapper.select(pmsProductImage);
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {
        PmsProductSaleAttr pmsProductSaleAttr = new PmsProductSaleAttr();
        pmsProductSaleAttr.setProductId(spuId);
        List<PmsProductSaleAttr> saleAttrList = productSaleAttrMapper.select(pmsProductSaleAttr);
        for (PmsProductSaleAttr productSaleAttr : saleAttrList) {
            PmsProductSaleAttrValue pmsProductSaleAttrValue = new PmsProductSaleAttrValue();
            pmsProductSaleAttrValue.setProductId(spuId);
            pmsProductSaleAttrValue.setSaleAttrId(productSaleAttr.getId());
            List<PmsProductSaleAttrValue> attrValueList = productSaleAttrValueMapper.select(pmsProductSaleAttrValue);
            productSaleAttr.setSpuSaleAttrValueList(attrValueList);
        }
        return saleAttrList;
    }

    @Override
    public List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String skuId, String spuId) {
        Map<String,String> map = new HashMap<>();
        map.put("skuId",skuId);
        map.put("spuId",spuId);
        List<PmsProductSaleAttr> list = productSaleAttrMapper.spuSaleAttrListCheckBySku(map);
        return list;
    }
}
