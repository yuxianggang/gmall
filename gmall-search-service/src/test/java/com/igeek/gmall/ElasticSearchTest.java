package com.igeek.gmall;

import com.alibaba.dubbo.config.annotation.Reference;
import com.igeek.gmall.pojo.PmsSearchSkuInfo;
import com.igeek.gmall.pojo.PmsSkuInfo;
import com.igeek.gmall.service.SkuService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 余祥刚
 * @create 2020-02-06 13:26
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticSearchTest {

    @Autowired
    private JestClient jestClient;

//    @Reference
    private SkuService skuService;

    @Test
    public void testOk(){
        try {
            get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void put() throws InvocationTargetException, IllegalAccessException, IOException {
        List<PmsSkuInfo> skuInfoList = skuService.getSkuAll();

        List<PmsSearchSkuInfo> searchSkuInfoList = new ArrayList<>();

        for (PmsSkuInfo pmsSkuInfo : skuInfoList) {
            PmsSearchSkuInfo searchSkuInfo = new PmsSearchSkuInfo();


            BeanUtils.copyProperties(pmsSkuInfo,searchSkuInfo);

            searchSkuInfo.setId(Long.parseLong(pmsSkuInfo.getId()));

            System.out.println(searchSkuInfo);
            searchSkuInfoList.add(searchSkuInfo);
        }
        for (PmsSearchSkuInfo searchSkuInfo : searchSkuInfoList) {
            Index index = new Index.Builder(searchSkuInfo).index("gmall").type("PmsSkuInfo").id(searchSkuInfo.getId() + "").build();
            jestClient.execute(index);

        }

    }

    public void get() throws IOException {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        QueryBuilder builder = new TermQueryBuilder("skuAttrValueList.valueId","41");
        boolQueryBuilder.filter(builder);

        QueryBuilder mustBuilder = new MatchQueryBuilder("skuName","荣耀");
        boolQueryBuilder.must(mustBuilder);

        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);

        searchSourceBuilder.highlight(null);

        String query = searchSourceBuilder.toString();
        Search search = new Search.Builder(query).addIndex("gmall").addType("PmsSkuInfo").build();

        SearchResult execute = jestClient.execute(search);

        List<SearchResult.Hit<PmsSearchSkuInfo, Void>> hitList = execute.getHits(PmsSearchSkuInfo.class);

        for (SearchResult.Hit<PmsSearchSkuInfo, Void> pmsSearchSkuInfoVoidHit : hitList) {
            PmsSearchSkuInfo pmsSearchSkuInfo = pmsSearchSkuInfoVoidHit.source;
            System.out.println(pmsSearchSkuInfo);
        }
    }
}
