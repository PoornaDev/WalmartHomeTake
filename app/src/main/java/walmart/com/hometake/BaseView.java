package walmart.com.hometake;

import android.content.Context;

public interface BaseView<T extends  BasePresenter> {

    void setPresenter(T presenter);
    void init();
    Context getActivityContext();
    void showNoNetworkToast();
}
