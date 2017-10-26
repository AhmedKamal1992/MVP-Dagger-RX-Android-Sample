package ahmed.com.mvp_dagger_rx_android_sample.presentation.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ahmed.com.mvp_dagger_rx_android_sample.R;
import ahmed.com.mvp_dagger_rx_android_sample.data.remote.model.Article;
import ahmed.com.mvp_dagger_rx_android_sample.data.remote.model.NewsModel;
import ahmed.com.mvp_dagger_rx_android_sample.presentation.base.listeners.RecyclerItemListener;
import butterknife.BindView;
import butterknife.ButterKnife;

import static ahmed.com.mvp_dagger_rx_android_sample.utils.ResourcesUtil.getDrawableById;
import static android.text.TextUtils.isEmpty;

/**
 * Created by Ahmed Kamal on 26-10-2017.
 */

public class NewsViewHolder extends RecyclerView.ViewHolder
{
    private RecyclerItemListener onItemClickListener;

    @BindView(R.id.tv_caption)
    TextView tvCaption;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_news_item)
    RelativeLayout newsItemLayout;
    @BindView(R.id.iv_news_item_image)
    ImageView newsImage;

    public NewsViewHolder(View itemView, RecyclerItemListener onItemClickListener)
    {
        super(itemView);
        this.onItemClickListener = onItemClickListener;
        ButterKnife.bind(this, itemView);
    }

    public void bind(int position, Article articleItem, RecyclerItemListener recyclerItemListener)
    {
        if (!isEmpty(articleItem.getTitle())) {
            tvTitle.setText(articleItem.getTitle());
        }
        if (!isEmpty(articleItem.getTitle())) {
            tvCaption.setText(articleItem.getTitle());
        }

        if(articleItem.getUrlToImage() != null)
        {
            Picasso.with(newsImage.getContext()).load(articleItem.getUrlToImage()).
                    placeholder(getDrawableById(R.drawable.news)).into(newsImage);
        }
        newsItemLayout.setOnClickListener(view -> recyclerItemListener.onItemSelected(position));
    }
}
