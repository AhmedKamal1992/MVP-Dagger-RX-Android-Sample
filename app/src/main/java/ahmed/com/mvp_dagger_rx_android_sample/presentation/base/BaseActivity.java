package ahmed.com.mvp_dagger_rx_android_sample.presentation.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import ahmed.com.mvp_dagger_rx_android_sample.R;
import ahmed.com.mvp_dagger_rx_android_sample.presentation.base.listeners.ActionBarView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Ahmed Kamal on 25-10-2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements MvpView,
        ActionBarView {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @BindView(R.id.ic_toolbar_setting)
    ImageView icSettings;

    @Nullable
    @BindView(R.id.ic_toolbar_refresh)

    protected
    ImageView icHome;

    private Unbinder unbinder;

    protected abstract void initializeDagger();

    protected abstract void initializePresenter();

    public abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        initializeDagger();
        initializePresenter();
        initializeToolbar();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void initializeToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    public void setUpIconVisibility(boolean visible) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(visible);
        }
    }

    @Override
    public void setTitle(String titleKey) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            TextView title = ButterKnife.findById(this, R.id.txt_toolbar_title);
            if (title != null) {
                title.setText(titleKey);
            }
        }
    }

    @Override
    public void setSettingsIconVisibility(boolean visible) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            ImageView icon = ButterKnife.findById(this, R.id.ic_toolbar_setting);
            if (icon != null) {
                icon.setVisibility(visible ? VISIBLE : GONE);
            }
        }
    }

    @Override
    public void setRefreshVisibility(boolean visible) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            ImageView icon = ButterKnife.findById(this, R.id.ic_toolbar_refresh);
            if (icon != null) {
                icon.setVisibility(visible ? VISIBLE : GONE);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}