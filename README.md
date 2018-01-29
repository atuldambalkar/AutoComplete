# AutoComplete

## Index definition
```json
PUT /bestbuy
{
  "settings": {
    "number_of_shards": 5,
    "analysis": {
      "filter": {
        "nGram_filter": {
          "type": "nGram",
          "min_gram": 2,
          "max_gram": 20,
          "token_chars": [
            "letter",
            "digit",
            "punctuation",
            "symbol"
          ]
        }
      },
      "analyzer": {
        "nGram_analyzer": {
          "type": "custom",
          "tokenizer": "whitespace",
          "filter": [
            "lowercase",
            "asciifolding",
            "nGram_filter"
          ]
        },
        "whitespace_analyzer": {
          "type": "custom",
          "tokenizer": "whitespace",
          "filter": [
            "lowercase",
            "asciifolding"
          ]
        }
      }
    }
  },

  "mappings": {
    "product": {
      "properties": {
        "sku": {
          "type": "text",
          "index": false
        },
        "name": {
          "type": "text",
          "index": true,
          "analyzer": "nGram_analyzer",
          "search_analyzer": "whitespace_analyzer"
        },
        "type": {
          "type": "text",
          "index": false
        },
        "price": {
          "type": "float",
          "index": false
        },
        "upc": {
          "type": "text",
          "index": false
        },
        "category": {
          "type": "nested",
          "properties": {
            "id": {
              "type": "text",
              "index": false
            },
            "name": {
              "type": "text",
              "index": false
            }
          }
        },
        "shipping": {
          "type": "float",
          "index": false
        },
        "description": {
          "type": "text",
          "index": false
        },
        "manufacturer": {
          "type": "text",
          "index": false
        },
        "model": {
          "type": "text",
          "index": false
        },
        "url": {
          "type": "text",
          "index": false
        },
        "image": {
          "type": "text",
          "index": false
        }
      }
    }
  }
}
```

## Sample query -
POST /bestbuy/product/_search
{
  "query": {
    "multi_match": {
      "query": "ctor",
      "fields": ["name"]
    }
  }
}

## References
  - https://qbox.io/blog/multi-field-partial-word-autocomplete-in-elasticsearch-using-ngrams
  - https://artifacts.elastic.co/javadoc/org/elasticsearch/client/elasticsearch-rest-high-level-client/5.6.3/index.html
  - https://www.elastic.co/guide/en/elasticsearch/client/java-rest/master/java-rest-high-search.html
  - https://github.com/BestBuyAPIs/open-data-set
  - https://spring.io/guides/gs/rest-service/
  - http://www.jquerycookbook.com/demos/S2-InputControls/16.1-Autocomplete-ajax.html
  - https://stackoverflow.com/questions/31876389/how-to-serve-static-html-content-page-in-spring-boot
