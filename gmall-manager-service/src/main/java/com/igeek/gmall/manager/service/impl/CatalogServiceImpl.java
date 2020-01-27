package com.igeek.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.igeek.gmall.manager.mapper.Catalog1Mapper;
import com.igeek.gmall.manager.mapper.Catalog2Mapper;
import com.igeek.gmall.manager.mapper.Catalog3Mapper;
import com.igeek.gmall.pojo.PmsBaseCatalog1;
import com.igeek.gmall.pojo.PmsBaseCatalog2;
import com.igeek.gmall.pojo.PmsBaseCatalog3;
import com.igeek.gmall.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-27 14:03
 */
@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private Catalog1Mapper catalogMapper;

    @Autowired
    private Catalog2Mapper catalog2Mapper;

    @Autowired
    private Catalog3Mapper catalog3Mapper;

    @Override
    public List<PmsBaseCatalog1> getCatalog1() {
        return catalogMapper.selectAll();
    }

    @Override
    public List<PmsBaseCatalog2> getCatalog2(String catalog1Id) {
        PmsBaseCatalog2 catalog2 = new PmsBaseCatalog2();
        catalog2.setCatalog1Id(catalog1Id);
        return catalog2Mapper.select(catalog2);
    }

    @Override
    public List<PmsBaseCatalog3> getCatalog3(String catalog2Id) {
        PmsBaseCatalog3 catalog3 = new PmsBaseCatalog3();
        catalog3.setCatalog2Id(catalog2Id);
        return catalog3Mapper.select(catalog3);
    }
}
