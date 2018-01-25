package tech.text.search.elasticsearch.model;

import java.util.List;

/**
 * Created by datum on 24/1/18.
 */
public class AutoCompleteResponse<T> {

    private long totalHits;
    private List<T> values;

    public long getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(long totalHits) {
        this.totalHits = totalHits;
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }
}
