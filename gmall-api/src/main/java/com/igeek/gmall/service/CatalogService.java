package com.igeek.gmall.service;

import com.igeek.gmall.pojo.PmsBaseCatalog1;
import com.igeek.gmall.pojo.PmsBaseCatalog2;
import com.igeek.gmall.pojo.PmsBaseCatalog3;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-27 14:03
 */
public interface CatalogService {
    List<PmsBaseCatalog1> getCatalog1();

    List<PmsBaseCatalog2> getCatalog2(String catalog1Id);

    List<PmsBaseCatalog3> getCatalog3(String catalog2Id);
}
