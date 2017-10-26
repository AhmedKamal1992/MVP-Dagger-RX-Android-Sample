package ahmed.com.mvp_dagger_rx_android_sample.presentation.details;

import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import javax.inject.Inject;

import ahmed.com.mvp_dagger_rx_android_sample.R;
import ahmed.com.mvp_dagger_rx_android_sample.data.remote.model.Article;
import ahmed.com.mvp_dagger_rx_android_sample.presentation.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import static ahmed.com.mvp_dagger_rx_android_sample.utils.Constants.ARTICLE_ITEM;
import static android.text.TextUtils.isEmpty;

public class DetailsActivity extends AppCompatActivity
{
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_news_main_Image)
    ImageView ivMainImage;
    @BindView(R.id.tv_description)
    TextView tvDescription;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Article article = getIntent().getParcelableExtra(ARTICLE_ITEM);
        initUi(article);
    }

    private void initUi(Article article)
    {
        if (!isEmpty(article.getTitle())) {
            tvDescription.setText(article.getTitle());
        }
        if (!isEmpty(article.getTitle())) {
            tvTitle.setText(article.getTitle());
        }
        Picasso picasso = Picasso.with(this);
        RequestCreator requestCreator;
        if (!isEmpty(article.getUrlToImage())) {
            requestCreator = picasso.load(article.getUrlToImage());
        } else {
            requestCreator = picasso.load(R.drawable.news);
        }
        requestCreator.placeholder(R.drawable.news).into(ivMainImage);
    }
}
