package com.gia.elasticsearch;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.joda.time.DateTime;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fenglin on 2017/5/26.
 */
public class SofaClientTest {

    private static final String index = "dtlog-3652-*-*-2017.06.01";

    private static final String type = "logs";

    private static String host = "172.16.10.86";

    private static int port = 9300;

    public static void main(String[] args) throws Exception {
        Settings settings = Settings.builder().put("client.transport.ignore_cluster_name", true).build();
        TransportClient client = new PreBuiltTransportClient(settings)//(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), 9300));

        DateTime dateTime = new DateTime(2017, 6, 1, 00, 00, 00);
        Map<String, String> cond = new HashMap<String, String>();
        //cond.put("method", "findDictByItem");
        cond.put("result_code", "01");

//        searchAppname(client, dateTime);
//        System.out.println();
//        searchService(client, dateTime);
//        System.out.println();
//        searchMethod(client, dateTime);
//        System.out.println();
        searchTrace(client, dateTime, 1, 20, cond);
//        System.out.println();
//        searchTraceTree(client, "0a02105714931665516461012", dateTime);

        client.close();
    }


    /**
     * 查询某个标签的所有值
     */


    /**
     * 查询所有
     */
    public static void searchAll(Client client) {
        SearchResponse response = client.prepareSearch(index).setTypes(type)
                .setQuery(QueryBuilders.matchAllQuery()).setSize(1000)
                .setExplain(true).execute().actionGet();
        System.out.println("searchAll : ");
        System.out.println(JSON.toJSONString(response));
        System.out.println("hits size: " + response.getHits().totalHits);
        for (SearchHit searchHit : response.getHits()) {
            System.out.println(searchHit.getSource());
        }
    }


    /**
     * 查询某天所有的app name
     *
     * @param client
     * @param date
     */
    public static void searchAppname(Client client, DateTime date) {

        SearchRequestBuilder responsebuilder = client.prepareSearch(index)
                .setTypes(type).setFrom(0).setSize(0);

        AggregationBuilder aggregation = AggregationBuilders
                .terms("appnames").field("appname");
        SearchResponse response = responsebuilder.
                setQuery(QueryBuilders.rangeQuery("@timestamp").gte(date)).
                addAggregation(aggregation).
                execute().actionGet();

        System.out.println("search app name");
        System.out.println("hits size: " + response.getHits().totalHits);
        Terms term = response.getAggregations().get("appnames");
        for (Terms.Bucket entry : term.getBuckets()) {
            String key = (String) entry.getKey();
            long docCount = entry.getDocCount();
            System.out.println(key + ": " + docCount);
        }
    }

    /**
     * 查询某天所有的service name
     *
     * @param client
     * @param date
     */
    public static void searchService(Client client, DateTime date) {
        String termsName = "services";
        String field = "service";

        SearchRequestBuilder responsebuilder = client.prepareSearch(index)
                .setTypes(type).setFrom(0).setSize(50);

        AggregationBuilder aggregation = AggregationBuilders
                .terms(termsName).field(field);
        SearchResponse response = responsebuilder.
                addAggregation(aggregation).
                execute().actionGet();

        System.out.println("search " + field + " name");
        System.out.println("hits size: " + response.getHits().totalHits);
        Terms term = response.getAggregations().get(termsName);
        for (Terms.Bucket entry : term.getBuckets()) {
            String key = (String) entry.getKey();
            long docCount = entry.getDocCount();
            System.out.println(key + ": " + docCount);
        }
    }

    /**
     * 查询某天所有的method name
     *
     * @param client
     * @param date
     */
    public static void searchMethod(Client client, DateTime date) {
        String termsName = "methods";
        String field = "method";

        SearchRequestBuilder responsebuilder = client.prepareSearch(index)
                .setTypes(type).setFrom(0).setSize(50);

        AggregationBuilder aggregation = AggregationBuilders
                .terms(termsName).field(field);
        SearchResponse response = responsebuilder.
                addAggregation(aggregation).
                execute().actionGet();

        System.out.println("search " + field + " name");
        System.out.println("hits size: " + response.getHits().totalHits);
        Terms term = response.getAggregations().get(termsName);
        for (Terms.Bucket entry : term.getBuckets()) {
            String key = (String) entry.getKey();
            long docCount = entry.getDocCount();
            System.out.println(key + ": " + docCount);
        }
    }

    /**
     * 查询某天所有的(条件)trace记录，分页
     *
     * @param client
     * @param dateTime
     * @param page
     * @param size
     * @param cond     method, service, appname, time, result
     */
    public static void searchTrace(Client client, DateTime dateTime, int page, int size, Map<String, String> cond) {

        SearchRequestBuilder responsebuilder = client.prepareSearch(index)
                .setTypes(type).setFrom(page).setSize(size)
                .setPostFilter(QueryBuilders.rangeQuery("@timestamp").gte(dateTime))
                .setPostFilter(QueryBuilders.matchQuery("logtype.raw", "sofa-client-stats"));;

        for (Map.Entry<String, String> entry : cond.entrySet()) {
            System.out.println("add query " + entry.getKey() + ":" + entry.getValue());
            responsebuilder = responsebuilder.setQuery(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
            responsebuilder.setQuery(QueryBuilders.rangeQuery("").gt("200ms"));
        }
        SearchResponse response = responsebuilder
                .setFetchSource(new String[]{"TraceId", "appname", "method", "service", "time", "result_code", "invoke_cost"}, null)
                .execute().actionGet();
        System.out.println("total: " + response.getHits().getTotalHits());
        for (SearchHit searchHit : response.getHits()) {
            System.out.println(searchHit.getSource());
        }


    }

    public static void searchTraceTree(Client client, String TraceId, DateTime dateTime) {

        SearchRequestBuilder responsebuilder = client.prepareSearch(index)
                .setTypes(type).setFrom(0).setSize(500);
        SearchResponse response = responsebuilder
                .setPostFilter(QueryBuilders.rangeQuery("@timestamp").gte(dateTime))
                .setQuery(QueryBuilders.matchQuery("TraceId", TraceId))
                .setFetchSource(new String[]{"TraceId", "appname", "method", "service", "time", "result_code", "invoke_cost"}, null)
                .execute().actionGet();

        for (SearchHit searchHit : response.getHits()) {
            System.out.println(searchHit.getSource());
        }
    }

}
