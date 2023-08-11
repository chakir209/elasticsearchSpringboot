package com.example.demo.service;

import com.example.demo.model.DocumentFile;
import com.example.demo.model.MultiCriteriaSearchRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.erhlc.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.client.erhlc.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

@Service
public class SearchService  {

     private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    public SearchService(RestHighLevelClient elasticsearchClient) {
        this.elasticsearchRestTemplate = new ElasticsearchRestTemplate(elasticsearchClient);
    }

    public SearchHits<DocumentFile> searchDocuments(MultiCriteriaSearchRequest searchRequest) {
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();

        if (searchRequest.getTitle() != null) {
            boolQuery.must(QueryBuilders.matchQuery("title", searchRequest.getTitle()));
        }
        if (searchRequest.getReferenceGed() != null) {
            boolQuery.must(QueryBuilders.matchQuery("referenceGed", searchRequest.getReferenceGed()));
        }
        if (searchRequest.getReference() != null) {
            boolQuery.must(QueryBuilders.matchQuery("reference", searchRequest.getReference()));
        }

        if (searchRequest.getUploadDate() != null) {
            boolQuery.must(QueryBuilders.matchQuery("uploadDate", searchRequest.getUploadDate()));
        }
        if (searchRequest.getDateLastUpdate() != null) {
            boolQuery.must(QueryBuilders.matchQuery("dateLastUpdate", searchRequest.getDateLastUpdate()));
        }


        if (searchRequest.getFolder() != null) {
            boolQuery.must(QueryBuilders.matchQuery("folder", searchRequest.getFolder()));
        }
        if (searchRequest.getSize() != null) {
            boolQuery.must(QueryBuilders.matchQuery("size", searchRequest.getSize()));
        }
        if (searchRequest.getContent() != null) {
            boolQuery.must(QueryBuilders.matchQuery("content", searchRequest.getContent()));
        }
        if (searchRequest.getDescription() != null) {
            boolQuery.must(QueryBuilders.matchQuery("description", searchRequest.getDescription()));
        }
        // ... add more criteria ...

        // Perform the search using the boolQuery
        return elasticsearchRestTemplate.search(new NativeSearchQueryBuilder()
                .withQuery(boolQuery)
                .build(), DocumentFile.class);
    }
}