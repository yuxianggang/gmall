package com.igeek.gmall.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.igeek.gmall.pojo.*;
import com.igeek.gmall.service.BaseAttrService;
import com.igeek.gmall.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.*;

/**
 * @author 余祥刚
 * @create 2020-02-06 15:59
 */
@Controller
public class SearchController {

    @Reference
    private SearchService searchService;

    @Reference
    private BaseAttrService baseAttrService;

    @RequestMapping("list")
    public String list(PmsSearchParam pmsSearchParam, Model model) throws IOException {
        List<PmsSearchSkuInfo> searchSkuInfoList = searchService.search(pmsSearchParam);
        model.addAttribute("skuLsInfoList",searchSkuInfoList);

        Set<String> attrValueIdSet = new HashSet<>();
        for (PmsSearchSkuInfo searchSkuInfo : searchSkuInfoList) {
            List<PmsSkuAttrValue> skuAttrValueList = searchSkuInfo.getSkuAttrValueList();
            for (PmsSkuAttrValue skuAttrValue : skuAttrValueList) {
                String valueId = skuAttrValue.getValueId();
                attrValueIdSet.add(valueId);
            }
        }
        //品牌属性列表查询
        List<PmsBaseAttrInfo> baseBaseAttrList = baseAttrService.getAttrList(attrValueIdSet);
        //去掉选择了的平台属性
        String[] attrValueIds = pmsSearchParam.getAttrValueIds();
        if(attrValueIds != null && attrValueIds.length>0){
            //面包屑
            List<PmsSearchCrumbs> pmsSearchCrumbsList = new ArrayList<>();
            for (String attrValueId : attrValueIds) {
                PmsSearchCrumbs pmsSearchCrumbs = new PmsSearchCrumbs();
                pmsSearchCrumbs.setValueId(attrValueId);

                pmsSearchCrumbs.setUrlParam(getUrlParam(pmsSearchParam,attrValueId));

                Iterator<PmsBaseAttrInfo> iterator = baseBaseAttrList.iterator();
                while (iterator.hasNext()){
                    PmsBaseAttrInfo pmsBaseAttrInfo = iterator.next();
                    for (PmsBaseAttrValue baseAttrValue : pmsBaseAttrInfo.getAttrValueList()) {
                        if(baseAttrValue.getId().equals(attrValueId)){
                            pmsSearchCrumbs.setValueName(baseAttrValue.getValueName());
                            iterator.remove();
                        }
                    }
                }
                pmsSearchCrumbsList.add(pmsSearchCrumbs);
            }
            model.addAttribute("attrValueSelectedList",pmsSearchCrumbsList);
        }
        model.addAttribute("attrList",baseBaseAttrList);
        //url封装
        String urlParam = getUrlParam(pmsSearchParam,null);
        model.addAttribute("urlParam",urlParam);

        model.addAttribute("keyword",pmsSearchParam.getKeyword());
        return "list";
    }

    private String getUrlParam(PmsSearchParam pmsSearchParam,String delValueId) {
        StringBuffer urlParam = new StringBuffer();
        if (StringUtils.isNotBlank(pmsSearchParam.getKeyword())){
            urlParam.append("&keyword="+pmsSearchParam.getKeyword());
        }
        if(StringUtils.isNotBlank(pmsSearchParam.getCatalog3Id())){
            urlParam.append("&catalog3Id="+pmsSearchParam.getCatalog3Id());
        }
        if (pmsSearchParam.getAttrValueIds() != null && pmsSearchParam.getAttrValueIds().length >0){
            for (String attrValueId : pmsSearchParam.getAttrValueIds()) {
                if(StringUtils.isBlank(delValueId) || (StringUtils.isNotBlank(delValueId)&& !attrValueId.equals(delValueId))){
                    urlParam.append("&attrValueIds="+attrValueId);
                }
            }
        }
        return urlParam.substring(1);
    }

}
