package ahmed.com.mvp_dagger_rx_android_sample.data.remote;

import ahmed.com.mvp_dagger_rx_android_sample.data.remote.model.NewsModel;
import io.reactivex.Observable;
import retrofit2.http.GET;

import static ahmed.com.mvp_dagger_rx_android_sample.utils.Constants.NEWS_API;
import static ahmed.com.mvp_dagger_rx_android_sample.utils.Constants.NEWS_API_KEY;

/**
 * Created by Ahmed Kamal on 25-10-2017.
 */

public interface NewsService
{
    @GET(NEWS_API + NEWS_API_KEY)
    Observable<NewsModel> getNews();
}
