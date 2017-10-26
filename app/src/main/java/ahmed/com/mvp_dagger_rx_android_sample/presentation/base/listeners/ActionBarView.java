package ahmed.com.mvp_dagger_rx_android_sample.presentation.base.listeners;

/**
 * Created by Ahmed Kamal on 25-10-2017.
 */

public interface ActionBarView {

    void setUpIconVisibility(boolean visible);

    void setTitle(String titleKey);

    void setSettingsIconVisibility(boolean visibility);

    void setRefreshVisibility(boolean visibility);
}
