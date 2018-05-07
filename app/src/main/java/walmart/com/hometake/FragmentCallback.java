package walmart.com.hometake;

import android.content.Context;

import walmart.com.hometake.model.pojos.Item;

/**
 * Created by Abhigna on 5/5/18.
 */

public interface FragmentCallback {

     void onListItemClicked(Item item);
     Context getActivityContext();

}
