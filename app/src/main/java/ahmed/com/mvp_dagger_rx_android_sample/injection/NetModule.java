package ahmed.com.mvp_dagger_rx_android_sample.injection;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import ahmed.com.mvp_dagger_rx_android_sample.data.remote.NewsService;
import ahmed.com.mvp_dagger_rx_android_sample.usecase.news.NewsUsecase;
import ahmed.com.mvp_dagger_rx_android_sample.usecase.news.NewsUsecaseImp;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static ahmed.com.mvp_dagger_rx_android_sample.utils.Constants.BASE_URL;

/**
 * Created by Ahmed Kamal on 26-10-2017.
 */

@Module
public class NetModule
{
    private OkHttpClient okHttpClient;
    private NewsService newsService;
    private Retrofit retrofitInstance;

    @Singleton
    @Provides
    NewsUsecase providesNewsUsecase()
    {
        return new NewsUsecaseImp(providesNewsService());
    }

    @Provides
    @Singleton
    NewsService providesNewsService()
    {
        if(newsService == null)
        {
            newsService = provideRetrofit().create(NewsService.class);
        }
        return newsService;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application)
    {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient()
    {
        if(okHttpClient == null)
        {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        }
        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit()
    {
        if(retrofitInstance == null)
        {
            Retrofit.Builder retrofit = new Retrofit.Builder().client(provideOkhttpClient()).baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            retrofitInstance = retrofit.build();
        }
        return retrofitInstance;
    }
}
