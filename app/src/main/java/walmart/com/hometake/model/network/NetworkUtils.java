package walmart.com.hometake.model.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Abhigna on 4/24/18.
 */

public class NetworkUtils {

    public static boolean isNetworkConnected(Context context) {

        ConnectivityManager ConnectionManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected())
            return  true;

        return  false;
    }
}
