package com.yk.collection.es;

import io.searchbox.client.JestClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.util.List;

/**
 * Project Name:es-util
 * File Name:Main
 * Package Name:com.yk.collection.es
 * Date:2018/6/1 16:41
 * Author:wangzhili
 * Description:
 * Copyright (c) 2018, 重庆云凯科技有限公司 All Rights Reserved.
 */

public class Main {
    public static void main(String[] args) throws Exception{
        EsUtil esUtil = new EsUtil();
        JestClient jestClient = esUtil.getClient("http://10.0.101.24:9200");

        //单值匹配
        String query = new SearchSourceBuilder().query(QueryBuilders.matchQuery("name","三")).toString();
        //多字段混合匹配，注意 满足一个字段就算满足查询条件
        String query01 = new SearchSourceBuilder().query(QueryBuilders.multiMatchQuery("张", "name", "sex")).toString();
        String query02 = new SearchSourceBuilder().query(QueryBuilders.matchPhraseQuery("sex","nan")).toString();

    }
}
