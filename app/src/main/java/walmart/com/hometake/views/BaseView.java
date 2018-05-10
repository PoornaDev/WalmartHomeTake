package walmart.com.hometake.views;

import android.content.Context;

import walmart.com.hometake.presenter.BasePresenter;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);
    void init();
    Context getActivityContext();
    void showNoNetworkToast();
}
