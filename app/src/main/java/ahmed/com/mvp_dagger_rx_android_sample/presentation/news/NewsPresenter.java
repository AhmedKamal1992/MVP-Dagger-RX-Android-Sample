package ahmed.com.mvp_dagger_rx_android_sample.presentation.news;

import ahmed.com.mvp_dagger_rx_android_sample.data.remote.model.NewsModel;
import ahmed.com.mvp_dagger_rx_android_sample.presentation.base.BasePresenter;
import ahmed.com.mvp_dagger_rx_android_sample.presentation.base.listeners.RecyclerItemListener;
import ahmed.com.mvp_dagger_rx_android_sample.usecase.news.NewsUsecase;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Ahmed Kamal on 26-10-2017.
 */

public class NewsPresenter extends BasePresenter<NewsContract.NewsView> implements NewsContract.NewsPresenter
{
    private Scheduler mainScheduler , ioScheduler;
    private NewsUsecase newsUsecase;
    private NewsModel newsModel;

    public NewsPresenter(Scheduler mainScheduler, Scheduler ioScheduler, NewsUsecase newsUsecase)
    {
        this.mainScheduler = mainScheduler;
        this.ioScheduler = ioScheduler;
        this.newsUsecase = newsUsecase;
    }

    @Override
    public void getNews()
    {
        checkViewAttached();
        getView().showLoading();
        addDisposable(newsUsecase.getNews()
                .subscribeOn(ioScheduler).observeOn(mainScheduler)
                .subscribeWith(new DisposableObserver<NewsModel>()
        {
            @Override
            public void onNext(@NonNull NewsModel newsModel)
            {
                NewsPresenter.this.newsModel = newsModel;
                getView().showNews(newsModel);
                getView().hideLoading();
            }

            @Override
            public void onError(@NonNull Throwable e)
            {
                getView().hideLoading();
                getView().showError(e.getMessage());
            }

            @Override
            public void onComplete()
            {

            }
        }));
    }


    @Override
    public RecyclerItemListener getRecyclerItemListener()
    {
        return position -> getView().navigateToDetailsScreen(newsModel.getArticles().get(position));
    }
}
