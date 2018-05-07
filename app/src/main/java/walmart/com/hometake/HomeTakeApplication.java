package walmart.com.hometake;

import android.app.Application;
import android.content.Context;

/**
 * Created by Abhigna on 5/5/18.
 */

public class HomeTakeApplication extends Application {

    static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;

    }

    public static Context getAppContext() {
        return sContext;
    }
}
