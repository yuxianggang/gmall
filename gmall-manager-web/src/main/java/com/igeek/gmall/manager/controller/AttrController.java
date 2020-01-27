package com.igeek.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.igeek.gmall.pojo.PmsBaseAttrInfo;
import com.igeek.gmall.pojo.PmsBaseAttrValue;
import com.igeek.gmall.service.AttrService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-27 14:19
 */
@RestController
@CrossOrigin
public class AttrController {

    @Reference
    private AttrService attrService;

    @RequestMapping("attrInfoList")
    public List<PmsBaseAttrInfo> attrInfoList(String catalog3Id){
        List<PmsBaseAttrInfo> list = attrService.attrInfoList(catalog3Id);
        return list;
    }

    @RequestMapping("saveAttrInfo")
    public String saveAttrInfo(@RequestBody PmsBaseAttrInfo pmsBaseAttrInfo){
        attrService.saveAttrInfo(pmsBaseAttrInfo);
        return "success";
    }

    @RequestMapping("getAttrValueList")
    public List<PmsBaseAttrValue> getAttrValueList(String attrId){
        List<PmsBaseAttrValue> list = attrService.getAttrValueList(attrId);
        return list;
    }
}
