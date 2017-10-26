package ahmed.com.mvp_dagger_rx_android_sample.presentation.news;

import ahmed.com.mvp_dagger_rx_android_sample.data.remote.model.Article;
import ahmed.com.mvp_dagger_rx_android_sample.data.remote.model.NewsModel;
import ahmed.com.mvp_dagger_rx_android_sample.presentation.base.MvpPresenter;
import ahmed.com.mvp_dagger_rx_android_sample.presentation.base.MvpView;
import ahmed.com.mvp_dagger_rx_android_sample.presentation.base.listeners.RecyclerItemListener;

/**
 * Created by Ahmed Kamal on 26-10-2017.
 */

public interface NewsContract
{
    interface NewsView extends MvpView
    {
        void showNews(NewsModel newsModel);
        void showError(String msg);
        void showLoading();
        void hideLoading();
        void navigateToDetailsScreen(Article article);
    }

    interface NewsPresenter extends MvpPresenter<NewsView>
    {
        void getNews();
        RecyclerItemListener getRecyclerItemListener();
    }
}
