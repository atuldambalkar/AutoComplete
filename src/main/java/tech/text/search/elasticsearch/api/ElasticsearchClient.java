package tech.text.search.elasticsearch.api;


import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * Created by datum on 24/1/18.
 */
public class ElasticsearchClient {

    private static RestClient restClient =
            RestClient.builder(new HttpHost("localhost", 9200, "http")).build();

    private static RestHighLevelClient client =
            new RestHighLevelClient(restClient);


    public static RestHighLevelClient getClient() {
        return client;
    }

    public static void close() throws Exception {
        restClient.close();
    }
}
