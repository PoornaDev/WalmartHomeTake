package walmart.com.hometake;


import android.content.Context;
import android.view.View;

import java.util.List;

import walmart.com.hometake.model.pojos.Item;
import walmart.com.hometake.model.pojos.SearchResults;

public interface HomeTakeContract {

    interface SearchItemsView extends BaseView<SearchPresenter> {
            void showProgress();
            void hideProgress();
            boolean validateInput(String input);
            void showSearchResults(List<Item> resultsList);
    }

    interface ItemDetailsView extends BaseView<ItemDetailsPresenter>{
        void populatedItemDetails(Item item);
        void showProgress();
        void hideProgress();
        void populateRecommendedItemsList(List<Item> items);
        void populateNoRecordsAvailable();

    }

    interface SearchPresenter extends BasePresenter {
        void populateSearchResults(SearchResults movies);
        void loadItems(String inputStr);
    }

    interface ItemDetailsPresenter extends  BasePresenter{
        void showItemDetails(Item item);
        void loadRecommendedItems(int itemId);
        void loadItemDetails(String itemId);
    }
}
