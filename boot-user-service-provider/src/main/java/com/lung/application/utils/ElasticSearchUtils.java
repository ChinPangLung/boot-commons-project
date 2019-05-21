package com.lung.application.utils;

import org.elasticsearch.index.query.*;

import java.io.IOException;

public class ElasticSearchUtils {


    public void TestES() {

        MatchAllQueryBuilder matchAllQueryBuilder1 = QueryBuilders.matchAllQuery();
        TermQueryBuilder termQueryBuilder1 = QueryBuilders.termQuery("field", "value");
        MatchQueryBuilder matchQueryBuilder1 = QueryBuilders.matchQuery("name", "value");
        MultiMatchQueryBuilder multiMatchQueryBuilder1 = QueryBuilders.multiMatchQuery("searchValue", "id", "name", "mobile");
        WildcardQueryBuilder wildcardQueryBuilder1 = QueryBuilders.wildcardQuery("id", "value");

        //match all query
        MatchAllQueryBuilder matchAllQueryBuilder = new MatchAllQueryBuilder();

        //term query
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("id", "111");

        //match query
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name", "long");

        //multiMatch query
        MultiMatchQueryBuilder multiMatchQueryBuilder = new MultiMatchQueryBuilder("searchValue", "id", "name", "mobile");

        //wildcard query
        WildcardQueryBuilder wildcardQueryBuilder = new WildcardQueryBuilder("id", "value");
    }

}
