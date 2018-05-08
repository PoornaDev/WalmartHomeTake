package walmart.com.hometake.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import walmart.com.hometake.FragmentCallback;
import walmart.com.hometake.HomeTakeContract;
import walmart.com.hometake.R;
import walmart.com.hometake.model.pojos.Item;
import walmart.com.hometake.presenter.ItemDetailsPresenterImpl;

public class ItemDetailsActivity extends AppCompatActivity implements FragmentCallback,
                    HomeTakeContract.ItemDetailsView {

    HomeTakeContract.ItemDetailsPresenter mPresenter;
    ItemDetailsFragment detailsFragment;
    RecommendedProductsListFragment mRecommendedListFragment;
    ProgressBar mBottomProgressBar;
    View noDataAvailableView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        init();
    }

    @Override
    public void setPresenter(HomeTakeContract.ItemDetailsPresenter presenter) {
        mPresenter =  presenter;
    }

    @Override
    public void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mPresenter = new ItemDetailsPresenterImpl(this);
        Item item = (Item) getIntent().getSerializableExtra(BundleConstants.ITEM_DETAILS_KEY);
        mPresenter.showItemDetails(item);

        initRecommendedListFragment();
        if(item!=null){
            mPresenter.loadRecommendedItems(item.getItemId());
        }
    }

    private void initRecommendedListFragment() {

        mBottomProgressBar = (ProgressBar) findViewById(R.id.recommended_list_progress);

        noDataAvailableView = findViewById(R.id.no_data_tv);

        FragmentManager fragmentManager =  getSupportFragmentManager();
        mRecommendedListFragment = (RecommendedProductsListFragment) fragmentManager
                .findFragmentByTag(RecommendedProductsListFragment.class.getSimpleName());

        if(mRecommendedListFragment ==  null) {
            mRecommendedListFragment  =  RecommendedProductsListFragment.newInstance(new Bundle());
            fragmentManager.beginTransaction().replace(R.id.recommended_list_container,
                    mRecommendedListFragment,
                    RecommendedProductsListFragment.class.getSimpleName()).commit();
        }

    }

    @Override
    public void onListItemClicked(Item item) {
            Intent intent = new Intent(this,ItemDetailsActivity.class);
            intent.putExtra(BundleConstants.ITEM_DETAILS_KEY, item);
            startActivity(intent);
    }

    @Override
    public Context getActivityContext() {
        return getBaseContext();
    }

    @Override
    public void populatedItemDetails(Item item) {
       FragmentManager fragmentManager =  getSupportFragmentManager();
       detailsFragment = (ItemDetailsFragment) fragmentManager.findFragmentByTag(ItemDetailsFragment.class.getSimpleName());
        if(detailsFragment ==  null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(BundleConstants.ITEM_DETAILS_KEY, item);
            detailsFragment  =  ItemDetailsFragment.newInstance(bundle);
            fragmentManager.beginTransaction().replace(R.id.item_details_container,
                                                         detailsFragment,
                                                         ItemDetailsFragment.class.getSimpleName()).commit();
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void populateRecommendedItemsList(List<Item> items) {

        if(mBottomProgressBar!=null) {
            mBottomProgressBar.setVisibility(View.GONE);
        }
        if(null == items) {
            return;
        }
        if(noDataAvailableView!=null) {
            noDataAvailableView.setVisibility(View.GONE);
        }
        mRecommendedListFragment.refreshAdapter(items);

    }

    @Override
    public void populateNoRecordsAvailable() {
        if(mBottomProgressBar!=null) {
            mBottomProgressBar.setVisibility(View.GONE);
        }
        if(noDataAvailableView!=null) {
            noDataAvailableView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showNoNetworkToast() {
        Toast.makeText(this, getString(R.string.no_network_message), Toast.LENGTH_SHORT).show();
    }

}
