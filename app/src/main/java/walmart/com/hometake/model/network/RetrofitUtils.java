package walmart.com.hometake.model.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import walmart.com.hometake.model.utils.NetworkConstants;

/**
 * Created by Abhigna on 5/6/18.
 */

public class RetrofitUtils {

    public static Retrofit getRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
