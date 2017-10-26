package ahmed.com.mvp_dagger_rx_android_sample.presentation.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ahmed.com.mvp_dagger_rx_android_sample.R;
import ahmed.com.mvp_dagger_rx_android_sample.data.remote.model.Article;
import ahmed.com.mvp_dagger_rx_android_sample.data.remote.model.NewsModel;
import ahmed.com.mvp_dagger_rx_android_sample.presentation.base.listeners.RecyclerItemListener;

/**
 * Created by Ahmed Kamal on 26-10-2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder>
{
    private List<Article> articles;
    private List<Article> articleSearch;
    private RecyclerItemListener onItemClickListener;

    public NewsAdapter(RecyclerItemListener recyclerItemListener , List<Article> articles)
    {
        this.articles = articles;
        this.articleSearch = articles;
        this.onItemClickListener = recyclerItemListener;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new NewsViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position)
    {
        holder.bind(position , articles.get(position) , onItemClickListener);
    }

    @Override
    public int getItemCount()
    {
        return articles.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        articles = new ArrayList<Article>();
        if (charText.length() == 0) {
            articles.addAll(articleSearch);
        } else {
            for (Article item : articleSearch) {
                if (item.getTitle().toLowerCase(Locale.getDefault()).contains(charText)) {
                    articles.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}
