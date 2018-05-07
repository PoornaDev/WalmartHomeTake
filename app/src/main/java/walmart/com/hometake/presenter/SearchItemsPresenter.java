package walmart.com.hometake.presenter;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import walmart.com.hometake.HomeTakeApplication;
import walmart.com.hometake.HomeTakeContract;
import walmart.com.hometake.model.network.HomeTakeSearchServiceInterface;
import walmart.com.hometake.model.network.NetworkUtils;
import walmart.com.hometake.model.network.RetrofitUtils;
import walmart.com.hometake.model.pojos.Item;
import walmart.com.hometake.model.pojos.SearchResults;
import walmart.com.hometake.model.utils.NetworkConstants;

/**
 * Created by Abhigna on 5/5/18.
 */

public class SearchItemsPresenter implements HomeTakeContract.SearchPresenter {

    HomeTakeContract.SearchItemsView  mView;
    public SearchItemsPresenter(HomeTakeContract.SearchItemsView view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        //load startup content if any

    }
    @Override
    public void populateSearchResults(SearchResults movies) {

        if(movies.getItems()!=null && movies.getItems().size() > 0 ) {
            mView.showSearchResults(movies.getItems());
            mView.hideProgress();
        }
    }

    @Override
    public void loadItems(String  queryInput) {

        mView.showProgress();

        //if(NetworkUtils.isNetworkConnected(HomeTakeApplication.getAppContext())){

            Retrofit retrofit = RetrofitUtils.getRetrofit();
            HomeTakeSearchServiceInterface service = retrofit.create(HomeTakeSearchServiceInterface.class);
            Call<SearchResults> searchResultsCallback =  service.getSearchResultsList(queryInput, NetworkConstants.API_KEY, "json");
            Log.i("loadItems URL",searchResultsCallback.request().toString());
            searchResultsCallback.enqueue(new Callback<SearchResults>() {
                @Override
                public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
                    SearchResults searchResults = response.body();
                    Log.i("Item Size::",searchResults.getItems().size()+"");
                    if(searchResults !=null) {
                        populateSearchResults(searchResults);
                    }
                }
                @Override
                public void onFailure(Call<SearchResults> call, Throwable t) {
                    Log.i("ERROR","onFailure");
                }
            });
       // }
    }

}
