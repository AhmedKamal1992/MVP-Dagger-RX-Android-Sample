package ahmed.com.mvp_dagger_rx_android_sample;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

import ahmed.com.mvp_dagger_rx_android_sample.injection.AppModule;
import ahmed.com.mvp_dagger_rx_android_sample.injection.DaggerNetComponent;
import ahmed.com.mvp_dagger_rx_android_sample.injection.NetComponent;
import ahmed.com.mvp_dagger_rx_android_sample.injection.NetModule;

/**
 * Created by Ahmed Kamal on 25-10-2017.
 */

public class App extends Application
{
    private static WeakReference<Context> context;
    private NetComponent netComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = new WeakReference<>(this);

        /*if (LeakCanary.isInAnalyzerProcess(this))
        {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);*/

        netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule())
                .build();
    }

    public static Context getContext()
    {
        return context.get();
    }

    public NetComponent getNetComponent()
    {
        return netComponent;
    }
}
