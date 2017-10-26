package ahmed.com.mvp_dagger_rx_android_sample.presentation.base;

/**
 * Created by Ahmed Kamal on 25-10-2017.
 */

public interface MvpPresenter<V extends MvpView>
{
    void attachView(V view);
    void detechView();
}
