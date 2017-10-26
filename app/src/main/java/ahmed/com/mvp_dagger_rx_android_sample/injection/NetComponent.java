package ahmed.com.mvp_dagger_rx_android_sample.injection;

import javax.inject.Singleton;

import ahmed.com.mvp_dagger_rx_android_sample.presentation.news.NewsActivity;
import dagger.Component;

/**
 * Created by Ahmed Kamal on 26-10-2017.
 */

@Singleton
@Component(modules = {AppModule.class , NetModule.class})
public interface NetComponent
{
    void inject(NewsActivity newsActivity);
}
