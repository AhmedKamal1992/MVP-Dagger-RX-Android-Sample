package ahmed.com.mvp_dagger_rx_android_sample.presentation.news;

import android.content.Intent;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import ahmed.com.mvp_dagger_rx_android_sample.App;
import ahmed.com.mvp_dagger_rx_android_sample.R;
import ahmed.com.mvp_dagger_rx_android_sample.data.remote.model.Article;
import ahmed.com.mvp_dagger_rx_android_sample.data.remote.model.NewsModel;
import ahmed.com.mvp_dagger_rx_android_sample.presentation.base.BaseActivity;
import ahmed.com.mvp_dagger_rx_android_sample.presentation.details.DetailsActivity;
import ahmed.com.mvp_dagger_rx_android_sample.usecase.news.NewsUsecase;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static ahmed.com.mvp_dagger_rx_android_sample.utils.Constants.ARTICLE_ITEM;

public class NewsActivity extends BaseActivity implements NewsContract.NewsView
{

    @Inject
    NewsUsecase newsUsecase;

    NewsPresenter presenter;

    @BindView(R.id.rv_news_list)
    RecyclerView rvNews;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.rl_news_list)
    RelativeLayout rlNewsList;
    @BindView(R.id.btn_search)
    ImageButton btnSearch;
    @BindView(R.id.et_search)
    EditText editTextSearch;

    private NewsAdapter newsAdapter;

    @Override
    protected void initializeDagger()
    {
        ((App) getApplicationContext()).getNetComponent().inject(NewsActivity.this);
    }

    @Override
    protected void initializePresenter()
    {
        presenter = new NewsPresenter(AndroidSchedulers.mainThread() , Schedulers.io() , newsUsecase);
        presenter.attachView(this);
        presenter.getNews();
    }

    @Override
    public int getLayoutId()
    {
        return R.layout.activity_news;
    }


    @Override
    public void showNews(NewsModel newsModel)
    {
        newsAdapter = new NewsAdapter(presenter.getRecyclerItemListener(), newsModel.getArticles());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvNews.setLayoutManager(layoutManager);
        rvNews.setHasFixedSize(true);
        rvNews.setAdapter(newsAdapter);
    }

    @Override
    public void showError(String msg)
    {
        Snackbar.make(rlNewsList , msg , Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading()
    {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading()
    {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void navigateToDetailsScreen(Article article)
    {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(ARTICLE_ITEM, article);
        startActivity(intent);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        presenter.detechView();
    }

    @OnClick({R.id.ic_toolbar_setting, R.id.ic_toolbar_refresh, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_toolbar_refresh:
                presenter.getNews();
                break;
            case R.id.btn_search:
                newsAdapter.filter(editTextSearch.getText().toString());
        }
    }
}
