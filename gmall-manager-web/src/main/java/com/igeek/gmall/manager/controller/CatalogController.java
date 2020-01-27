package com.igeek.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.igeek.gmall.pojo.PmsBaseCatalog1;
import com.igeek.gmall.pojo.PmsBaseCatalog2;
import com.igeek.gmall.pojo.PmsBaseCatalog3;
import com.igeek.gmall.service.CatalogService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-27 13:55
 */
@RestController
@CrossOrigin // 跨域请求
public class CatalogController {

    @Reference
    private CatalogService catalogService;

    @RequestMapping("getCatalog1")
    public List<PmsBaseCatalog1> getCatalog1(){
        List<PmsBaseCatalog1> list = catalogService.getCatalog1();
        return list;
    }

    @RequestMapping("getCatalog2")
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id){
        List<PmsBaseCatalog2> list = catalogService.getCatalog2(catalog1Id);
        return list;
    }

    @RequestMapping("getCatalog3")
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id){
        List<PmsBaseCatalog3> list = catalogService.getCatalog3(catalog2Id);
        return list;
    }
}
