package walmart.com.hometake.model.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import walmart.com.hometake.model.pojos.Item;
import walmart.com.hometake.model.pojos.SearchResults;

/**
 * Created by Abhigna on 3/26/18.
 */

public interface HomeTakeSearchServiceInterface {

    @GET("/v1/search")
    Call<SearchResults> getSearchResultsList(
            @Query("query") String inputQuery,
            @Query("apiKey") String apiKey,
            //@Query("lsPublisherId") String publisher,
            @Query("format") String format
    );


    @GET("/v1/items/{id}")
    Call<Item> getRecommendedProductDetails(
            @Path("id") String itemId,
            @Query("apiKey") String apiKey
    );

    @GET("/v1/nbp")
    Call<List<Item>> getRecommendedItems(
            @Query("apiKey") String apiKey,
            @Query("itemId") String itemId
    );
}
