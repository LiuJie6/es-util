package com.yk.collection.es;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name:es-util
 * File Name:EsUtilTest
 * Package Name:com.yk.collection.es
 * Date:2018/6/1 15:08
 * Author:wangzhili
 * Description:
 * Copyright (c) 2018, 重庆云凯科技有限公司 All Rights Reserved.
 */

public class EsUtilTest {

    private EsUtil esUtil = new EsUtil();

    @Test
    public void getClient() {
        JestClient jestClient = esUtil.getClient("http://10.0.101.24:9200");

    }

    @Test
    public void createIndex() throws Exception {
        JestClient jestClient = esUtil.getClient("http://10.0.101.24:9200");
        JestResult m = esUtil.createIndex(jestClient, "m");
        System.out.println(m.isSucceeded());
    }

    @Test
    public void createDocument() throws Exception {

        JestClient jestClient = esUtil.getClient("http://10.0.101.24:9200");
        long start =System.currentTimeMillis();
        JestResult document = esUtil.createDocument(jestClient, "wang", "test", new Student("张三", "男"));
        long end =System.currentTimeMillis();
        System.out.println("耗时：" +(end-start));
        System.out.println(document.isSucceeded());

    }

    @Test
    public void createDocuments() throws Exception {
        List list = new ArrayList<>();
        list.add(new Student("三", "三"));

        JestClient jestClient = esUtil.getClient("http://10.0.101.24:9200");
        JestResult documents = esUtil.createDocuments(jestClient, "wang", "test", list);
        System.out.println(documents.getJsonObject());
    }


    @Test
    public void getDocumentById() throws Exception {
        JestClient jestClient = esUtil.getClient("http://10.0.101.24:9200");
        Student documentById = esUtil.getDocumentById(jestClient, "wang", "test", "r-qRumMBOb1f1L2yhEY4", Student.class);
        System.out.println(documentById);
    }

    @Test
    public void deleteDocument() throws Exception {
        JestClient jestClient = esUtil.getClient("http://10.0.101.24:9200");
        JestResult jestResult = esUtil.deleteDocument(jestClient, "wang", "test", "r-qRumMBOb1f1L2yhEY4");
        System.out.println(jestResult.isSucceeded());
    }


    @Test
    public void deleteIndex() throws Exception {
        JestClient jestClient = esUtil.getClient("http://10.0.101.24:9200");
        JestResult jestResult = esUtil.deleteIndex(jestClient, "wang");
        System.out.println(jestResult.isSucceeded());
    }

    @Test
    public void indicesExist() throws Exception {
        JestClient jestClient = esUtil.getClient("http://10.0.101.24:9200");
        JestResult jestResult = esUtil.indicesExist(jestClient, "wang");
        System.out.println(jestResult.isSucceeded());
    }

    @Test
    public void nodesInfo() throws Exception {
        JestClient jestClient = esUtil.getClient("http://10.0.101.24:9200");
        JestResult jestResult = esUtil.nodesInfo(jestClient);
        System.out.println(jestResult.getJsonObject());
    }

    @Test
    public void health() throws Exception {
        JestClient jestClient = esUtil.getClient("http://10.0.101.24:9200");
        JestResult jestResult = esUtil.health(jestClient);
        System.out.println(jestResult.getJsonObject());
    }

    @Test
    public void nodesStates() throws Exception {
        JestClient jestClient = esUtil.getClient("http://10.0.101.24:9200");
        JestResult jestResult = esUtil.nodesStates(jestClient);
        System.out.println(jestResult.getJsonObject());
        esUtil.closeJestClient(jestClient);
    }

    @Test
    public void andConditionQuery() throws Exception {
        JestClient jestClient = esUtil.getClient("http://10.0.101.24:9200");
        QueryBuilder queryBuilder1 = esUtil.matchPhraseQuery("name", "张三");
        QueryBuilder queryBuilder2 = esUtil.matchQuery("sex","三");
        List<QueryBuilder> list = new ArrayList<>();
        list.add(queryBuilder1);
        list.add(queryBuilder2);
        QueryBuilder queryBuilder = esUtil.orConditionQuery(list);
        List<SearchResult.Hit<Student, Void>> searchs = esUtil.search(jestClient, "wang", "test", Student.class, queryBuilder);
        searchs.forEach(x -> System.out.println(x.source));
        esUtil.closeJestClient(jestClient);
    }


}


