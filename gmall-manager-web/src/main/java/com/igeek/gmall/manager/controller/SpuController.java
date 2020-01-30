package com.igeek.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.igeek.gmall.pojo.PmsBaseSaleAttr;
import com.igeek.gmall.pojo.PmsProductImage;
import com.igeek.gmall.pojo.PmsProductInfo;
import com.igeek.gmall.pojo.PmsProductSaleAttr;
import com.igeek.gmall.service.SpuService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-28 10:59
 */
@RestController
@CrossOrigin
public class SpuController {

    @Reference
    private SpuService spuService;

    @RequestMapping("spuList")
    public List<PmsProductInfo> supList(String catalog3Id){
        List<PmsProductInfo> list = spuService.supList(catalog3Id);
        return list;
    }

    @RequestMapping("baseSaleAttrList")
    public List<PmsBaseSaleAttr> baseSaleAttrList(){
        List<PmsBaseSaleAttr> list = spuService.baseSaleAttrList();
        return list;
    }

    @RequestMapping("saveSpuInfo")
    public String saveSpuInfo(@RequestBody PmsProductInfo productInfo){
        String result = spuService.saveSpuInfo(productInfo);
        System.out.println(productInfo);
        return result;
    }

    @RequestMapping("spuImageList")
    public List<PmsProductImage> spuImageList(String spuId){
        List<PmsProductImage> list = spuService.supImageList(spuId);
        return list;
    }

    @RequestMapping("spuSaleAttrList")
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId){
        List<PmsProductSaleAttr> list = spuService.spuSaleAttrList(spuId);
        return list;
    }
}
