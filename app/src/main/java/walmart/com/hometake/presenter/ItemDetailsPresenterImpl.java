package walmart.com.hometake.presenter;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import walmart.com.hometake.HomeTakeContract;
import walmart.com.hometake.model.network.HomeTakeSearchServiceInterface;
import walmart.com.hometake.model.network.NetworkUtils;
import walmart.com.hometake.model.network.RetrofitUtils;
import walmart.com.hometake.model.pojos.Item;
import walmart.com.hometake.model.utils.NetworkConstants;

public class ItemDetailsPresenterImpl implements HomeTakeContract.ItemDetailsPresenter {

    private HomeTakeContract.ItemDetailsView mView;
    public static final String TAG = "DetailsPresenterImpl";

    public ItemDetailsPresenterImpl(HomeTakeContract.ItemDetailsView view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        //TODO: do initialization stuff here
    }

    @Override
    public void showItemDetails(Item item) {
        if(item!=null) {
            mView.populatedItemDetails(item);
        }
    }

    @Override
    public void loadRecommendedItems(int itemId) {
        if(!NetworkUtils.isNetworkConnected(mView.getActivityContext())) {
            mView.showNoNetworkToast();
            mView.populateRecommendedItemsList(null);
            return;
        }

        Retrofit retrofit = RetrofitUtils.getRetrofit();
        HomeTakeSearchServiceInterface serviceInterface =  retrofit.create(HomeTakeSearchServiceInterface.class);
        Call<List<Item>> recommendedCallback =  serviceInterface.getRecommendedItems( NetworkConstants.API_KEY,itemId+"");
        Log.i(TAG,"Request::"+recommendedCallback.request().toString());
        recommendedCallback.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                List<Item> recommendedList  =response.body();
                if(recommendedList != null) {
                    Log.i(TAG, recommendedList.size()+"");
                    mView.populateRecommendedItemsList(recommendedList);
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                t.printStackTrace();
                Log.e(TAG, "onFailure");
                mView.populateNoRecordsAvailable();
            }
        });
    }

    @Override
    public void loadItemDetails(String itemId) {

        if(!NetworkUtils.isNetworkConnected(mView.getActivityContext())) {
            mView.showNoNetworkToast();
            return;
        }

        mView.showProgress();
        Retrofit retrofit = RetrofitUtils.getRetrofit();
        HomeTakeSearchServiceInterface serviceInterface =  retrofit.create(HomeTakeSearchServiceInterface.class);
        Call<Item> productDetailsCall = serviceInterface.getRecommendedProductDetails(itemId, NetworkConstants.API_KEY);
        productDetailsCall.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item item = response.body();
                if(item!=null) {
                    showItemDetails(item);
                }
            }

            @Override
            public void onFailure(Call<Item> call, Throwable t) {

            }
        });
    }

}
