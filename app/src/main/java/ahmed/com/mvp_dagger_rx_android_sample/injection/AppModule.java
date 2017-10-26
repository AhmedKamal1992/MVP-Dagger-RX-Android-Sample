package ahmed.com.mvp_dagger_rx_android_sample.injection;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ahmed Kamal on 26-10-2017.
 */
@Module
public class AppModule
{
    private Application application;

    public AppModule(Application application)
    {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application providesApplication()
    {
        return application;
    }
}
