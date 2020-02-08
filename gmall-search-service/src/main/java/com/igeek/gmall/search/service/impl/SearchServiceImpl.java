package com.igeek.gmall.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.igeek.gmall.pojo.PmsSearchParam;
import com.igeek.gmall.pojo.PmsSearchSkuInfo;
import com.igeek.gmall.pojo.PmsSkuAttrValue;
import com.igeek.gmall.service.SearchService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 余祥刚
 * @create 2020-02-06 16:04
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private JestClient jestClient;

    @Override
    public List<PmsSearchSkuInfo> search(PmsSearchParam pmsSearchParam) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //过滤条件
        if(StringUtils.isNotBlank(pmsSearchParam.getCatalog3Id())){
            QueryBuilder queryBuilder = new TermQueryBuilder("catalog3Id",pmsSearchParam.getCatalog3Id());
            boolQueryBuilder.must(queryBuilder);
        }
        String [] pmsSkuAttrValues = pmsSearchParam.getAttrValueIds();
        if(pmsSkuAttrValues != null && pmsSkuAttrValues.length>0){
            for (String skuAttrValue : pmsSkuAttrValues) {
                QueryBuilder filterBuilder = new TermQueryBuilder("skuAttrValueList.valueId",skuAttrValue);
                boolQueryBuilder.filter(filterBuilder);
            }
        }
        //筛选条件
        if(StringUtils.isNotBlank(pmsSearchParam.getKeyword())){
            QueryBuilder mustBuilder = new MatchQueryBuilder("skuName",pmsSearchParam.getKeyword());
            boolQueryBuilder.must(mustBuilder);
        }

        searchSourceBuilder.query(boolQueryBuilder);

        //分页
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);

        //排序
        searchSourceBuilder.sort("id", SortOrder.ASC);

        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("skuName");
        highlightBuilder.preTags("<em style='color:red'>");
        highlightBuilder.postTags("</em>");

        searchSourceBuilder.highlight(highlightBuilder);

        String query = searchSourceBuilder.toString();

        Search search = new Search.Builder(query).addIndex("gmall").addType("PmsSkuInfo").build();

        SearchResult result = jestClient.execute(search);
        List<SearchResult.Hit<PmsSearchSkuInfo, Void>> hitList = result.getHits(PmsSearchSkuInfo.class);

        List<PmsSearchSkuInfo> searchSkuInfoList = new ArrayList<>();

        for (SearchResult.Hit<PmsSearchSkuInfo, Void> pmsSearchSkuInfoVoidHit : hitList) {
            PmsSearchSkuInfo pmsSearchSkuInfo = pmsSearchSkuInfoVoidHit.source;
            Map<String, List<String>> highlight = pmsSearchSkuInfoVoidHit.highlight;
            if(highlight!=null && !highlight.isEmpty()){
                String skuName = highlight.get("skuName").get(0);
                pmsSearchSkuInfo.setSkuName(skuName);
            }
            searchSkuInfoList.add(pmsSearchSkuInfo);
        }

        return searchSkuInfoList;
    }
}
