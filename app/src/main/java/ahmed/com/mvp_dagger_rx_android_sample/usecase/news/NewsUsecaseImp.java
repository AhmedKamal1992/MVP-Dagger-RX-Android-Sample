package ahmed.com.mvp_dagger_rx_android_sample.usecase.news;

import java.io.IOException;

import ahmed.com.mvp_dagger_rx_android_sample.App;
import ahmed.com.mvp_dagger_rx_android_sample.data.remote.NewsService;
import ahmed.com.mvp_dagger_rx_android_sample.data.remote.model.NewsModel;
import ahmed.com.mvp_dagger_rx_android_sample.utils.NetworkUtils;
import io.reactivex.Observable;

import static ahmed.com.mvp_dagger_rx_android_sample.utils.Constants.INTERNET_CONNECTION_ERROR;
import static ahmed.com.mvp_dagger_rx_android_sample.utils.Constants.SERVER_ERROR;

/**
 * Created by Ahmed Kamal on 26-10-2017.
 */

public class NewsUsecaseImp implements NewsUsecase
{

    private NewsService newsService;

    public NewsUsecaseImp(NewsService newsService)
    {
        this.newsService = newsService;
    }

    @Override
    public Observable<NewsModel> getNews()
    {
       if(NetworkUtils.isConnected(App.getContext()))
       {
            return Observable.defer(() -> newsService.getNews()).retryWhen(throwableObservable -> throwableObservable.flatMap(throwable -> {
                if(throwable instanceof IOException)
                {
                    Observable.error(new Throwable(SERVER_ERROR));
                }
                return Observable.error(throwable);
            }));
       }
       else
       {
           return Observable.error(new Throwable(INTERNET_CONNECTION_ERROR));
       }
    }
}
