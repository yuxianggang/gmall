package com.igeek.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.igeek.gmall.pojo.PmsProductSaleAttr;
import com.igeek.gmall.pojo.PmsSkuInfo;
import com.igeek.gmall.pojo.PmsSkuSaleAttrValue;
import com.igeek.gmall.service.SkuService;
import com.igeek.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 余祥刚
 * @create 2020-01-29 11:58
 */
@Controller
public class IndexController {

    @Reference
    private SkuService skuService;

    @Reference
    private SpuService spuService;

    @RequestMapping("{skuId}.html")
    public String index(@PathVariable String skuId, Model model){
        PmsSkuInfo skuInfo = skuService.getSkuInfo(skuId);
        model.addAttribute("skuInfo",skuInfo);

        List<PmsProductSaleAttr> list = spuService.spuSaleAttrListCheckBySku(skuId,skuInfo.getProductId());

        model.addAttribute("spuSaleAttrListCheckBySku",list);

        List<PmsSkuInfo> skuInfoList = skuService.skuSaleAttrHsah(skuInfo.getProductId());

        Map map = new HashMap<>();
        for (PmsSkuInfo pmsSkuInfo : skuInfoList) {
            String key = "";
            String value = pmsSkuInfo.getId();
            for (PmsSkuSaleAttrValue skuSaleAttrValue : pmsSkuInfo.getSkuSaleAttrValueList()) {
                key += skuSaleAttrValue.getSaleAttrValueId() + "|";
            }
            map.put(key,value);
        }
        String jsonString = JSON.toJSONString(map);
        model.addAttribute("skuSaleAttrHashJsonStr",jsonString);
        return "item";
    }
}
