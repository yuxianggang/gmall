package com.igeek.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.igeek.gmall.jedis.JedisUtil;
import com.igeek.gmall.manager.mapper.SkuAttrValueMapper;
import com.igeek.gmall.manager.mapper.SkuImageMapper;
import com.igeek.gmall.manager.mapper.SkuInfoMapper;
import com.igeek.gmall.manager.mapper.SkuSaleAttrValueMapper;
import com.igeek.gmall.pojo.PmsSkuAttrValue;
import com.igeek.gmall.pojo.PmsSkuImage;
import com.igeek.gmall.pojo.PmsSkuInfo;
import com.igeek.gmall.pojo.PmsSkuSaleAttrValue;
import com.igeek.gmall.service.SkuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-01-28 19:07
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuInfoMapper skuInfoMapper;

    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;

    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Autowired
    private SkuImageMapper skuImageMapper;

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    @Transactional
    public String saveSkuInfo(PmsSkuInfo pmsSkuInfo) {
        if (StringUtils.isBlank(pmsSkuInfo.getSkuDefaultImg())){
            PmsSkuImage pmsSkuImage = pmsSkuInfo.getSkuImageList().get(0);
            pmsSkuInfo.setSkuDefaultImg(pmsSkuImage.getImgUrl());
            pmsSkuImage.setIsDefault("1");
        }
        skuInfoMapper.insert(pmsSkuInfo);

        for (PmsSkuAttrValue pmsSkuAttrValue : pmsSkuInfo.getSkuAttrValueList()) {
            pmsSkuAttrValue.setSkuId(pmsSkuInfo.getId());
            skuAttrValueMapper.insertSelective(pmsSkuAttrValue);
        }

        for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : pmsSkuInfo.getSkuSaleAttrValueList()) {
            pmsSkuSaleAttrValue.setSkuId(pmsSkuInfo.getId());
            skuSaleAttrValueMapper.insertSelective(pmsSkuSaleAttrValue);
        }

        for (PmsSkuImage pmsSkuImage : pmsSkuInfo.getSkuImageList()) {
            pmsSkuImage.setSkuId(pmsSkuInfo.getId());
            skuImageMapper.insertSelective(pmsSkuImage);
        }
        return "success";
    }

    @Override
    public PmsSkuInfo getSkuInfo(String skuId) {
        Jedis jedis = jedisUtil.getJedis();
        String value = jedis.get("item_" + skuId);
        if(StringUtils.isNotBlank(value)){//redis存在
            PmsSkuInfo skuInfo = null;
            try {
                skuInfo = JSON.parseObject(value, PmsSkuInfo.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return skuInfo;
        }else{//不存在
            PmsSkuInfo skuInfo = skuInfoMapper.selectByPrimaryKey(skuId);
            if(skuInfo == null){
                jedis.setex("item_" + skuId,60*3,"1");
                return null;
            }else{
                PmsSkuImage skuImage = new PmsSkuImage();
                skuImage.setSkuId(skuId);
                List<PmsSkuImage> imageList = skuImageMapper.select(skuImage);
                skuInfo.setSkuImageList(imageList);
                String jsonString = JSON.toJSONString(skuInfo);
                jedis.set("item_" + skuId,jsonString);
                return skuInfo;
            }
        }
    }

    @Override
    public List<PmsSkuInfo> skuSaleAttrHsah(String productId) {
        PmsSkuInfo info = new PmsSkuInfo();
        info.setProductId(productId);
        List<PmsSkuInfo> skuInfoList = skuInfoMapper.select(info);
        for (PmsSkuInfo skuInfo : skuInfoList) {
            PmsSkuSaleAttrValue skuSaleAttrValue = new PmsSkuSaleAttrValue();
            skuSaleAttrValue.setSkuId(skuInfo.getId());
            List<PmsSkuSaleAttrValue> saleAttrValueList = skuSaleAttrValueMapper.select(skuSaleAttrValue);
            skuInfo.setSkuSaleAttrValueList(saleAttrValueList);
        }
        return skuInfoList;
    }

    @Override
    public List<PmsSkuInfo> getSkuAll() {
        List<PmsSkuInfo> skuInfoList = skuInfoMapper.selectAll();
        for (PmsSkuInfo pmsSkuInfo : skuInfoList) {
            PmsSkuAttrValue skuAttrValue = new PmsSkuAttrValue();
            skuAttrValue.setSkuId(pmsSkuInfo.getId());
            List<PmsSkuAttrValue> attrValueList = skuAttrValueMapper.select(skuAttrValue);
            pmsSkuInfo.setSkuAttrValueList(attrValueList);
        }
        return skuInfoList;
    }
}
