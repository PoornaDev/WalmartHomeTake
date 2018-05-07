package walmart.com.hometake;

/**
 * Created by Abhigna on 3/26/18.
 */

public interface BaseView<T extends  BasePresenter> {

    void setPresenter(T presenter);
    void init();
}
