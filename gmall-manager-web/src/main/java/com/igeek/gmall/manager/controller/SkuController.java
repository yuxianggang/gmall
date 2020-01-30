package com.igeek.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.igeek.gmall.pojo.PmsSkuInfo;
import com.igeek.gmall.service.SkuService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 余祥刚
 * @create 2020-01-28 16:27
 */
@RestController
@CrossOrigin
public class SkuController {

    @Reference
    private SkuService skuService;

    @RequestMapping("saveSkuInfo")
    public String saveSkuInfo(@RequestBody PmsSkuInfo pmsSkuInfo){
        pmsSkuInfo.setProductId(pmsSkuInfo.getSpuId());
        System.out.println(pmsSkuInfo);
        String result = skuService.saveSkuInfo(pmsSkuInfo);
        return result;
    }
}
