package tech.text.search.elasticsearch.api;

import tech.text.search.elasticsearch.model.AutoCompleteResponse;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by datum on 24/1/18.
 */
@RestController
public class AutocompleteController {

    @RequestMapping("/autocomplete")
    public AutoCompleteResponse autoComplete(@RequestParam(value="search-entity") String name,
                                         @RequestParam(value="search-query") String query) throws Exception {

        SearchRequest searchRequest = new SearchRequest("bestbuy");  // name of the index to search
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        String[] includeFields = new String[] {"name"};
        String[] excludeFields = new String[] {};
        searchSourceBuilder.query(QueryBuilders.matchQuery(name, query)).fetchSource(includeFields, excludeFields);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse =
                ElasticsearchClient.getClient().search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        AutoCompleteResponse<String> autoCompleteResponse = new AutoCompleteResponse<String>();
        autoCompleteResponse.setTotalHits(hits.getTotalHits());
        Iterator<SearchHit> iterator = hits.iterator();
        List<String> values = new ArrayList<String>();
        autoCompleteResponse.setValues(values);
        while (iterator.hasNext()) {
            SearchHit hit = iterator.next();
            values.add((String)hit.getSourceAsMap().get("name"));
        }
        return autoCompleteResponse;
    }
}
