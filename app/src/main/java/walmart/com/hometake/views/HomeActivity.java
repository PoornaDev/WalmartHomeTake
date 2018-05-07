package walmart.com.hometake.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import walmart.com.hometake.FragmentCallback;
import walmart.com.hometake.HomeTakeContract;
import walmart.com.hometake.R;
import walmart.com.hometake.model.pojos.Item;
import walmart.com.hometake.presenter.SearchItemsPresenter;

public class HomeActivity extends AppCompatActivity implements HomeTakeContract.SearchItemsView, FragmentCallback {

    HomeTakeContract.SearchPresenter presenter;
    ItemFragment itemFragment;
    private View mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        MenuItem searchMenuItem = menu.findItem( R.id.search ); // get my MenuItem with placeholder submenu
        searchMenuItem.expandActionView(); // E

        final SearchView searchView = (SearchView) menu.findItem(
                R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(validateInput(query)) {
                    searchView.setQuery("", false);
                    searchView.clearFocus();
                    presenter.loadItems(query);
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(HomeTakeContract.SearchPresenter presenter) {
            this.presenter = presenter;
    }

    @Override
    public void showProgress() {
        if(mProgressBar!=null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if(mProgressBar!=null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public boolean validateInput(String input) {
        return (input == null || input.length() <1 ) ? false : true;
    }

    @Override
    public void showSearchResults(final List<Item> searchResults) {
        itemFragment.refreshAdapter(searchResults);
    }


    @Override
    public void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(null);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);


        mProgressBar = findViewById(R.id.progress_bar);
        presenter = new SearchItemsPresenter(this);

        //
        FragmentManager fragmentManager =  getSupportFragmentManager();
        itemFragment = (ItemFragment) fragmentManager.findFragmentByTag(ItemFragment.class.getSimpleName());
        if(null == itemFragment) {
            itemFragment = ItemFragment.newInstance(new Bundle());
            fragmentManager.beginTransaction()
                    .replace(R.id.content_layout, itemFragment, ItemFragment.class.getSimpleName())
                    .commit();
        }
    }

    @Override
    public void onListItemClicked(Item item) {
        //Launch Details Screen
        Intent intent = new Intent(this, ItemDetailsActivity.class);
        intent.putExtra(BundleConstants.ITEM_DETAILS_KEY, item);
        startActivity(intent);

    }

    @Override
    public Context getActivityContext() {
        return getBaseContext();
    }
}
