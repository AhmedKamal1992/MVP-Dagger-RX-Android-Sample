package ahmed.com.mvp_dagger_rx_android_sample.usecase.news;

import ahmed.com.mvp_dagger_rx_android_sample.data.remote.model.NewsModel;
import io.reactivex.Observable;

/**
 * Created by Ahmed Kamal on 26-10-2017.
 */

public interface NewsUsecase
{
    Observable<NewsModel> getNews();
}
