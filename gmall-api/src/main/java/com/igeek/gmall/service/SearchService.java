package com.igeek.gmall.service;

import com.igeek.gmall.pojo.PmsSearchParam;
import com.igeek.gmall.pojo.PmsSearchSkuInfo;

import java.io.IOException;
import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-02-06 16:01
 */
public interface SearchService {
    List<PmsSearchSkuInfo> search(PmsSearchParam pmsSearchParam) throws IOException;
}
