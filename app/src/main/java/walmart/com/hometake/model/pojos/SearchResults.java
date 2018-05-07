package walmart.com.hometake.model.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResults {

    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("sort")
    @Expose
    private String sort;
    @SerializedName("responseGroup")
    @Expose
    private String responseGroup;
    @SerializedName("totalResults")
    @Expose
    private int totalResults;
    @SerializedName("start")
    @Expose
    private int start;
    @SerializedName("numItems")
    @Expose
    private int numItems;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("facets")
    @Expose
    private List<Object> facets = null;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getResponseGroup() {
        return responseGroup;
    }

    public void setResponseGroup(String responseGroup) {
        this.responseGroup = responseGroup;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Object> getFacets() {
        return facets;
    }

    public void setFacets(List<Object> facets) {
        this.facets = facets;
    }

}